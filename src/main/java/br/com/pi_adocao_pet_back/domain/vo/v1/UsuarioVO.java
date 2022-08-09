package br.com.pi_adocao_pet_back.domain.vo.v1;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import com.github.dozermapper.core.Mapping;

import br.com.pi_adocao_pet_back.domain.entity.Endereco;
import br.com.pi_adocao_pet_back.domain.entity.Tipo;

public class UsuarioVO extends RepresentationModel<UsuarioVO> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Mapping("id")
	private Long key;

	private String nome;
	private String telefone;
	private String email;
	private String rg;
	private String cpf;
	private Date dataNascimento;
	private Date dataCadastro;
	
	private Endereco endereco;

	private Tipo tipo;

	public UsuarioVO(Long key, String nome, String telefone, String email, String rg, String cpf, Date dataNascimento,
			Date dataCadastro, Endereco endereco, Tipo tipo) {
		super();
		this.key = key;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.rg = rg;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.dataCadastro = dataCadastro;
		this.endereco = endereco;
		this.tipo = tipo;
	}

	
	public UsuarioVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ Objects.hash(cpf, dataCadastro, dataNascimento, email, endereco, key, nome, rg, telefone, tipo);
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
		UsuarioVO other = (UsuarioVO) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(dataCadastro, other.dataCadastro)
				&& Objects.equals(dataNascimento, other.dataNascimento) && Objects.equals(email, other.email)
				&& Objects.equals(endereco, other.endereco) && Objects.equals(key, other.key)
				&& Objects.equals(nome, other.nome) && Objects.equals(rg, other.rg)
				&& Objects.equals(telefone, other.telefone) && tipo == other.tipo;
	}

}
