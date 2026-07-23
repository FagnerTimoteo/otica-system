package com.otica.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otica.system.model.Usuario;
import com.otica.system.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	public Usuario save(Usuario usuario) {
		return repository.save(usuario);
	}
	
	public Usuario findById(Long id) {
		return repository.findById(id)
			.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public Usuario updateById(Long id, Usuario usuario) {
		return repository.findById(id).map( usuarioExistente -> {
			usuarioExistente.setNome(usuario.getNome());
			usuarioExistente.setEmail(usuario.getEmail());
			usuarioExistente.setSenha(usuario.getSenha());
			usuarioExistente.setRole(usuario.getRole());
			usuarioExistente.setAtivo(usuario.getAtivo());
			//usuarioExistente.setPedidos(null);
			
			return repository.save(usuarioExistente);
		}).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
	}
}
