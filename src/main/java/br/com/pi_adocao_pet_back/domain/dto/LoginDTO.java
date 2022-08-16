package br.com.pi_adocao_pet_back.domain.dto;

import java.util.List;

import br.com.pi_adocao_pet_back.domain.entity.Permissao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
	private String username;
	private List<String> permissoes;
}
