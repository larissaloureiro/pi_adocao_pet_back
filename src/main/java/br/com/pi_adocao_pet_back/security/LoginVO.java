package br.com.pi_adocao_pet_back.security;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

public class LoginVO extends RepresentationModel<LoginVO> implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;
    private String password;
	
	public LoginVO() {
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(password, username);
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
		LoginVO other = (LoginVO) obj;
		return Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}
	
	
	
}