package br.com.pi_adocao_pet_back.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import br.com.pi_adocao_pet_back.domain.vo.v1.UsuarioVO;
import br.com.pi_adocao_pet_back.service.UsuarioService;

@SpringBootTest
@AutoConfigureMockMvc
class UsuarioControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	UsuarioService usuarioService;
	
	@Test
	   void salvarUsuario() throws Exception {
	      UsuarioVO usuario = new UsuarioVO();
	      usuario.setNome("usuario1");
	      usuario.setEmail("usuario01@mail.com");
	      usuario.setTelefone("99211452");
	      usuario.setRg("3132309");
	      usuario.setCpf("12345678909");
	      Mockito.when(usuarioService.inserir(Mockito.any())).thenReturn(usuario);
	      this.mockMvc.perform(post("/api/usuario/v1")
	                      .contentType(MediaType.APPLICATION_JSON)
	                      .content("{\"id\":null,\"nome\":\"usuario1\",\"email\":\"usuario01@mail.com\",\"telefone\":\"99211452\",\"rg\":\"3132309\",\"cpf\":\"12345678909\"}")
	                      .accept(MediaType.APPLICATION_JSON)
	              )
	              .andDo(print())
	              .andExpect(status().isCreated())
	              .andExpect(jsonPath("$.nome").value("usuario1"))
	              .andExpect(jsonPath("$.telefone").isNotEmpty())
	              .andExpect(jsonPath("$.rg").isNotEmpty())
	              .andExpect(jsonPath("$.cpf").isNotEmpty())
	              .andExpect(jsonPath("$.email").isNotEmpty())
	              .andExpect(content().json("{'id':null,nome:'usuario1',email:'usuario01@mail.com',telefone:'99211452',rg:'3132309',cpf:'12345678909'}"));
	   }

}

