package com.will.model;

public enum TipoEquipamento {

	COMPUTADOR("Computador"),
	NOTEBOOK("Notebook"), 
	ROTEADOR("Roteador");
	
	private String descricao;
	
	TipoEquipamento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
