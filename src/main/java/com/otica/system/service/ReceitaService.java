package com.otica.system.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.otica.system.model.Receita;
import com.otica.system.repository.ReceitaRepository;

public class ReceitaService {
	
	@Autowired
	private ReceitaRepository repository;
	
	public Receita save(Receita receita) {
		return repository.save(receita);
	}
	
	public Receita findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Receita não encontrada"));
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
}
