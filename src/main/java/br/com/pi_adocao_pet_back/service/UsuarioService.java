package br.com.pi_adocao_pet_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.pi_adocao_pet_back.adapter.DozerConverter;
import br.com.pi_adocao_pet_back.domain.entity.Usuario;
import br.com.pi_adocao_pet_back.domain.vo.v1.UsuarioVO;
import br.com.pi_adocao_pet_back.exception.IllegalArgumentException;
import br.com.pi_adocao_pet_back.exception.ResourceNotFoundException;
import br.com.pi_adocao_pet_back.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	UsuarioRepository repository;

	public UsuarioVO inserir(UsuarioVO usuario) {
		var entity = DozerConverter.parseObject(usuario, Usuario.class);

		if (!entity.validaCpf()) {
			throw new IllegalArgumentException("CPF Inválido.");
		}

		if (!entity.possuiIdadeMinima()) {
			throw new IllegalArgumentException("Não possui a idade mínima.");
		}

		var vo = DozerConverter.parseObject(repository.save(entity), UsuarioVO.class);
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
		var entity = repository.findById(usuario.getKey())
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

	private UsuarioVO convertToUsuarioVO(Usuario entity) {
		return DozerConverter.parseObject(entity, UsuarioVO.class);
	}
}
