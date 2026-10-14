package com.otica.system.service;

import com.otica.system.DTO.request.ClienteRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otica.system.model.Cliente;
import com.otica.system.repository.ClienteRepository;

import java.time.LocalDateTime;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	public Cliente save(ClienteRequestDTO dto) {

		Cliente cliente = new Cliente();
		cliente.setNome(dto.getNome());
		cliente.setCpf(dto.getCpf());
		cliente.setEmail(dto.getEmail());
		cliente.setTelefone(dto.getTelefone());
		cliente.setDataCadastro(LocalDateTime.now());

		return repository.save(cliente);
	}
	
	public Cliente findById(Long id) {
		return repository.findById(id)
			.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public Cliente updateById(Long id, Cliente cliente) {
		return repository.findById(id).map( clienteExistente -> {
			clienteExistente.setNome(cliente.getNome());	
			clienteExistente.setCpf(cliente.getCpf());
			clienteExistente.setTelefone(cliente.getTelefone());
			//clienteExistente.setPedidos(null);
			//clienteExistente.setReceitas(null);
			clienteExistente.setEmail(cliente.getEmail());
			clienteExistente.setDataCadastro(cliente.getDataCadastro());
			
			return repository.save(clienteExistente);
		}).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
	}
}
