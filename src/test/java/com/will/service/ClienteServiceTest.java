package com.will.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.will.AbstractTests;
import com.will.model.Cliente;

public class ClienteServiceTest extends AbstractTests {

	private static final Logger LOGGER = Logger.getLogger(ClienteServiceTest.class);
	
	@Autowired
	private ClienteService clienteService;
	
	@Test
	@Ignore
	@Transactional(propagation= Propagation.REQUIRES_NEW)
    @Rollback(false)
	public void salvar() throws Exception{
		Cliente cliente = new Cliente();
		
		cliente.setCpf("111.222.333-44");
		cliente.setNome("Will Filho");
		cliente.setEmail("will@email.com");
		cliente.setTelefone("71 9988-7766");
		cliente.setObservacoes("Cliente de teste");
		
		clienteService.salvar(cliente);
		LOGGER.info(cliente);
	}
	
	@Test
	@Ignore
	public void listar(){
		List<Cliente> clientes = clienteService.listar();
		for (Cliente cliente : clientes) {
			LOGGER.info(cliente);
		}
	}
	
}
