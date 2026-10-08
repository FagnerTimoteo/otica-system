package com.otica.system.DTO.response;

import com.otica.system.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoResponseDTO {

    private Long id;

    private Long clienteId;
    private Long funcionarioId;
    private Long pagamentoId;
    private Long receitaId;

    private LocalDateTime dataPedido;
    private StatusPedido status;
    private BigDecimal valorTotal;
}
