package com.algaworks.osworks.core;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Anotação de configuração de @Bean
public class ModelMapperConfig {
	
	@Bean // Configura / inicializa um objeto
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
