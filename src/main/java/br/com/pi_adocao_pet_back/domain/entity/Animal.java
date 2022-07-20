package br.com.pi_adocao_pet_back.domain.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_Animal")
public class Animal implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_animal")
	private Long id;

	@NotBlank
	@Size(max = 50)
	@Column(name = "nome")
	private String Nome;

	@NotBlank
	@Size(max = 50)
	@Column(name = "raca")
	private String Raca;

	@NotBlank
	@Size(max = 50)
	@Column(name = "especie")
	private String Especie;

	@NotBlank
	@Size(max = 250)
	@Column(name = "descricao")
	private String Descricao;

	@NotBlank
	@Size(max = 50)
	@Column(name = "porte")
	private String Porte;

	@NotBlank
	@Column(name = "idade")
	private Integer Idade;

	@NotBlank
	@Size(max = 50)
	@Column(name = "sexo")
	private String Sexo;

	@NotBlank
	@Size(max = 50)
	@Column(name = "foto_url")
	private String FotoUrl;

	@NotBlank
	@Column(name = "data_cadastro")
	private Date DataCadastro;

	@NotBlank
	@Column(name = "disponivel")
	private Boolean Disponivel;

	@NotBlank
	@Size(max = 250)
	@Column(name = "inf_adicionais")
	private String InfAdicionais;
}