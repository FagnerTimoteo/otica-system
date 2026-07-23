package com.otica.system.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.otica.system.model.Usuario;
import com.otica.system.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	private final UsuarioService service;
	
	public UsuarioController(UsuarioService service) {
		this.service = service;
	}
	
	@PostMapping
	public Usuario save(@RequestBody Usuario usuario) {
		return service.save(usuario);
	}
	
	@GetMapping("/{id}")
	public Usuario findById(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@PutMapping("/{id}")
	public Usuario updateById(@PathVariable Long id, @RequestBody Usuario usuario) {
		return service.updateById(id, usuario);
	}
	
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id) {
		service.deleteById(id);
	}
}
