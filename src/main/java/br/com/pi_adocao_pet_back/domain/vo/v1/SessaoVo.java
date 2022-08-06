package br.com.pi_adocao_pet_back.domain.vo.v1;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

public class SessaoVo extends RepresentationModel<SessaoVo> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String login;
    private String token;
    
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
    
    
}
