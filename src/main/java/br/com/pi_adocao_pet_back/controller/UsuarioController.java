package br.com.pi_adocao_pet_back.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.pi_adocao_pet_back.domain.dto.UsuarioAlterPermissaoDTO;
import br.com.pi_adocao_pet_back.domain.dto.UsuarioLoginDTO;
import br.com.pi_adocao_pet_back.domain.entity.Login;
import br.com.pi_adocao_pet_back.domain.vo.v1.UsuarioVO;
import br.com.pi_adocao_pet_back.security.LoginVO;
import br.com.pi_adocao_pet_back.service.LoginService;
import br.com.pi_adocao_pet_back.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Usuario Endpoint")
@RestController
@RequestMapping("/api/usuario/v1")
public class UsuarioController {
	@Autowired
	UsuarioService service;

	@Autowired
	LoginService loginService;
	
	@RequestMapping(method=RequestMethod.GET, produces={"application/json","application/xml"})
	@Operation(summary = "Listar todas os usuarios")
	@ResponseStatus(value=HttpStatus.OK)
	public ResponseEntity<CollectionModel<UsuarioVO>> findAll(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction
			){
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "nome"));
		Page<UsuarioVO> usuariosVO = service.buscarTodos(pageable);
		usuariosVO.stream().forEach(u -> u.add(linkTo(methodOn(UsuarioController.class).findById(u.getId())).withSelfRel()));
		return ResponseEntity.ok(CollectionModel.of(usuariosVO));
	}
	
	@GetMapping(value="/{id}", produces={"application/json","application/xml"})
	@ResponseStatus(value=HttpStatus.OK)
	public UsuarioVO findById(@PathVariable("id") Long id) {
		UsuarioVO usuarioVO = service.buscarPorId(id);
		usuarioVO.add(linkTo(methodOn(UsuarioController.class).findById(id)).withSelfRel());
		return usuarioVO;
	}
	
	@GetMapping(value="username/{username}", produces={"application/json","application/xml"})
	@ResponseStatus(value=HttpStatus.OK)
	public UsuarioVO findByUsername(@PathVariable("username") String username) {
		UsuarioVO usuarioVO = service.buscarPorUsername(username);
		usuarioVO.add(linkTo(methodOn(UsuarioController.class).findByUsername(username)).withSelfRel());
		return usuarioVO;
	}
	
	@PostMapping(consumes = {"application/json","application/xml"}, produces={"application/json","application/xml"})
	@ResponseStatus(value=HttpStatus.CREATED)
	public UsuarioVO create(@Valid @RequestBody UsuarioLoginDTO usuariologinDTO) {
		UsuarioVO usuarioVO = service.inserir(usuariologinDTO);
		//UsuarioVO usuarioVO = service.inserir(usuario);
		usuarioVO.add(linkTo(methodOn(UsuarioController.class).findById(usuarioVO.getId())).withSelfRel());
		return usuarioVO;
	}
	
	@PutMapping(consumes = {"application/json","application/xml"}, produces={"application/json","application/xml"})
	@ResponseStatus(value=HttpStatus.OK)
	public UsuarioVO update(@Valid @RequestBody UsuarioVO usuario) {
		UsuarioVO usuarioVO = service.atualizar(usuario);
		usuarioVO.add(linkTo(methodOn(UsuarioController.class).findById(usuarioVO.getId())).withSelfRel());
		return usuarioVO;
	}
	
	@DeleteMapping(value="/{id}", produces={"application/json","application/xml"})
	@ResponseStatus(value=HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}
	
	@PatchMapping(consumes = {"application/json","application/xml"}, produces={"application/json","application/xml"})
	@ResponseStatus(value=HttpStatus.OK)
	public ResponseEntity<String> addPermissionInUser(@Valid @RequestBody UsuarioAlterPermissaoDTO usuarioAlterPermissaoDTO) {
		return service.atualizarPermissao(usuarioAlterPermissaoDTO);
	}

}
