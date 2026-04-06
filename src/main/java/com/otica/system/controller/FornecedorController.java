package com.otica.system.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.otica.system.model.Fornecedor;
import com.otica.system.service.FornecedorService;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

	private final FornecedorService service;
	
	public FornecedorController(FornecedorService service) {
		this.service = service;
	}
	
	@PostMapping
	public Fornecedor save(@RequestBody Fornecedor fornecedor) {
		return service.save(fornecedor);
	}
	
	@GetMapping("/{id}")
	public Fornecedor findById(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id) {
		service.deleteById(id);
	}
}
