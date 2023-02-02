package com.avaliacao.attornatus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avaliacao.attornatus.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{
	
}
