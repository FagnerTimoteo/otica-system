package com.otica.system.service;

import com.otica.system.DTO.request.PagamentoRequestDTO;
import com.otica.system.model.Pedido;
import com.otica.system.model.StatusPagamento;
import com.otica.system.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otica.system.model.Pagamento;
import com.otica.system.repository.PagamentoRepository;

import java.time.LocalDateTime;

@Service
public class PagamentoService {
	
	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	public Pagamento save(PagamentoRequestDTO dto) {
		Pagamento pagamento = new Pagamento();

		Pedido pedido = pedidoRepository.findById(dto.getPedidoId()).orElseThrow(
				() -> new RuntimeException("Pedido não encontrado"));

		pagamento.setPedido(pedido);
		pagamento.setTipo(dto.getTipo());
		pagamento.setValor(dto.getValor());
		pagamento.setDataPagamento(LocalDateTime.now());
		pagamento.setStatus(StatusPagamento.PENDENTE);

		return pagamentoRepository.save(pagamento);
	}
	
	public Pagamento findById(Long id) {
		return pagamentoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
	}
	
	public void deleteById(Long id) {
		pagamentoRepository.deleteById(id);
	}

	public Pagamento updateById(Long id, Pagamento pagamento) {
		return pagamentoRepository.findById(id).map (pagamentoExistente -> {
			//pagamentoExistente.setPedido(null);
			pagamentoExistente.setTipo(pagamento.getTipo());
			pagamentoExistente.setValor(pagamento.getValor());
			pagamentoExistente.setDataPagamento(pagamento.getDataPagamento());
			//pagamentoExistente.setStatus(null);
			
			return pagamentoRepository.save(pagamentoExistente);
		}).orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
	}
}


