package com.otica.system.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.otica.system.model.Cliente;
import com.otica.system.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	private final ClienteService service;
	
		public ClienteController(ClienteService service) {
		this.service = service;
	}
	
	@PostMapping
	public Cliente save(@RequestBody Cliente cliente) {
		return service.save(cliente);
	}
	
	@GetMapping("/{id}")
	public Cliente findById(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@PutMapping("/{id}")
	public Cliente updateById(@PathVariable Long id, @RequestBody Cliente cliente) {
		return service.updateById(id, cliente);
	}
	
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id) {
		service.deleteById(id);
	}
}
