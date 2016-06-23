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

import com.will.model.Cliente;
import com.will.model.Endereco;
import com.will.service.ClienteService;
import com.will.utils.ControllerPath;

@RestController
@RequestMapping(path = ControllerPath.CLIENTE_PATH)
public class ClienteController
{

   @Autowired
   private ClienteService clienteService;
   
   
   //------------------ LISTAR TODOS -----------------------
   
   @RequestMapping(method = RequestMethod.GET)
   public ResponseEntity<List<Cliente>> list(@RequestParam(defaultValue = "0", required = false) int page){
	 List<Cliente> clientes = clienteService.listar();
     return ResponseEntity.status(HttpStatus.OK).body(clientes);
   }
   
 //------------------ LISTAR TODOS PAGINADO -----------------------
   
   @RequestMapping(params = {"page", "size"}, method = RequestMethod.GET)
   public ResponseEntity<Page<Cliente>> listarPaginado(@RequestParam(defaultValue = "0", required = false) int page, 
		   @RequestParam(defaultValue = "10", required = false) int size){
	 Pageable pageable = new PageRequest(page, size);
	 Page<Cliente> clientes = clienteService.listarPaginado(pageable);
     return ResponseEntity.status(HttpStatus.OK).body(clientes);
   }
   
 //------------------- BUSCAR POR ID -------------------------
   
   @RequestMapping(method = RequestMethod.GET, value = "/{id}")
   public ResponseEntity<Cliente> load(@PathVariable("id") long id){ 
	  Cliente cliente = clienteService.buscar(id);
      return ResponseEntity.status(HttpStatus.OK).body(cliente);
   }
   
   //-------------------- SALVAR --------------------------

   @RequestMapping(method = RequestMethod.POST)
   public ResponseEntity<Void> save(@RequestBody Cliente cliente){
	  clienteService.salvar(cliente);
	  URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
      return ResponseEntity.created(uri).build();
   }

   //------------------- ATUALIZAR ------------------------
   
   @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
   public ResponseEntity<Void> update(@PathVariable("id") long id, @RequestBody Cliente cliente){
	  cliente.setId(id);
	  clienteService.atualizar(cliente);
      return ResponseEntity.status(HttpStatus.OK).build();
   }

   //------------------- REMOVER ------------------------
   
   @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
   public ResponseEntity<Void> remove(@PathVariable("id") long id){
	  clienteService.deletetar(id);	
      return ResponseEntity.noContent().build();
   }
   
   //------------------- ADICIONAR ENDEREÇO ------------------------
   
   @RequestMapping(value = "/{id}/enderecos",method = RequestMethod.POST)
   public ResponseEntity<Void> adicionarEndereco(@PathVariable("id") Long clienteId, @RequestBody Endereco endereco){
	  clienteService.adicionarEndereco(clienteId, endereco);
	  URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
      return ResponseEntity.created(uri).build();
   }
   
//------------------- LISTAR ENDEREÇO ------------------------
   
   @RequestMapping(value = "/{id}/enderecos",method = RequestMethod.GET)
   public ResponseEntity<List<Endereco>> listarEnderecos(@PathVariable("id") Long clienteId){
	  List<Endereco> enderecos = clienteService.listarEnderecos(clienteId);
      return ResponseEntity.status(HttpStatus.OK).body(enderecos);
   }
   
}

