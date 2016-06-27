package com.will.model;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cliente extends GenericModel<Long>{

	private static final long serialVersionUID = 1L;
	
	@JsonInclude(Include.NON_NULL)
	private String nome;
	
	@JsonInclude(Include.NON_NULL)
	private String cpf;
	
	@JsonInclude(Include.NON_NULL)
	private String email;
	
	@JsonInclude(Include.NON_NULL)
	private String telefone;
	
	@JsonInclude(Include.NON_NULL)
	private String observacoes;
	
	@JsonInclude(Include.NON_NULL)
	@OneToMany(mappedBy = "cliente")
	private List<Endereco> endereco;
	
	@JsonInclude(Include.NON_NULL)
	@Embedded
	private DadosAcesso dadosAcesso;
	

}
