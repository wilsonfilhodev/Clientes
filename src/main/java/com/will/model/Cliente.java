package com.will.model;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
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
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public List<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}

	public DadosAcesso getDadosAcesso() {
		return dadosAcesso;
	}

	public void setDadosAcesso(DadosAcesso dadosAcesso) {
		this.dadosAcesso = dadosAcesso;
	}

}
