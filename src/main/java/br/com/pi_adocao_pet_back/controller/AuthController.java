package br.com.pi_adocao_pet_back.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pi_adocao_pet_back.domain.dto.LoginDTO;
import br.com.pi_adocao_pet_back.domain.entity.Login;
import br.com.pi_adocao_pet_back.repository.LoginRepository;
import br.com.pi_adocao_pet_back.security.LoginVO;
//import io.swagger.v3.oas.annotations.tags.Tag;
import br.com.pi_adocao_pet_back.security.jwt.JwtProvider;
import net.bytebuddy.asm.Advice.Return;

//@Tag(name="Authentication Endpoint")
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired	
	AuthenticationManager authenticationManager;

	//@Autowired
	//JwtProvider tokenProvider;

	@Autowired
	LoginRepository repository;
	
	@PostMapping(value = "/signin", produces = { "application/json", "application/xml" }, 
			consumes = { "application/json",	"application/xml" })
	public LoginDTO signin(@RequestBody LoginVO loginVO) {
		
		//try {
			var username = loginVO.getUsername();
			var password = loginVO.getPassword();
			
//			authenticationManager.authenticate(
//					new UsernamePasswordAuthenticationToken(username, password));
//			
			var user = repository.findByUsername(username);
			LoginDTO loginDTO;
			//var token = "";
			
			if (user !=null) {
				loginDTO = new LoginDTO();
				loginDTO.setPermissoes(user.getRoles());
				loginDTO.setUsername(user.getUsername());
				//token = tokenProvider.createToken(username, user.getRoles());
				if(user.getPassword().equals(password)) {

					return loginDTO;//ResponseEntity.ok(user,"Login realizado com sucesso");

				}else {
					return loginDTO =null;//new ResponseEntity<>("Usuario ou senha invalidos", HttpStatus.FORBIDDEN);

				}
			}else {

				return loginDTO = null;//new ResponseEntity<>("Usuario ou senha invalidos", HttpStatus.FORBIDDEN);
			}
//			
//			Map<Object, Object> model = new HashMap<>();
//			model.put("username", username);
//			//model.put("token", token);
//			
//			return ok(model);
//		} catch (AuthenticationException e) {
//			throw new BadCredentialsException("Usuário ou senha inválidos");
//		}
		
	}

}
