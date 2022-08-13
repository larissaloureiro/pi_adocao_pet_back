package br.com.pi_adocao_pet_back.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.pi_adocao_pet_back.adapter.DozerConverter;
import br.com.pi_adocao_pet_back.domain.dto.UsuarioAlterPermissaoDTO;
import br.com.pi_adocao_pet_back.domain.dto.UsuarioLoginDTO;
import br.com.pi_adocao_pet_back.domain.entity.Login;
import br.com.pi_adocao_pet_back.domain.entity.Permissao;
import br.com.pi_adocao_pet_back.domain.entity.Usuario;
import br.com.pi_adocao_pet_back.domain.vo.v1.AnimalVO;
import br.com.pi_adocao_pet_back.domain.vo.v1.UsuarioVO;
import br.com.pi_adocao_pet_back.exception.IllegalArgumentException;
import br.com.pi_adocao_pet_back.exception.ResourceNotFoundException;
import br.com.pi_adocao_pet_back.repository.UsuarioRepository;
import br.com.pi_adocao_pet_back.security.LoginVO;

@Service
public class UsuarioService {
	@Autowired
	UsuarioRepository repository;
	
	@Autowired
	LoginService loginService;

	@Autowired
	PermissaoService permissaoService;
	
	final String ADMINISTRADOR = "administrador";

	public UsuarioVO inserir(UsuarioLoginDTO usuariologinDTO) {
		Usuario usuario = DozerConverter.parseObject(usuariologinDTO.getUsuarioVO(), Usuario.class);
		Login login = DozerConverter.parseObject(usuariologinDTO.getLoginVO(), Login.class);
		
		if (!usuario.validaCpf()) {
			throw new IllegalArgumentException("CPF Inválido.");
		}

		if (!usuario.possuiIdadeMinima()) {
			throw new IllegalArgumentException("Não possui a idade mínima.");
		}

		var vo = DozerConverter.parseObject(repository.save(usuario), UsuarioVO.class);
		
		if(vo != null) {
			Permissao permissao = permissaoService.findPermissaoByDescription("tutor");
			login.setUsuario(usuario);
			login.setPermissoes(Arrays.asList(permissao));
			loginService.create(login);
		}
		
//	public UsuarioVO inserir(UsuarioVO usuario) {
//		var entity = DozerConverter.parseObject(usuario, Usuario.class);
//
//		if (!entity.validaCpf()) {
//			throw new IllegalArgumentException("CPF Inválido.");
//		}
//
//		if (!entity.possuiIdadeMinima()) {
//			throw new IllegalArgumentException("Não possui a idade mínima.");
//		}
//
//		var vo = DozerConverter.parseObject(repository.save(entity), UsuarioVO.class);
		return vo;
	}

	public Page<UsuarioVO> buscarTodos(Pageable pageable) {
		var page = repository.findAll(pageable);
		return page.map(this::convertToUsuarioVO);
	}

	public UsuarioVO buscarPorId(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse ID."));
		return DozerConverter.parseObject(entity, UsuarioVO.class);
	}

	public void delete(Long id) {
		Usuario entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse ID."));
		repository.delete(entity);
	}

	public UsuarioVO atualizar(UsuarioVO usuario) {
		var entity = repository.findById(usuario.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse ID."));

		entity.setNome(usuario.getNome());
		entity.setTelefone(usuario.getTelefone());
		entity.setEmail(usuario.getEmail());
		entity.setRg(usuario.getRg());
		entity.setCpf(usuario.getCpf());
		entity.setDataNascimento(usuario.getDataNascimento());
		entity.setDataCadastro(usuario.getDataCadastro());

		entity.setEndereco(usuario.getEndereco());


		if (!entity.validaCpf()) {
			throw new IllegalArgumentException("CPF Inválido.");
		}

		if (!entity.possuiIdadeMinima()) {
			throw new IllegalArgumentException("Não possui a idade mínima.");
		}

		var vo = DozerConverter.parseObject(repository.save(entity), UsuarioVO.class);
		return vo;
	}

	public ResponseEntity<String> atualizarPermissao(UsuarioAlterPermissaoDTO usuarioAlterPermissaoDTO) {
		repository.findById(usuarioAlterPermissaoDTO.getUsuarioAlterado().getId())
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse ID."));

		Login login = loginService.findLoginByUser(usuarioAlterPermissaoDTO.getUsuarioSolicitante());
		Login vo = null;
		if(login.getRoles().contains(ADMINISTRADOR)) {
			Login loginAlterado = new Login();
			login = loginService.findLoginByUser(usuarioAlterPermissaoDTO.getUsuarioAlterado());
			Permissao permissao = permissaoService.findPermissaoByDescription(usuarioAlterPermissaoDTO.getPermissao().getDescription());
			loginAlterado.setPermissoes(Arrays.asList(permissao));
			 vo = loginService.create(loginAlterado);
			
			if(vo == null) {
				return new ResponseEntity<>("Erro ao cadastrar permissão", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>("Permissão Cadastrado com sucesso", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Usuario não tem permissão para cadastrar Permissão", HttpStatus.FORBIDDEN);
		}
		
	}
	
	private UsuarioVO convertToUsuarioVO(Usuario entity) {
		return DozerConverter.parseObject(entity, UsuarioVO.class);
	}
}
