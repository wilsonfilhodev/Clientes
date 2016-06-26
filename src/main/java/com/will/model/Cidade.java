package com.will.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
public class Cidade extends GenericModel<Long> {

	private static final long serialVersionUID = 1L;
	
	@JsonInclude(Include.NON_NULL)
	private String nome;
	
	@JsonInclude(Include.NON_NULL)
	@ManyToOne
	private Estado uf;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getUf() {
		return uf;
	}

	public void setUf(Estado uf) {
		this.uf = uf;
	}

		
	
}

