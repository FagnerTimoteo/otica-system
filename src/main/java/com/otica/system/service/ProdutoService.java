package com.otica.system.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.otica.system.model.Produto;
import com.otica.system.repository.ProdutoRepository;

public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;
	
	public Produto save(Produto produto) {
		return repository.save(produto);
	}
	
	public Produto findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Produto não encontrado"));
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
}
