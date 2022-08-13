package br.com.pi_adocao_pet_back.domain.dto;

import br.com.pi_adocao_pet_back.domain.entity.Permissao;
import br.com.pi_adocao_pet_back.domain.vo.v1.UsuarioVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioAlterPermissaoDTO {
	UsuarioVO usuarioSolicitante;
	UsuarioVO usuarioAlterado;
	Permissao permissao;
}
