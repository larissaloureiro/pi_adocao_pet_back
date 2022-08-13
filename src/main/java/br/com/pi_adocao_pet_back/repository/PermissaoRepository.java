package br.com.pi_adocao_pet_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pi_adocao_pet_back.domain.entity.Permissao;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long>{

	Permissao findByDescription(String description);

}
