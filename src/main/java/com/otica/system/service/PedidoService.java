package com.otica.system.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.otica.system.model.Pedido;
import com.otica.system.repository.PedidoRepository;

public class PedidoService {
	
	@Autowired
	private PedidoRepository repository;
	
	public Pedido save(Pedido pedido) {
		return repository.save(pedido);
	}
	
	public Pedido findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
}
