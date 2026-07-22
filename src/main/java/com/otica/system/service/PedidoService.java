package com.otica.system.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otica.system.model.ItemPedido;
import com.otica.system.model.Pedido;
import com.otica.system.model.Produto;
import com.otica.system.repository.PedidoRepository;
import com.otica.system.repository.ProdutoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
    private ProdutoRepository produtoRepository;
	
	public Pedido save(Pedido pedido) {
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
	
	public Pedido adicionarItem(
			Long pedidoId,
            Long produtoId,
            Integer quantidade) {
		
		Pedido pedido = pedidoRepository.findById(pedidoId)
				.orElseThrow(() -> new RuntimeException(""));
		
		Produto produto = produtoRepository.findById(produtoId)
				.orElseThrow(() -> new RuntimeException(""));
		
		if(produto.getQuantidadeEstoque() < quantidade) {
			throw new RuntimeException("");
		}
		
		ItemPedido item = new ItemPedido();
		
		item.setPedido(pedido);
		item.setProduto(produto);
		item.setQuantidade(quantidade);
		item.setPrecoUnitario(produto.getPreco());
		
		pedido.getItens().add(item);
		
		recalcularTotal(pedido);
		
		return pedido;
	}

	private void recalcularTotal(Pedido pedido) {
		
		BigDecimal total = pedido.getItens()
			.stream().map(item -> 
					item.getPrecoUnitario()
						.multiply(BigDecimal.valueOf(item.getQuantidade())))
			.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		pedido.setTotal(total);
	}

	public Pedido updateById(Long id, Pedido pedido) {
		return pedidoRepository.findById(id).map( pedidoExistente -> {
			//pedidoExistente.setCliente(null);
			//pedidoExistente.setFuncionario(null);
			pedidoExistente.setDataPedido(pedido.getDataPedido());
			pedidoExistente.setStatus(pedido.getStatus());
			pedidoExistente.setPreco(pedido.getPreco());
			pedidoExistente.setReceita(pedido.getReceita());
			//pedidoExistente.setItens(null);
			//pedidoExistente.setPagamento(null);
			pedidoExistente.setTotal(pedido.getTotal());
			
			return pedidoRepository.save(pedidoExistente);
		}).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
	}
}



