package com.will.model;

import javax.persistence.Entity;

@Entity
public class Estado extends GenericModel<Long> {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	
	private String sigla;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	
	
}

