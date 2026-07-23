package com.otica.system.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.otica.system.model.Produto;
import com.otica.system.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	private final ProdutoService service;
	
	public ProdutoController(ProdutoService service) {
		this.service = service;
	}
	
	@PostMapping
	public Produto save(@RequestBody Produto produto) {
		return service.save(produto);
	}
	
	@GetMapping("/{id}")
	public Produto findById(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@PutMapping("/{id}")
	public Produto updateById(@PathVariable Long id, @RequestBody Produto produto) {
		return service.updateById(id, produto);
	}
	
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id) {
		service.deleteById(id);
	}
}
