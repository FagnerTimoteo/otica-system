package com.otica.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otica.system.model.Cliente;
import com.otica.system.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	public Cliente save(Cliente cliente) {
		return repository.save(cliente);
	}
	
	public Cliente findById(Long id) {
		return repository.findById(id)
			.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
}
