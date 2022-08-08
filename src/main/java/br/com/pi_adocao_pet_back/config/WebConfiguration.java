package br.com.pi_adocao_pet_back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import br.com.pi_adocao_pet_back.security.jwt.JwtTokenFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebConfiguration extends WebSecurityConfigurerAdapter {
    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    private static final String[] SWAGGER_WHITELIST = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"
    };
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.cors().and().csrf().disable()
                .addFilterAfter(new JwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(SWAGGER_WHITELIST).permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/api/**").permitAll();
//                .antMatchers(HttpMethod.POST,"/users").permitAll()
//                .antMatchers(HttpMethod.GET,"/users").hasAnyRole("USERS","MANAGERS")
//                .antMatchers("/managers").hasAnyRole("MANAGERS")
//                .anyRequest().authenticated()
//                .and()
//               .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
  
    public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") //mapear todos
		.allowedMethods("GET", "PUT", "POST", "DELETE", "PATH", "OPTIONS", "TRACE", "HEAD", "CONNECT");
	}	

}