package com.will.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.will.model.Cliente;
import com.will.repository.ClienteRepository;
import com.will.services.exceptions.ClienteNaoEncontradoException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Cliente> listar(){
		return clienteRepository.findAll();
	}
	
	public Cliente buscar(Long id){
		Cliente cliente = clienteRepository.findOne(id);
		
		if(cliente == null){
			throw new ClienteNaoEncontradoException("O cliente não pode ser encontrado.");
		}
		
		return cliente;
	}
	
	public Cliente salvar(Cliente cliente){
		cliente.setId(null);
		return clienteRepository.save(cliente);
	}
	
	public void deletetar(Long id){
		try {
			clienteRepository.delete(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ClienteNaoEncontradoException("Cliente não pode ser encontrado");
		}
		
	}
	
	public void atualizar(Cliente cliente){
		isExistente(cliente);
		clienteRepository.save(cliente);
	}
	
	private void isExistente(Cliente cliente){
		buscar(cliente.getId());
	}
	
	public Page<Cliente> listarPaginado(Pageable pageable){
		return clienteRepository.findAll(pageable);
	}
}
