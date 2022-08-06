package br.com.pi_adocao_pet_back.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.pi_adocao_pet_back.config.SecurityConfig;
import br.com.pi_adocao_pet_back.domain.entity.User;
import br.com.pi_adocao_pet_back.domain.vo.v1.SessaoVo;
import br.com.pi_adocao_pet_back.repository.UserRepository;
import br.com.pi_adocao_pet_back.security.LoginVO;
import br.com.pi_adocao_pet_back.security.jwt.JWTCreator;
import br.com.pi_adocao_pet_back.security.jwt.JWTObject;


@RestController
public class LoginController {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private SecurityConfig securityConfig;
    @Autowired
    private UserRepository repository;

    @PostMapping("/login")
    public SessaoVo logar(@RequestBody LoginVO loginVO){
        User user = repository.findByUsername(loginVO.getUsername());
        if(user!=null) {
            boolean passwordOk =  encoder.matches(loginVO.getPassword(), user.getPassword());
            if (!passwordOk) {
                throw new RuntimeException("Senha inválida para o login: " + loginVO.getUsername());
            }
            //Estamos enviando um objeto Sessão para retornar mais informações do usuário
            SessaoVo sessao = new SessaoVo();
            sessao.setLogin(user.getUsername());

            JWTObject jwtObject = new JWTObject();
            jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
            jwtObject.setExpiration((new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION)));
            jwtObject.setRoles(user.getRoles());
            sessao.setToken(JWTCreator.create(SecurityConfig.PREFIX, SecurityConfig.KEY, jwtObject));
            return sessao;
        }else {
            throw new RuntimeException("Erro ao tentar fazer login");
        }
    }
}

