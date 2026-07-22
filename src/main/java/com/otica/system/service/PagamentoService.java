package com.otica.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otica.system.model.Pagamento;
import com.otica.system.repository.PagamentoRepository;

@Service
public class PagamentoService {
	
	@Autowired
	private PagamentoRepository repository;
	
	public Pagamento save(Pagamento pagamento) {
		return repository.save(pagamento);
	}
	
	public Pagamento findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public Pagamento updateById(Long id, Pagamento pagamento) {
		return repository.findById(id).map ( pagamentoExistente -> {
			//pagamentoExistente.setPedido(null);
			pagamentoExistente.setTipo(pagamento.getTipo());
			pagamentoExistente.setValor(pagamento.getValor());
			pagamentoExistente.setDataPagamento(pagamento.getDataPagamento());
			//pagamentoExistente.setStatus(null);
			
			return repository.save(pagamentoExistente);
		}).orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
	}
}


