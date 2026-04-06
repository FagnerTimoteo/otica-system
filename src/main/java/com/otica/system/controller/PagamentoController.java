package com.otica.system.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.otica.system.model.Pagamento;
import com.otica.system.service.PagamentoService;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {
	
	private final PagamentoService service;
	
	public PagamentoController(PagamentoService service) {
		this.service = service;
	}
	
	@PostMapping
	public Pagamento save(@RequestBody Pagamento pagamento) {
		return service.save(pagamento);
	}
	
	@GetMapping("/{id}")
	public Pagamento findById(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id) {
		service.deleteById(id);
	}
}
