package com.will.model;

import javax.persistence.Entity;

@Entity
public class Cidade extends GenericModel<Long> {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	
	private String uf;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
	
	
}

