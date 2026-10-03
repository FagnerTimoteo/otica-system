package com.otica.system.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
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
	@JoinColumn(name = "funcionario_id")
	private Usuario funcionario;

	private LocalDateTime dataPedido;

	@Enumerated(EnumType.STRING)
	private StatusPedido status;

	@ManyToOne
	@JoinColumn(name = "receita_id")
	private Receita receita;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedido> itens = new ArrayList<>();

	@OneToOne(mappedBy = "pedido")
	private Pagamento pagamento;

	private BigDecimal valorTotal;
}