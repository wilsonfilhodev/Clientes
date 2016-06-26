package com.will.model;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
public class Estado extends GenericModel<Long> {

	private static final long serialVersionUID = 1L;
	
	@JsonInclude(Include.NON_NULL)
	private String nome;
	
	@JsonInclude(Include.NON_NULL)
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

