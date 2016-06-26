package com.will.controller;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.will.model.GenericModel;
import com.will.service.GenericService;
import com.will.utils.ControllerMap;

public abstract class GenericController<T extends GenericModel<ID>, ID extends Serializable> implements ControllerMap {

	@Autowired
	protected GenericService<T, ID> genericService;
	

	//------------------ LISTAR TODOS -----------------------
	   
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<T>> list(@RequestParam(defaultValue = "0", required = false) int page) {

		List<T> entityObjects = genericService.listar();

		return ResponseEntity.status(HttpStatus.OK).body(entityObjects);
	}
	   
	// ------------------ LISTAR TODOS PAGINADO -----------------------

	@RequestMapping(params = { "page", "size" }, method = RequestMethod.GET)
	public ResponseEntity<Page<T>> listarPaginado(@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "10", required = false) int size) {
		Pageable pageable = new PageRequest(page, size);
		Page<T> entityObjects = genericService.listarPaginado(pageable);
		return ResponseEntity.status(HttpStatus.OK).body(entityObjects);
	}
	
	// ------------------- BUSCAR POR ID -------------------------

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<T> load(@PathVariable("id") ID id) {
		T entityObject = genericService.buscar(id);
		return ResponseEntity.status(HttpStatus.OK).body(entityObject);
	}
	
	//-------------------- SALVAR --------------------------

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody T entityObject) {
		genericService.salvar(entityObject);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entityObject.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	//------------------- ATUALIZAR ------------------------

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable("id") ID id, @RequestBody T entityObject) {
		entityObject.setId(id);
		genericService.atualizar(entityObject);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	  
	// ------------------- REMOVER ------------------------

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Void> remove(@PathVariable("id") ID id) {
		genericService.deletar(id);
		return ResponseEntity.noContent().build();
	}

}