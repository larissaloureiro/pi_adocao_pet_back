package br.com.pi_adocao_pet_back.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class JwtTokenFilter extends GenericFilterBean {

	
	//injetamos a classe provider
	@Autowired
	private JwtProvider jwtProvider;
	
	
	public JwtTokenFilter(JwtProvider jwtProvider) {
	
		this.jwtProvider = jwtProvider;
	}


	//o filtro serve para filtrar  se há um token devolvido pelo cabeçalho 
	// trabalhado no nosso resolverToken
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String token =jwtProvider.resolveToken((HttpServletRequest) request);
		
		if (token != null && jwtProvider.validateToken(token)) {
			Authentication auth = jwtProvider.getAuthentication(token);
			if (auth != null) {
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		chain.doFilter(request, response);
		
	}

}