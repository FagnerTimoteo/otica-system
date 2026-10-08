package com.otica.system.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.datatype.jdk8.WrappedIOException;
import com.otica.system.model.*;
import com.otica.system.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otica.system.repository.PedidoRepository;
import com.otica.system.repository.ProdutoRepository;

@Service
public class PedidoService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
    private ProdutoRepository produtoRepository;
	
	public Pedido save(Pedido pedido) {
		Cliente cliente = clienteRepository.findById(pedido.getCliente().getId())
				.orElseThrow(() -> new RuntimeException("Cliente não existe"));

		pedido.setCliente(cliente);
		pedido.setDataPedido(LocalDateTime.now());
		pedido.setStatus(StatusPedido.ABERTO);
		pedido.setValorTotal(BigDecimal.ZERO);

		return pedidoRepository.save(pedido);
	}
	
	public Pedido findById(Long id) {
		return pedidoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
	}
	
	public void deleteById(Long id) {
		pedidoRepository.deleteById(id);
	}
	
	public String saveItemPedido(ItemPedido pedido) {
		return "Salvo";
	}
	
	public Pedido adicionarItem(Long pedidoId, Long produtoId, Integer qtd) {
		
		Pedido pedido = pedidoRepository.findById(pedidoId)
				.orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
		
		Produto produto = produtoRepository.findById(produtoId)
				.orElseThrow(() -> new RuntimeException("Produto não encontrado"));

		if(qtd == null || qtd <= 0) {
			throw new RuntimeException("A quantidade deve ser maior que zero");
		}
		
		if(produto.getQuantidadeEstoque() < qtd) {
			throw new RuntimeException("Estoque insuficiente");
		}
		
		ItemPedido item = new ItemPedido();
		
		item.setPedido(pedido);
		item.setProduto(produto);
		item.setQuantidade(qtd);
		item.setPrecoUnitario(produto.getPreco());
		
		pedido.getItens().add(item);
		
		recalcularTotal(pedido);
		
		return pedidoRepository.save(pedido);
	}

	private void recalcularTotal(Pedido pedido) {

		BigDecimal total = pedido.getItens()
			.stream()
			.map(item -> item.getPrecoUnitario()
				.multiply(
					BigDecimal.valueOf(
						item.getQuantidade()
					)
				)
			).reduce(BigDecimal.ZERO, BigDecimal::add);

		pedido.setValorTotal(total);
	}

	public Pedido updateById(Long id, Pedido pedido) {
		return pedidoRepository.findById(id).map( pedidoExistente -> {
			//pedidoExistente.setCliente(null);
			//pedidoExistente.setFuncionario(null);
			pedidoExistente.setDataPedido(pedido.getDataPedido());
			pedidoExistente.setStatus(pedido.getStatus());
			pedidoExistente.setReceita(pedido.getReceita());
			//pedidoExistente.setItens(null);
			//pedidoExistente.setPagamento(null);
			pedidoExistente.setValorTotal(pedido.getValorTotal());
			
			return pedidoRepository.save(pedidoExistente);
		}).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
	}
}