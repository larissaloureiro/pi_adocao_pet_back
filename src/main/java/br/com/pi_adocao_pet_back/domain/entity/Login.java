package br.com.pi_adocao_pet_back.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_login")
public class Login implements UserDetails, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_login")
	private Long id;

	@OneToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@Column(name = "username_login", unique = true) // não permite repetição do valor do objeto
	private String username;

	@Column(name = "senha_login")
	private String password;
//	
//	@ElementCollection()
//	@CollectionTable(name = "tab_user_roles", joinColumns = @JoinColumn(name = "id_login"))
//	@Column(name = "role_id")
//	private List<String> roles = new ArrayList<>();

	@Column(name = "account_non_expired")
	private Boolean accountNonExpired;

	@Column(name = "account_non_locked")
	private Boolean accountNonLocked;

	@Column(name = "credentials_non_expired")
	private Boolean credentialsNonExpired;

	@Column(name = "enable")
	private Boolean enable;

	//@ManyToMany(fetch = FetchType.EAGER)
	@ManyToMany()
	@JoinTable(name = "permissao_login", joinColumns = { @JoinColumn(name = "id_login") }, inverseJoinColumns = {
			@JoinColumn(name = "id_permissao") })
	private List<Permissao> permissoes;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		//return this.permissoes;
		return null;

	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return this.credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.enable;
	}


	public List<String> getRoles() {
		List<String> roles = new ArrayList<>();
		
		for (Permissao permissao : this.permissoes) {
			roles.add(permissao.getDescription());
		}
		return roles;
	}

}
