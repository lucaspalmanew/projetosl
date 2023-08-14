package com.lcp.projetos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcp.projetos.entities.Mocidade;
import com.lcp.projetos.repositories.MocidadeRepository;

@Service
public class MocidadeService {
	
	@Autowired
	private MocidadeRepository mocidadeRepository;
	
	public Mocidade salvarMocidade(Mocidade mocidade) {
		
		return mocidadeRepository.save(mocidade);
		
	}
	
	public List<Mocidade> listarTodosMocidade(){
		
		return mocidadeRepository.findAll();
		
	}
	
	public List<Mocidade> listarMocidadePorNomeemesNasc(String nomepesquisa, String mesNasc) {
		
		return mocidadeRepository.findDistinctByNomeContainsAndMesNasc(nomepesquisa, mesNasc.toString());
	}
	
	public List<Mocidade> listarMocidade(String nomepesquisa, String mesNasc) {
		
		if(nomepesquisa != null || mesNasc != null) 
			return listarMocidadePorNomeemesNasc(nomepesquisa, mesNasc);
		else
		return listarTodosMocidade();
	}
	
	public Mocidade obterMocidade(Long id) throws Exception {
		
		Optional<Mocidade> mocidade = mocidadeRepository.findById(id);
		
	
		
		return mocidade.get();
		
	}

	public void excluirMocidade(Long id) {
		mocidadeRepository.deleteById(id);
	}

}
