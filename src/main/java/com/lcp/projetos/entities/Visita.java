package com.lcp.projetos.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Visita {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	private String nome;
	
	@Column(length=2000)
	private String modoPreparo;
	
	private String horario;
	

	private int diaVisita;
	
	@ManyToMany
	private List<Mocidade> mocidade;
	
	@ManyToOne
	private Auxiliar auxiliar;

	public Visita() {

	}

	public Long getId() {
		return Id;
	}

	public void setId(Long i) {
		Id = i;
	}

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getModoPreparo() {
		return modoPreparo;
	}

	public void setModoPreparo(String modoPreparo) {
		this.modoPreparo = modoPreparo;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public int getDiaVisita() {
		return diaVisita;
	}

	public void setDiaVisita(int i) {
		this.diaVisita = i;
	}

	public List<Mocidade> getMocidade() {
		return mocidade;
	}

	public void setMocidade(List<Mocidade> string) {
		this.mocidade = string;
	}

	public Auxiliar getAuxiliar() {
		return auxiliar;
	}

	public void setAuxiliar(Auxiliar auxiliar) {
		this.auxiliar = auxiliar;
	}
}
