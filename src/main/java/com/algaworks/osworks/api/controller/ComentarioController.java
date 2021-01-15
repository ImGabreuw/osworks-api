package com.algaworks.osworks.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osworks.api.model.Comentario;
import com.algaworks.osworks.api.model.ComentarioDTO;
import com.algaworks.osworks.api.model.ComentarioInputDTO;
import com.algaworks.osworks.domain.exception.EntitadeNaoEncontradaException;
import com.algaworks.osworks.domain.model.OrdemServico;
import com.algaworks.osworks.domain.repository.OrdemServicoRepository;
import com.algaworks.osworks.domain.service.GestaoOrdemServicoService;

@RestController
@RequestMapping("/ordens-servico/{ordemServicoId}/comentario")
public class ComentarioController {
	
	@Autowired
	private OrdemServicoRepository repository;

	@Autowired
	private GestaoOrdemServicoService service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public List<ComentarioDTO> listar(@PathVariable Long ordemServicoId) {
		OrdemServico ordemServico = repository
				.findById(ordemServicoId)
				.orElseThrow(() -> new EntitadeNaoEncontradaException("Ordem de serviço não encontrada."));
		
		return toCollectionDTO(ordemServico.getComentarios());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ComentarioDTO adicionar(@PathVariable Long ordemServicoId, @PathVariable ComentarioInputDTO comentarioInputDTO) {
		Comentario comentario = service
				.adicionarComentario(
						ordemServicoId, 
						comentarioInputDTO.getDescricao());
		
		return toDTO(comentario);
	}
	
	private ComentarioDTO toDTO(Comentario comentario) {
		return modelMapper.map(comentario, ComentarioDTO.class);
	}
	
	private List<ComentarioDTO> toCollectionDTO(List<Comentario> comentarios) {
		return comentarios
				.stream()
				.map(this::toDTO)
				.collect(Collectors.toList());
	}
}
