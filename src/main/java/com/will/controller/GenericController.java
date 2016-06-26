package com.will.controller;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.will.model.GenericModel;
import com.will.services.exceptions.RegistroNaoEncontradoException;
import com.will.utils.ControllerMap;

public abstract class GenericController<T extends GenericModel<ID>, ID extends Serializable> implements ControllerMap {

	private final Logger LOGGER = Logger.getLogger(this.getClass());

	@Autowired
	protected JpaRepository<T, ID> genericRepository;

	@RequestMapping(method = RequestMethod.GET)
	public List<T> listar() {
		this.LOGGER.debug("Buscando todos os registros.");

		return this.genericRepository.findAll();
	}
	
	public T buscar(ID id){
		T entityObject = genericRepository.findOne(id);
		
		if(entityObject == null){
			throw new RegistroNaoEncontradoException("O registro não pode ser encontrado.");
		}
		
		return entityObject;
	}

	@RequestMapping(method = RequestMethod.POST)
	public T salvar(@RequestBody T entityObject) {
		this.LOGGER.debug(String.format("Salvando a entidade [%s].", entityObject));

		entityObject.setId(null);

		return this.genericRepository.save(entityObject);
	}


	@RequestMapping(method = RequestMethod.DELETE)
	public void deletar(@RequestBody T entityObject) {
		this.LOGGER.debug(String.format("Buscando para deletar a entidade [%s].", entityObject));

		try {
			this.genericRepository.delete(entityObject);
		} catch (EmptyResultDataAccessException e) {
			throw new RegistroNaoEncontradoException("O registro não pode ser encontrado");
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public void atualizar(@RequestBody T entityObject){
		
		this.LOGGER.debug(String.format("Buscando para atualizar a entidade [%s].", entityObject));

		if (entityObject == null) {
			this.LOGGER.error("Entidade nula.");
			return;
		}

		if (entityObject.getId() == null) {
			this.LOGGER.error("ID da entidade é nulo.");
			return;
		}
		
		isExistente(entityObject);
		
		this.genericRepository.save(entityObject);
	}
	
	
	@RequestMapping(params = { "page", "size" }, method = RequestMethod.GET)
	public Page<T> listarPaginado(@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "10", required = false) int size) {
		
		Pageable pageable = new PageRequest(page, size);

		return this.genericRepository.findAll(pageable);
	}
	
	private void isExistente(T entityObject){
		buscar(entityObject.getId());
	}
	

}
