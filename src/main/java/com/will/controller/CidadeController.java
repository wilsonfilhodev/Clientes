package com.will.controller;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.will.model.Cidade;
import com.will.service.CidadeService;
import com.will.utils.ControllerPath;

@RestController
@RequestMapping(path = ControllerPath.CIDADE_PATH)
public class CidadeController
{

   @Autowired
   private CidadeService cidadeService;
   
   
   //------------------ LISTAR TODOS -----------------------
   
   @RequestMapping(method = RequestMethod.GET)
   public ResponseEntity<List<Cidade>> list(@RequestParam(defaultValue = "0", required = false) int page){
	 List<Cidade> cidades = cidadeService.listar();
     return ResponseEntity.status(HttpStatus.OK).body(cidades);
   }
   
 //------------------ LISTAR TODOS PAGINADO -----------------------
   
   @RequestMapping(params = {"page", "size"}, method = RequestMethod.GET)
   public ResponseEntity<Page<Cidade>> listarPaginado(@RequestParam(defaultValue = "0", required = false) int page, 
		   @RequestParam(defaultValue = "10", required = false) int size){
	 Pageable pageable = new PageRequest(page, size);
	 Page<Cidade> cidades = cidadeService.listarPaginado(pageable);
     return ResponseEntity.status(HttpStatus.OK).body(cidades);
   }
   
 //------------------- BUSCAR POR ID -------------------------
   
   @RequestMapping(method = RequestMethod.GET, value = "/{id}")
   public ResponseEntity<Cidade> load(@PathVariable("id") long id){ 
	   Cidade cidade = cidadeService.buscar(id);
      return ResponseEntity.status(HttpStatus.OK).body(cidade);
   }
   
   //-------------------- SALVAR --------------------------

   @RequestMapping(method = RequestMethod.POST)
   public ResponseEntity<Void> save(@RequestBody Cidade cidade){
	  cidadeService.salvar(cidade);
	  URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cidade.getId()).toUri();
      return ResponseEntity.created(uri).build();
   }

   //------------------- ATUALIZAR ------------------------
   
   @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
   public ResponseEntity<Void> update(@PathVariable("id") long id, @RequestBody Cidade cidade){
	  cidade.setId(id);
	  cidadeService.atualizar(cidade);
      return ResponseEntity.status(HttpStatus.OK).build();
   }

   //------------------- REMOVER ------------------------
   
   @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
   public ResponseEntity<Void> remove(@PathVariable("id") long id){
	  cidadeService.deletetar(id);	
      return ResponseEntity.noContent().build();
   }
   
}

