package br.com.pi_adocao_pet_back.domain.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_Adocao")
public class Adocao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_adocao")
	private Long id;

	@ManyToOne 
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@OneToOne
	@JoinColumn(name = "id_animal")
	private Animal animal;

	//@NotBlank
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Status status;

	@NotNull
	@Column(name = "data_solicitacao")
	private Date dataSolicitacao;

	@NotNull
	@Column(name = "data_atualizacao")
	private Date dataAtualizacao;

}

