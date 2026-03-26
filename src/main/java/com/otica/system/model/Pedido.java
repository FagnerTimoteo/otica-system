package com.otica.system.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Pedido {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "funcionario")
	private Usuario funcionario;
	
	private LocalDateTime dataPedido;
	
	@Enumerated(EnumType.STRING)
	private StatusPedido status;
	
	private BigDecimal preco;
	
	@ManyToOne
	private Receita receita;
	
	@OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;

    @OneToOne(mappedBy = "pedido")
    private Pagamento pagamento;
}
