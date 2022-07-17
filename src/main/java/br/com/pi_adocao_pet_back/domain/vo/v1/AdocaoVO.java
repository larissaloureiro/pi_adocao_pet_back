package br.com.pi_adocao_pet_back.domain.vo.v1;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.github.dozermapper.core.Mapping;
import br.com.pi_adocao_pet_back.domain.entity.Status;

public class AdocaoVO extends RepresentationModel<AdocaoVO> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Mapping("id")
	private Long key;
	private UsuarioVO usuario;
	private AnimalVO animal;
	private Date dataSolicitacao;
	private Date dataAtualizacao;
	
	private Status status;
	
	
	public AdocaoVO(Long key, UsuarioVO usuario, AnimalVO animal, Status status, Date dataSolicitacao,
			Date dataAtualizacao) {
		super();
		this.key = key;
		this.usuario = usuario;
		this.animal = animal;
		this.status = status;
		this.dataSolicitacao = dataSolicitacao;
		this.dataAtualizacao = dataAtualizacao;
	}
	public Long getKey() {
		return key;
	}
	public void setKey(Long key) {
		this.key = key;
	}
	public UsuarioVO getUsuario() {
		return usuario;
	}
	public void setTutor(UsuarioVO usuario) {
		this.usuario = usuario;
	}
	public AnimalVO getAnimal() {
		return animal;
	}
	public void setAnimal(AnimalVO animal) {
		this.animal = animal;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Date getDataSolicitacao() {
		return dataSolicitacao;
	}
	public void setDataSolicitacao(Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}
	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(animal, dataAtualizacao, dataSolicitacao, key, status, usuario);
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
		AdocaoVO other = (AdocaoVO) obj;
		return Objects.equals(animal, other.animal) && Objects.equals(dataAtualizacao, other.dataAtualizacao)
				&& Objects.equals(dataSolicitacao, other.dataSolicitacao) && Objects.equals(key, other.key)
				&& status == other.status && Objects.equals(usuario, other.usuario);
	}
}
	
	