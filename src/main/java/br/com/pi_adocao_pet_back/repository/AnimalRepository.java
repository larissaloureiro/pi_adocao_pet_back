package br.com.pi_adocao_pet_back.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.pi_adocao_pet_back.domain.entity.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    @Query("Select m from Animal m where m.disponivel = :disponivel")
	Page<Animal> findAllByDisponivel(@Param("disponivel") Boolean disponivel, Pageable pageable);

    @Query("Select m from Animal m where m.especie = :especie")
	Page<Animal> findAllByEspecie(@Param("especie") String especie, Pageable pageable);

}