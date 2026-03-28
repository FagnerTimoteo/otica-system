package com.otica.system.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.otica.system.model.Pagamento;
import com.otica.system.repository.PagamentoRepository;

public class PagamentoService {
	
	@Autowired
	private PagamentoRepository repository;
	
	public Pagamento save(Pagamento pagamento) {
		return repository.save(pagamento);
	}
	
	public Pagamento findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
}


