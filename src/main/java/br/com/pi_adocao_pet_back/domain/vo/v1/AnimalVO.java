package br.com.pi_adocao_pet_back.domain.vo.v1;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.github.dozermapper.core.Mapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalVO extends RepresentationModel<AnimalVO> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Mapping("id")
	private Long key;
	private String nome;
	private String raca;
	private String especie;
	private String descricao;
	private String porte;
	private Integer idade;
	private String sexo;
	private String fotoUrl;
	private Date dataCadastro;
	private Boolean disponivel;
	private String infAdicionais;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnimalVO other = (AnimalVO) obj;
		return Objects.equals(dataCadastro, other.dataCadastro) && Objects.equals(descricao, other.descricao)
				&& Objects.equals(disponivel, other.disponivel) && Objects.equals(especie, other.especie)
				&& Objects.equals(fotoUrl, other.fotoUrl) && Objects.equals(idade, other.idade)
				&& Objects.equals(infAdicionais, other.infAdicionais) && Objects.equals(key, other.key)
				&& Objects.equals(nome, other.nome) && Objects.equals(porte, other.porte)
				&& Objects.equals(raca, other.raca) && Objects.equals(sexo, other.sexo);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(dataCadastro, descricao, disponivel, especie, fotoUrl, idade,
				infAdicionais, key, nome, porte, raca, sexo);
		return result;
	}

}