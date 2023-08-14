package com.lcp.projetos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcp.projetos.entities.Auxiliar;
import com.lcp.projetos.repositories.AuxiliarRepository;

@Service
public class AuxiliarService {
	
	@Autowired
	private AuxiliarRepository auxiliarRepository;
	
	public Auxiliar salvarAuxiliar(Auxiliar auxiliar) {
		
		return auxiliarRepository.save(auxiliar);
		
	}
	
	public List<Auxiliar> listarTodasAuxiliar(){
		
		return auxiliarRepository.findAll();
		
	}
	
	public List<Auxiliar> listarAuxiliarPorNome(String nome) {
		
		return auxiliarRepository.findByNomeContains(nome);
	}
	
	public List<Auxiliar> listarAuxiliar(String nome) {
		
		if(nome != null) {
			return listarAuxiliarPorNome(nome);
		}
		return listarTodasAuxiliar();
	}

	
	public Auxiliar obterAuxiliar(Long id) throws Exception{
		
		Optional<Auxiliar> auxiliar = auxiliarRepository.findById(id);

		return auxiliar.get();
		
		
	}
	
	public void excluirAuxiliar(Long id) {
		
		auxiliarRepository.deleteById(id);
		
	}

}
