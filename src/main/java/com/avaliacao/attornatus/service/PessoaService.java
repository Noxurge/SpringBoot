package com.avaliacao.attornatus.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avaliacao.attornatus.model.Pessoa;
import com.avaliacao.attornatus.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	PessoaRepository service;
	
	public Pessoa incluir(Pessoa pessoa){
		return service.save(pessoa);
	}
	
	public List<Pessoa> findAll(){
		return service.findAll();
	}
	
	public Optional<Pessoa> findById(Integer id){
		return service.findById(id);
	}
	
	public void delete(Integer id) {
		Pessoa pessoaDelete = service.findById(id).get();
		if (pessoaDelete != null) {
			service.delete(pessoaDelete);
		}
	}
}
