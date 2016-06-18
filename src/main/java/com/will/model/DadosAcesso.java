package com.will.model;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Embeddable
public class DadosAcesso{

	@JsonInclude(Include.NON_NULL)
	private String usuario;
	
	@JsonInclude(Include.NON_NULL)
	private String senha;
	
	@JsonInclude(Include.NON_NULL)
	@Enumerated(EnumType.STRING)
	private TipoEquipamento tipoEquipamento;
	
	@JsonInclude(Include.NON_NULL)
	private String mac;
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public TipoEquipamento getTipoEquipamento() {
		return tipoEquipamento;
	}

	public void setTipoEquipamento(TipoEquipamento tipoEquipamento) {
		this.tipoEquipamento = tipoEquipamento;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

}
