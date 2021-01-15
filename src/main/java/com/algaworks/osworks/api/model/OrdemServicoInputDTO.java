package com.algaworks.osworks.api.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OrdemServicoInputDTO {
	
	@NotNull
	private ClienteIdInputDTO clienteId;
	@NotBlank
	private String descricao;
	@NotNull
	private BigDecimal preco;
	
	public ClienteIdInputDTO getClienteId() {
		return clienteId;
	}
	public void setClienteId(ClienteIdInputDTO clienteId) {
		this.clienteId = clienteId;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}	
}
