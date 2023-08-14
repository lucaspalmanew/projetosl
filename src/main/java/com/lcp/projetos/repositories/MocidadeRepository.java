package com.lcp.projetos.repositories;

import java.util.List;

import com.lcp.projetos.entities.Mocidade;
import com.lcp.projetos.entities.Visita;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface MocidadeRepository extends JpaRepository<Mocidade, Long> {

	List<Mocidade> findByNomeContains(String nomepesquisa);

	List<Mocidade> findDistinctByNomeContainsAndMesNasc(String nome, String mesNasc);

	
}
