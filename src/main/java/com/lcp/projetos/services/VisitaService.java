package com.lcp.projetos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcp.projetos.entities.Visita;
import com.lcp.projetos.repositories.VisitaRepository;

@Service
public class VisitaService {
	
	@Autowired
	private VisitaRepository visitaRepository;
	
	public Visita salvarVisita(Visita visita) {
		
		return visitaRepository.save(visita);
		
	}
	
	public List<Visita> listarTodasVisita(){
		
		return visitaRepository.findAll();
		
	}
	
	public List<Visita> listarVisitaPorNomeEMocidade(String nome, String mocidade) {
		
		return visitaRepository.findDistinctByNomeAndMocidade_NomeContains(nome, mocidade.toString());
	}
	
	public List<Visita> listarVisita(String nome, String mocidade) {
		
		if(nome != null || mocidade != null) 
			return listarVisitaPorNomeEMocidade(nome, mocidade);
		else
		return listarTodasVisita();
	}
	
	public Visita obterVisita(Long id) throws Exception{
		
		Optional<Visita> visita = visitaRepository.findById(id);
		

		
		return visita.get();
		
		
	}
	
	public void excluirVisita(Long id) {
		
		visitaRepository.deleteById(id);
		
	}
}
