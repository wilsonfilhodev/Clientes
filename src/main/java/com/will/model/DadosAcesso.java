package com.will.model;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
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
	

}
