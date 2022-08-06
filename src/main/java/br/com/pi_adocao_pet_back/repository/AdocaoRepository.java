package br.com.pi_adocao_pet_back.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.pi_adocao_pet_back.domain.entity.Adocao;

@Repository
public interface AdocaoRepository extends JpaRepository<Adocao, Long> {
		
		@Query("Select a from Adocao a where a.usuario.id= :id")
		Page<Adocao> findAllByUsuario(@Param("id") Long id, Pageable pageable);
}



