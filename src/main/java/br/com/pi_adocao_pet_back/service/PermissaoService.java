package br.com.pi_adocao_pet_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pi_adocao_pet_back.domain.entity.Permissao;
import br.com.pi_adocao_pet_back.repository.PermissaoRepository;

@Service
public class PermissaoService {
	@Autowired
	PermissaoRepository repository;
	
	public Permissao findPermissaoByDescription(String description) {
		return repository.findByDescription(description);
	}
		
}
