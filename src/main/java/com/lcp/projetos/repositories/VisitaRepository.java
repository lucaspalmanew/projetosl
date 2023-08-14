package com.lcp.projetos.repositories;



import java.util.List;

import com.lcp.projetos.entities.Visita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitaRepository extends JpaRepository<Visita, Long>{

	List<Visita> findByNomeContains(String nome);
	
	List<Visita> findDistinctByNomeAndMocidade_NomeContains(String nome, String mocidade);
}
