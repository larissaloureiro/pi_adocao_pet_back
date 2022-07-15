package br.com.pi_adocao_pet_back.domain.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_Usuario")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Auto-increment
	@Column(name="id_usuario")
	private Long id;
	
	@NotBlank
	@Size(max = 45)
	@Column(name = "nome_usuario")
	private String nome;

	@NotBlank
	@Size(max = 20)
	@Column(name = "telefone_usuario")
	private String telefone;

	@NotBlank
	@Email
	@Size(max = 255)
	@Column(name = "email_usuario")
	private String email;

	@NotBlank
	@Size(max = 9)
	@Column(name = "rg_usuario")
	private String rg;

	@NotBlank
	@Size(max = 11)
	@Column(name = "cpf_usuario")
	private String cpf;
	
	@NotBlank
	@Column(name = "data_nascimento_usuario")
	private Date dataNascimento;

	@NotBlank
	@Column(name = "data_cadastro_usuario")
	private Date dataCadastro;
	
	// Endereco
	@NotBlank
	@Size(max = 50)
	@Column(name = "logradouro_end_usuario")
	private String logradouro;

	@NotBlank
	@Size(max = 9)
	@Column(name = "cep_end_usuario")
	private String cep;
	
	@NotBlank
	@Size(max = 9)
	@Column(name = "localidade_end_usuario")
	private String localidade;
	
	@NotBlank
	@Size(max = 9)
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
	//Fim do Endereco
	
	@NotBlank
	@Size(max = 15)
	@Column(name = "tipo_usuario")
	private Tipo tipo;
	
	public Boolean validaCpf() {
		if (this.cpf.length() != 11) {
			return false;
		}

		String numeros = this.cpf.substring(0, 9);
		String digitos = this.cpf.substring(9);

		// Calculando primeiro dígito verificador
		int soma = 0;
		for (int i = 10; i > 1; i--) {
			int numeroAtual = numeros.charAt(10 - i) - '0'; // Transforma char em inteiro
			soma += numeroAtual * i;
		}

		int resultado = (soma % 11) < 2 ? 0 : 11 - (soma % 11);
		if (resultado != (digitos.charAt(0) - '0')) {
			return false;
		}

		// Calculando segundo dígito verificador
		soma = 0;
		numeros = this.cpf.substring(0, 10);
		for (int k = 11; k > 1; k--) {
			int numeroAtual = numeros.charAt(11 - k) - '0'; // Transforma char em inteiro
			soma += numeroAtual * k;
		}

		resultado = (soma % 11) < 2 ? 0 : 11 - (soma % 11);
		if (resultado != (digitos.charAt(1) - '0')) {
			return false;
		}

		return true;
	}
	
	public Boolean possuiIdadeMinima() {
		Integer idade = this.calculaIdade(this.dataNascimento);
		if (idade >= 18) {
			return true;
		}
		return false;
	}
	
	public Integer calculaIdade(Date dataNascimento) {		
		
		Calendar dataNascimentoCalendar = Calendar.getInstance();
		dataNascimentoCalendar.setTime(dataNascimento);
		Integer anoNascimento = dataNascimentoCalendar.get(Calendar.YEAR);
		Integer mesNascimento = dataNascimentoCalendar.get(Calendar.MONTH);
		Integer diaNascimento = dataNascimentoCalendar.get(Calendar.DAY_OF_MONTH);

		Calendar dataAtual = Calendar.getInstance();
		Integer anoAtual = dataAtual.get(Calendar.YEAR);
		Integer mesAtual = dataAtual.get(Calendar.MONTH);
		Integer diaAtual = dataAtual.get(Calendar.DAY_OF_MONTH);
		
		Integer idade = anoAtual - anoNascimento;
		if (mesAtual < mesNascimento || (mesAtual == mesNascimento && diaAtual < diaNascimento)) {
			idade--;
		}
		
		return idade;
	}
	

}
