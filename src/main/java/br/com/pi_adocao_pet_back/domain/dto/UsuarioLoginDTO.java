package br.com.pi_adocao_pet_back.domain.dto;

import br.com.pi_adocao_pet_back.domain.vo.v1.UsuarioVO;
import br.com.pi_adocao_pet_back.security.LoginVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioLoginDTO {
	
	private UsuarioVO usuarioVO;
	private LoginVO loginVO;

}
