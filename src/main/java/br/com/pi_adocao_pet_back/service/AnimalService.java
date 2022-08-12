package br.com.pi_adocao_pet_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.pi_adocao_pet_back.adapter.DozerConverter;
import br.com.pi_adocao_pet_back.domain.entity.Animal;
import br.com.pi_adocao_pet_back.domain.vo.v1.AnimalVO;
import br.com.pi_adocao_pet_back.exception.ResourceNotFoundException;
import br.com.pi_adocao_pet_back.repository.AnimalRepository;

@Service
public class AnimalService {
	
	@Autowired
	AnimalRepository repository;

	private AnimalVO convertToAnimalVO(Animal entity) {
		return DozerConverter.parseObject(entity, AnimalVO.class);
	}

	private Animal convertToAnimal(AnimalVO vo) {
		return DozerConverter.parseObject(vo, Animal.class);
	}

	public AnimalVO inserir(AnimalVO animal) {
		var entity = convertToAnimal(animal);
		var vo = convertToAnimalVO(repository.save(entity));
		return vo;
	}

	public void delete(Long id) {
		Animal entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id."));
		repository.delete(entity);
	}

	public AnimalVO atualizar(AnimalVO animal) {
		var entity = repository.findById(animal.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id."));
		entity.setNome(animal.getNome());
		entity.setRaca(animal.getRaca());
		entity.setEspecie(animal.getEspecie());
		entity.setDescricao(animal.getDescricao());
		entity.setPorte(animal.getPorte());
		entity.setIdade(animal.getIdade());
		entity.setSexo(animal.getSexo());
		entity.setFotoBase64(animal.getFotoBase64());
		entity.setDataCadastro(animal.getDataCadastro());
		entity.setDisponivel(animal.getDisponivel());
		entity.setInfAdicionais(animal.getInfAdicionais());
		var vo = convertToAnimalVO(repository.save(entity));
		return vo;
	}

	public AnimalVO buscarPorId(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id."));
		return convertToAnimalVO(entity);
	}

	public Page<AnimalVO> buscarTodos(Pageable pageable) {
		var page = repository.findAll(pageable);
		return page.map(this::convertToAnimalVO);
	}

	public Page<AnimalVO> buscarTodosPorDisponivel(Boolean disponivel, Pageable pageable){
		var page = repository.findAllByDisponivel(disponivel, pageable);
		return page.map(this::convertToAnimalVO);
	}

	public Page<AnimalVO> buscarTodosPorEspecie(String especie, Pageable pageable){
		var page = repository.findAllByEspecie(especie, pageable);
		return page.map(this::convertToAnimalVO);
	}	
}