package com.otica.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otica.system.model.Produto;
import com.otica.system.repository.ProdutoRepository;

@Service
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

	public Produto updateById(Long id, Produto produto) {
		return repository.findById(id).map( produtoExistente -> {
			produtoExistente.setNome(produto.getNome());
			produtoExistente.setPreco(produto.getPreco());
			produtoExistente.setQuantidadeEstoque(produto.getQuantidadeEstoque());
			produtoExistente.setTipo(produto.getTipo());
			//produtoExistente.setFornecedor(null);
			//produtoExistente.setItens(null);
			
			return repository.save(produtoExistente);
		}).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
	}
}
