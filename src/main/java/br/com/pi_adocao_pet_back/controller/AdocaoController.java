package br.com.pi_adocao_pet_back.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import br.com.pi_adocao_pet_back.domain.dto.AdocaoUsuarioDTO;
import br.com.pi_adocao_pet_back.domain.vo.v1.AdocaoVO;
import br.com.pi_adocao_pet_back.service.AdocaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name="Adocao Endpoint")
@RestController
@RequestMapping("/api/adocao/v1")
public class AdocaoController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdocaoController.class);

	@Autowired
	AdocaoService service;

	@RequestMapping(method = RequestMethod.GET, produces = { "application/json", "application/xml" })
	@Operation(summary = "Listar todas as adoçoes")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<CollectionModel<AdocaoVO>> findAll(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "id"));
		Page<AdocaoVO> adocaoVO = service.buscarTodos(pageable);
		adocaoVO.stream()
				.forEach(f -> f.add(linkTo(methodOn(AdocaoController.class).findById(f.getKey())).withSelfRel()));
		return ResponseEntity.ok(CollectionModel.of(adocaoVO));
	}
	
	@GetMapping(value = "/buscausuario/{idUsuario}", produces = { "application/json", "application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<CollectionModel<AdocaoVO>> findAllByIdUsuario(
			@PathVariable("idUsuario") Long idUsuario,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "id"));
		Page<AdocaoVO> adocaoVO = service.buscarTodosPorUsuario(idUsuario, pageable);
		adocaoVO.stream()
				.forEach(a -> a.add(linkTo(methodOn(AdocaoController.class).findById(a.getKey())).withSelfRel()));
		return ResponseEntity.ok(CollectionModel.of(adocaoVO));
	}
	
	
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public AdocaoVO findById(@PathVariable("id") Long id) {
		AdocaoVO adocaoVO = service.buscarPorId(id);
		adocaoVO.add(linkTo(methodOn(AdocaoController.class).findById(id)).withSelfRel());
		return adocaoVO;
	}

	@PostMapping(consumes = { "application/json", "application/xml" }, produces = { "application/json",
			"application/xml" })
	@ResponseStatus(value = HttpStatus.CREATED)
	public AdocaoVO create(@Valid @RequestBody AdocaoVO adocao) {
		AdocaoVO adocaoVO = service.inserir(adocao);
		adocaoVO.add(linkTo(methodOn(AdocaoController.class).findById(adocaoVO.getKey())).withSelfRel());
		logger.info("Status " + adocaoVO.getStatus());
		return adocaoVO;
	}

	@PatchMapping(consumes = { "application/json", "application/xml" }, produces = { "application/json",
			"application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<String> update(@Valid @RequestBody AdocaoUsuarioDTO adocaousuarioDTO) {
		//AdocaoVO adocaoVO = service.atualizar(adocaousuarioDTO);
		//adocaoVO.add(linkTo(methodOn(AdocaoController.class).findById(adocaoVO.getKey())).withSelfRel());
		return service.atualizar(adocaousuarioDTO);
	}

	@DeleteMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}

}