package br.com.pi_adocao_pet_back.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.pi_adocao_pet_back.adapter.DozerConverter;
import br.com.pi_adocao_pet_back.domain.dto.AdocaoUsuarioDTO;
import br.com.pi_adocao_pet_back.domain.entity.Adocao;
import br.com.pi_adocao_pet_back.domain.entity.Login;
import br.com.pi_adocao_pet_back.domain.vo.v1.AdocaoVO;
import br.com.pi_adocao_pet_back.exception.ResourceNotFoundException;
import br.com.pi_adocao_pet_back.repository.AdocaoRepository;

@Service
public class AdocaoService {
	@Autowired
	AdocaoRepository repository;
	@Autowired
	LoginService loginService;
	
	final String ADMINISTRADOR = "administrador";
	
	public AdocaoVO inserir(AdocaoVO adocao) {
		var entity = DozerConverter.parseObject(adocao, Adocao.class);
		var adocaoVO = DozerConverter.parseObject(repository.save(entity), AdocaoVO.class);
		return adocaoVO;
	}

	public Page<AdocaoVO> buscarTodos(Pageable pageable) {
		var page = repository.findAll(pageable);
		return page.map(this::convertToAdocaoVO);
	}

	private AdocaoVO convertToAdocaoVO(Adocao entity) {
		return DozerConverter.parseObject(entity, AdocaoVO.class);
	}

	public Page<AdocaoVO> buscarTodosPorUsuario(Long idUsuario, Pageable pageable) {
		var page = repository.findAllByUsuario(idUsuario, pageable);
		return page.map(this::convertToAdocaoVO);
	}

	public AdocaoVO buscarPorId(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id."));
		return DozerConverter.parseObject(entity, AdocaoVO.class);
	}

	public void delete(Long id) {
		Adocao entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id."));
		repository.delete(entity);
	}

	public ResponseEntity<String> atualizar(AdocaoUsuarioDTO adocaousuarioDTO) {
		var entity = repository.findById(adocaousuarioDTO.getAdocaoVO().getKey())
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id."));
		

		Login login = loginService.findLoginByUser(adocaousuarioDTO.getUsuarioVO());
		AdocaoVO vo = null;
		if(login.getRoles().contains(ADMINISTRADOR)) {
			entity.setStatus(adocaousuarioDTO.getAdocaoVO().getStatus());
			entity.setDataSolicitacao(adocaousuarioDTO.getAdocaoVO().getDataSolicitacao());
			entity.setDataAtualizacao(adocaousuarioDTO.getAdocaoVO().getDataAtualizacao());
			vo = DozerConverter.parseObject(repository.save(entity), AdocaoVO.class);
			if(vo == null) {
				return new ResponseEntity<>("Erro ao alterar Adoção", HttpStatus.BAD_REQUEST);
			}else {
				return new ResponseEntity<>("Adoção atualizada com sucesso", HttpStatus.OK);
			}
		}else {
			return new ResponseEntity<>("Usuario não tem permissão para alterar adoção", HttpStatus.FORBIDDEN);
		}
		
	}

}
