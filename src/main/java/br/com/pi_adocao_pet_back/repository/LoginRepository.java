package br.com.pi_adocao_pet_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.pi_adocao_pet_back.domain.entity.Login;

@Repository
public interface LoginRepository extends JpaRepository <Login, Long>{
	
	@Query("SELECT u FROM Login u WHERE u.username = :username")
	Login findByUsername(@Param("username") String username);

	@Query("SELECT l FROM Login l WHERE l.usuario.id = :id")
	Login findByUsuarioId(@Param("id") Long id);
}
