package com.lcp.projetos.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class Mocidade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Nome não pode ser vazio.")
	private String nome;

	private int idade;
	private int dataNasc;
	private String mesNasc;
	private String nomeResp;
	private String teleResp;

	
	//@OneToMany(mappedBy = "ingredientes")
	//private List<Receita> receita;


	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public int getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(int dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getMesNasc() {
		return mesNasc;
	}

	public void setMesNasc(String mesNasc) {
		this.mesNasc = mesNasc;
	}

	public String getNomeResp() {
		return nomeResp;
	}

	public void setNomeResp(String nomeResp) {
		this.nomeResp = nomeResp;
	}

	public String getTeleResp() {
		return teleResp;
	}

	public void setTeleResp(String teleResp) {
		this.teleResp = teleResp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return nome;
	}

	
}
