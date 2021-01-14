package com.algaworks.osworks.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.osworks.domain.exception.DomainException;
import com.algaworks.osworks.domain.model.Cliente;
import com.algaworks.osworks.domain.repository.ClienteRepository;

@Service
public class CadastroClienteService {
	
	@Autowired
	private ClienteRepository repository;

	public Cliente salvar(Cliente cliente) {
		Cliente clienteExistente = repository.findByEmail(cliente.getEmail());
		
		if (clienteExistente != null && !clienteExistente.equals(cliente)) {
			throw new DomainException("Já existe um cliente cadastrado com este e-mail.");
		}
		
		return repository.save(cliente);
	}
	
	public void excluir(Long id) {
		repository.deleteById(id);
	}
}
