package com.algaworks.osworks.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osworks.api.model.OrdemServicoDTO;
import com.algaworks.osworks.domain.model.OrdemServico;
import com.algaworks.osworks.domain.repository.OrdemServicoRepository;
import com.algaworks.osworks.domain.service.GestaoOrdemServicoService;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

	@Autowired
	private GestaoOrdemServicoService service;
	@Autowired
	private OrdemServicoRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrdemServicoDTO criar(@Valid @RequestBody OrdemServico ordemServico) {
		return toDTO(service.criar(ordemServico));
	}
	
	public List<OrdemServicoDTO> listar() {
		return toCollectionDTO(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrdemServicoDTO> buscar(@PathVariable Long id) {
		Optional<OrdemServico> ordemServicoOptional = repository.findById(id);
		
		if (ordemServicoOptional.isPresent()) {
			OrdemServicoDTO dto = toDTO(ordemServicoOptional.get());
			
			return ResponseEntity
					.ok(dto);
		}
		
		return ResponseEntity
				.notFound()
				.build();
	}
	
	private OrdemServicoDTO toDTO(OrdemServico ordemServico) {
		return modelMapper.map(ordemServico, OrdemServicoDTO.class);
	}
	
	private List<OrdemServicoDTO> toCollectionDTO(List<OrdemServico> ordemServicos) {
		return ordemServicos
				.stream()
				.map(this::toDTO)
				.collect(Collectors.toList());
				
	}
	
}
