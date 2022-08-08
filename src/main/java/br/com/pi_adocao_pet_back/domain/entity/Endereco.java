package br.com.pi_adocao_pet_back.domain.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Endereco{

	@NotBlank
	@Size(max = 50)
	@Column(name = "logradouro_end_usuario")
	private String logradouro;

	@NotBlank
	@Size(max = 9)
	@Column(name = "cep_end_usuario")
	private String cep;

	@NotBlank
	@Size(max = 25)
	@Column(name = "localidade_end_usuario")
	private String localidade;

	@NotBlank
	@Size(max = 2)
	@Column(name = "uf_end_usuario")
	private String uf;

	@NotBlank
	@Size(max = 9)
	@Column(name = "numero_end_usuario")
	private String numero;

	@NotBlank
	@Size(max = 15)
	@Column(name = "complemento_end_usuario")
	private String complemento;

	@NotBlank
	@Size(max = 45)
	@Column(name = "referencia_end_usuario")
	private String referencia;
}
