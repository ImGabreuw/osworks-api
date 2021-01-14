package com.algaworks.osworks.api.controller;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osworks.domain.model.Cliente;
import com.algaworks.osworks.domain.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository repository;

	@GetMapping
	public List<Cliente> listar() {
//		return repository.findByNome("João da Silva); Busca pelo nome informado
//		return repository.findByNomeContaining("Si"); Busca pelo nome com base na String informada
		return repository
				.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long id) {
		Function<Cliente, ResponseEntity<Cliente>> foundedCliente = cliente -> ResponseEntity
				.ok()
				.body(cliente);
		ResponseEntity<Cliente> notFoundedCliente = ResponseEntity
				.notFound()
				.build();
		
		return repository
				.findById(id)
				.map(foundedCliente)
				.orElse(notFoundedCliente);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Cliente adicionar(@RequestBody Cliente cliente) {
		return repository.save(cliente);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
		if (repository.existsById(id)) {
			return ResponseEntity
					.notFound()
					.build();
		}
		
		cliente.setId(id);
		cliente = repository.save(cliente);
		
		return ResponseEntity
				.ok()
				.body(cliente);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		if (!repository.existsById(id)) {
			return ResponseEntity
					.notFound()
					.build();
		}
		
		repository.deleteById(id);
		
		return ResponseEntity
				.noContent()
				.build();
	}

}
