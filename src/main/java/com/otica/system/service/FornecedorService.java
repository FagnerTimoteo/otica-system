package com.otica.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otica.system.model.Fornecedor;
import com.otica.system.repository.FornecedorRepository;

@Service
public class FornecedorService {

	@Autowired
	private FornecedorRepository repository;
	
	public Fornecedor save(Fornecedor fornecedor) {
		return repository.save(fornecedor);
	}
	
	public Fornecedor findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public Fornecedor updateById(Long id, Fornecedor fornecedor) {
		return repository.findById(id).map( fornecedorExistente -> {
			fornecedorExistente.setNome(fornecedor.getNome());
			fornecedorExistente.setTelefone(fornecedor.getTelefone());
			fornecedorExistente.setEmail(fornecedor.getEmail());
			//fornecedorExistente.setProdutos(null);
			
			return repository.save(fornecedorExistente);
		}).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

	}
}
