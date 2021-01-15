package com.algaworks.osworks.api.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osworks.api.model.Comentario;
import com.algaworks.osworks.api.model.ComentarioDTO;
import com.algaworks.osworks.api.model.ComentarioInputDTO;
import com.algaworks.osworks.domain.service.GestaoOrdemServicoService;

@RestController
@RequestMapping("/ordens-servico/{ordemServicoId}/comentario")
public class ComentarioController {

	@Autowired
	private GestaoOrdemServicoService service;
	
	@Autowired
	private ModelMapper modelMapper;
	
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
	
}
