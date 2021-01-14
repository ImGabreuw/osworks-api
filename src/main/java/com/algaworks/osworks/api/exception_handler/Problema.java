package com.algaworks.osworks.api.exception_handler;

import java.time.LocalDateTime;

public class Problema {
	
	private Integer status;
	private LocalDateTime dataHora;
	private String titulo;
	
	public Problema() {
	}

	public Problema(Integer status, LocalDateTime dataHora, String titulo) {
		this.status = status;
		this.dataHora = dataHora;
		this.titulo = titulo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
