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

import com.avaliacao.attornatus.model.Endereco;
import com.avaliacao.attornatus.service.EnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoResource {
	
	@Autowired
	private EnderecoService resource;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Endereco inserirEndereco(@RequestBody Endereco endereco) {
		return resource.incluir(endereco);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Endereco> findAll(){
		return resource.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Endereco findById(@PathVariable("id") Integer id) {
		return resource.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereco não encontrado"));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteEndereco(@PathVariable ("id") int id) {
		resource.findById(id).map(endereco -> {
				resource.delete(endereco.getId());
				return Void.TYPE;
			}).orElseThrow((() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereco não encontrado")));
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void alterarEndereco(@PathVariable("id") Integer id, @RequestBody Endereco endereco) {
		resource.findById(id).map(enderecoBase -> {
			modelMapper.map(endereco, enderecoBase);
			resource.incluir(enderecoBase);
			return Void.TYPE;
		}).orElseThrow((() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereco não encontrado")));
	}
	
}
