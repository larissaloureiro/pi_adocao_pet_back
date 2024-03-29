package br.com.pi_adocao_pet_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.pi_adocao_pet_back.domain.entity.Login;
import br.com.pi_adocao_pet_back.domain.vo.v1.UsuarioVO;
import br.com.pi_adocao_pet_back.repository.LoginRepository;

@Service
public class LoginService implements UserDetailsService {

	@Autowired
	LoginRepository repository;
	
	public Login create(Login login) {
		return repository.save(login);
	}

	public Login findLoginByUser(UsuarioVO usuarioVO) {
		var id = usuarioVO.getId();
		return repository.findByUsuarioId(id);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = repository.findByUsername(username);
		if (user != null) {
			return user;
		} else {
			throw new UsernameNotFoundException("Username" + username + "não localizado!");
		}
	}
	
	public Login findByUsername(String username) throws UsernameNotFoundException {
		var user = repository.findByUsername(username);
		if (user != null) {
			return user;
		} else {
			throw new UsernameNotFoundException("Username" + username + "não localizado!");
		}
	}
}
