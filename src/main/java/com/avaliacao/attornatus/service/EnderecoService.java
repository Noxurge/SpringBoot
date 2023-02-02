package com.avaliacao.attornatus.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avaliacao.attornatus.model.Endereco;
import com.avaliacao.attornatus.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	EnderecoRepository service;
	
	public Endereco incluir(Endereco endereco) {
		return service.save(endereco);
	}
	
	public Endereco alterar(Endereco endereco) {
		return service.save(endereco);
	}
	
	public List<Endereco> findAll(){
		return service.findAll();
	}
	
	public Optional<Endereco> findById(Integer id) {
		return service.findById(id);
	}
	
	public void delete(Integer id) {
		Endereco enderecoDelete = service.findById(id).get();
		if (enderecoDelete != null) {
			service.delete(enderecoDelete);
		}
	}
}
