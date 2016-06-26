package com.will.service;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.will.model.GenericModel;
import com.will.services.exceptions.RegistroNaoEncontradoException;

public abstract class GenericService<T extends GenericModel<ID>, ID extends Serializable> {

	private final Logger LOGGER = Logger.getLogger(this.getClass());

	@Autowired
	protected JpaRepository<T, ID> genericRepository;

	
	//---------------- LISTAR TODOS -------------------
	
	public List<T> listar(){
		
		this.LOGGER.debug("Listando todos os registros.");
		
		return this.genericRepository.findAll();
	}
	
	//------------ LISTAR TODOS PAGINADO -------------
	
	public Page<T> listarPaginado(Pageable pageable) {

		this.LOGGER.debug("Listando paginado todos os registros.");

		return this.genericRepository.findAll(pageable);
	}
	
	//------------------ BUSCAR -----------------------
	
	public T buscar(ID id){
		
		this.LOGGER.debug(String.format("Buscando registro de ID [%s].", id));
		
		T entityObject = genericRepository.findOne(id);
		
		if(entityObject == null){
			throw new RegistroNaoEncontradoException("O registro não pode ser encontrado.");
		}
		
		return entityObject;
	}
	
	//------------------ SALVAR -----------------------
	
	public T salvar(T entityObject){
		
		this.LOGGER.debug(String.format("Salvando a entidade [%s].", entityObject));
		
		entityObject.setId(null);
		
		return this.genericRepository.save(entityObject);
	}

	//------------------ DELETAR -----------------------
	
	public void deletar(ID id){
		
		try {
			this.LOGGER.debug(String.format("Deletando a entidade de ID [%s].", id));
			
			this.genericRepository.delete(id);
			
		} catch (EmptyResultDataAccessException e) {
			
			throw new RegistroNaoEncontradoException("Entidade não pode ser deletada");
		}
		
	}

	//------------------ ATUALIZAR -----------------------
	
	public void atualizar(T entityObject) {

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
	
	
	//------------------ VERIFICAR SE EXISTE ---------------
	
	private void isExistente(T entityObject){
		
		this.LOGGER.debug(String.format("Verificando se existe a entidade [%s].", entityObject));
		
		buscar(entityObject.getId());
	}
	

}
