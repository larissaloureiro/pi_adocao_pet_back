package br.com.pi_adocao_pet_back.security.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import br.com.pi_adocao_pet_back.exception.InvalidJwtAuthenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtProvider {

	@Value("${security.jwt.token.secret-key:secret}")
	private String secretKey = "secret";

	@Value("${security.jwt.token.expire-length:3600000}")
	private long validade = 3600000;

	@Autowired
	private UserDetailsService userDetailsService;

	
	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}
	/*
	 * será feita a captura do username que será carregado assim que houver a
	 * validaçao de usuario e senha do banco
	 * 
	 * criar o token com o tipo de algoritmo passado para criação
	 */
	public String createToken(String username, List<String> roles) {
		Claims claims = Jwts.claims().setSubject(username);
		claims.put("roles", roles);

		Date now = new Date();
		Date tempoExpirar = new Date(now.getTime() + validade);

		return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(tempoExpirar)
				.signWith(SignatureAlgorithm.HS256, secretKey).compact();
	}

	// faz o cara crachá
	public Authentication getAuthentication(String token) {
		UserDetails userDetails =
				this.userDetailsService.loadUserByUsername(getUsername(token));
		return new UsernamePasswordAuthenticationToken(userDetails, 
				"", userDetails.getAuthorities());
	}

	// comportamento igual ao do modelo da pagina jwt

	private String getUsername(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}

	/* vai criar retornar a string de autorização via cabeçalho. O detalhe que ele é
	 * devolvido em texto com a palavra Bearer entao precisamos remover esse texto
	 * atraves de uma substring
	 */

	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

	//vamos criar a validação do nosso token pra saber de expirou ou nao
	
	public boolean validateToken(String token) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey)
					.parseClaimsJws(token);
			if(claims.getBody().getExpiration().before(new Date())) {
				return false;
			}
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			throw new InvalidJwtAuthenticationException ("Token expirado ou inválido");
		}
	}
}