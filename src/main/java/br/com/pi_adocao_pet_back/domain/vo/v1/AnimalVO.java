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
public class AnimalVO extends RepresentationModel<AnimalVO> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Mapping("id")
	private Long key;
    private String Nome;
    private String Raca;
	private String Especie;
	private String Descricao;
	private String Porte;
	private Integer Idade;
	private String Sexo;
	private String FotoUrl;
	private Date DataCadastro;
	private Boolean Disponivel;
	private String InfAdicionais;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(
			key, Nome, Raca, Especie, Descricao, Porte, Idade,
			Sexo, FotoUrl, DataCadastro, Disponivel, InfAdicionais
		);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnimalVO other = (AnimalVO) obj;
		return Objects.equals(key, other.key)
                && Objects.equals(Nome, other.Nome)
                && Objects.equals(Raca, other.Raca)
				&& Objects.equals(Especie, other.Especie)
				&& Objects.equals(Descricao, other.Descricao)
				&& Objects.equals(Porte, other.Porte)
				&& Objects.equals(Idade, other.Idade)
				&& Objects.equals(Sexo, other.Sexo)
				&& Objects.equals(FotoUrl, other.FotoUrl)
				&& Objects.equals(DataCadastro, other.DataCadastro)
				&& Objects.equals(Disponivel, other.Disponivel)
				&& Objects.equals(InfAdicionais, other.InfAdicionais);
	}
}