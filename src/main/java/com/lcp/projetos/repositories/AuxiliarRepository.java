package com.lcp.projetos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.lcp.projetos.entities.Auxiliar;

@Repository
public interface AuxiliarRepository extends JpaRepository<Auxiliar, Long> {


	List<Auxiliar> findByNomeContains(String nome);
}
