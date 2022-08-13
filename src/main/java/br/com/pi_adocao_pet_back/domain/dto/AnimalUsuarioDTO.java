package br.com.pi_adocao_pet_back.domain.dto;

import br.com.pi_adocao_pet_back.domain.vo.v1.AnimalVO;
import br.com.pi_adocao_pet_back.domain.vo.v1.UsuarioVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalUsuarioDTO {

	private AnimalVO animalVO;
	private UsuarioVO usuarioVO;
	
}
