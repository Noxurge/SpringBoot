package com.avaliacao.attornatus.resources;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.avaliacao.attornatus.model.Pessoa;
import com.avaliacao.attornatus.service.PessoaService;

@RestController
@RequestMapping("/pessoa")
public class PessoaResource {
	
	@Autowired
	private PessoaService resource;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pessoa inserirPessoa(@RequestBody Pessoa pessoa) {
		return resource.incluir(pessoa);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Pessoa> findAll() {
		return resource.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Pessoa findById(@PathVariable("id") Integer id) {
		return resource.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePessoa(@PathVariable("id") Integer id) {
		resource.findById(id).map(pessoa -> {
				resource.delete(pessoa.getId());
				return Void.TYPE;
			}).orElseThrow((() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada")));
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void alterarPessoa(@PathVariable("id") Integer id, @RequestBody Pessoa pessoa) {
		resource.findById(id).map(pessoaBase -> {
			modelMapper.map(pessoa, pessoaBase);
			resource.incluir(pessoaBase);
			return Void.TYPE;
		}).orElseThrow((() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada")));
	}
	
}
