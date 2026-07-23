package com.otica.system.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.otica.system.model.Receita;
import com.otica.system.service.ReceitaService;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

	private final ReceitaService service;
	
	public ReceitaController(ReceitaService service) {
		this.service = service;
	}
	
	@PostMapping
	public Receita save(@RequestBody Receita receita) {
		return service.save(receita);
	}
	
	@GetMapping("/{id}")
	public Receita findById(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@PutMapping("/{id}")
	public Receita updateById(@PathVariable Long id, @RequestBody Receita receita) {
		return service.updateById(id, receita);
	}
	
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id) {
		service.deleteById(id);
	}
}
