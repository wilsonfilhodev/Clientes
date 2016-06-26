package com.will.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.will.model.Cliente;
import com.will.model.Endereco;
import com.will.repository.ClienteRepository;
import com.will.repository.EnderecoRepository;
import com.will.services.exceptions.RegistroNaoEncontradoException;

@Service
public class EnderecoService extends GenericService<Endereco, Long> {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Endereco salvar(Long clienteId, Endereco endereco) {
		Cliente cliente = buscarCliente(clienteId);
		endereco.setCliente(cliente);
		
		return enderecoRepository.save(endereco);
	}
	
	public List<Endereco> listar(Long clienteId) {
		Cliente cliente = buscarCliente(clienteId);
		
		return cliente.getEndereco();
	}
	
	public Endereco buscar(Long idEndereco){
		Endereco endereco = enderecoRepository.findOne(idEndereco);
		
		if(endereco == null){
			throw new RegistroNaoEncontradoException("O registro não pode ser encontrado.");
		}
		
		return endereco;
	}
	
	public void deletar(Long idEndereco){
		try {
			enderecoRepository.delete(idEndereco);
		} catch (EmptyResultDataAccessException e) {
			throw new RegistroNaoEncontradoException("Endereco não pode ser encontrado");
		}
		
	}
	
	public void atualizar(Endereco endereco){
		isExistente(endereco);
		enderecoRepository.save(endereco);
	}
	
	private void isExistente(Endereco endereco){
		buscar(endereco.getId());
	}
	
	public Cliente buscarCliente(Long id){
		Cliente cliente = clienteRepository.findOne(id);
		
		if(cliente == null){
			throw new RegistroNaoEncontradoException("O cliente não pode ser encontrado.");
		}
		
		return cliente;
	}
}
