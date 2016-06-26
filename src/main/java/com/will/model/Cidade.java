package com.will.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Cidade extends GenericModel<Long> {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	
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

