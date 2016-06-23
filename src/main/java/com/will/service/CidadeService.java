package com.will.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.will.model.Cidade;
import com.will.repository.CidadeRepository;
import com.will.services.exceptions.RegistroNaoEncontradoException;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	public List<Cidade> listar(){
		return cidadeRepository.findAll();
	}
	
	public Cidade buscar(Long id){
		Cidade cidade = cidadeRepository.findOne(id);
		
		if(cidade == null){
			throw new RegistroNaoEncontradoException("O cidade não pode ser encontrado.");
		}
		
		return cidade;
	}
	
	public Cidade salvar(Cidade cidade){
		cidade.setId(null);
		return cidadeRepository.save(cidade);
	}
	
	public void deletetar(Long id){
		try {
			cidadeRepository.delete(id);
		} catch (EmptyResultDataAccessException e) {
			throw new RegistroNaoEncontradoException("A cidade não pode ser encontrado");
		}
		
	}
	
	public void atualizar(Cidade cidade){
		isExistente(cidade);
		cidadeRepository.save(cidade);
	}
	
	private void isExistente(Cidade cidade){
		buscar(cidade.getId());
	}
	
	public Page<Cidade> listarPaginado(Pageable pageable){
		return cidadeRepository.findAll(pageable);
	}
	
}
