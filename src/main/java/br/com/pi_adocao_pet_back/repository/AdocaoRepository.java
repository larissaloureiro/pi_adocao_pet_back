package br.com.pi_adocao_pet_back.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import br.com.pi_adocao_pet_back.domain.entity.Adocao;


public interface AdocaoRepository extends JpaRepository<Adocao, Long> {
		
		@Query("Select m from Adocao m where m.idUsuario.id= :id")
		Page<Adocao> findAllByIdUsuario(@Param("id") Long id, Pageable pageable);
}



