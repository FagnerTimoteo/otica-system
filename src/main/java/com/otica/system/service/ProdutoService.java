package com.otica.system.service;

import com.otica.system.DTO.request.ProdutoRequestDTO;
import com.otica.system.model.Fornecedor;
import com.otica.system.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.otica.system.model.Produto;
import com.otica.system.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	public Produto save(ProdutoRequestDTO dto) {

		Fornecedor fornecedor = fornecedorRepository.findById(dto.getFornecedorId())
			.orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));

		Produto produto = new Produto();
		produto.setFornecedor(fornecedor);
		produto.setNome(dto.getNome());
		produto.setPreco(dto.getPreco());
		produto.setQuantidadeEstoque(dto.getQuantidadeEstoque());
		produto.setTipo(dto.getTipo());

		return produtoRepository.save(produto);
	}
	
	public Produto findById(Long id) {
		return produtoRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Produto não encontrado"));
	}
	
	public void deleteById(Long id) {
		produtoRepository.deleteById(id);
	}

	public Produto updateById(Long id, ProdutoRequestDTO dto) {
		Fornecedor fornecedor = fornecedorRepository.findById(dto.getFornecedorId())
			.orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));

		return produtoRepository.findById(id).map(produtoExistente -> {
			produtoExistente.setNome(dto.getNome());
			produtoExistente.setPreco(dto.getPreco());
			produtoExistente.setQuantidadeEstoque(dto.getQuantidadeEstoque());
			produtoExistente.setTipo(dto.getTipo());
			produtoExistente.setFornecedor(fornecedor);
			
			return produtoRepository.save(produtoExistente);
		}).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
	}
}
