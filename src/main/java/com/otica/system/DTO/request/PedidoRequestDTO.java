package com.otica.system.DTO.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoRequestDTO {

    @NotNull(message="Id do cliente é obrigatório")
    private Long clienteId;

    @NotNull(message="Id do funcionario é obrigatório")
    private Long funcionarioId;

    @NotNull(message="Id do pagamento é obrigatório")
    private Long pagamentoId;

    @NotNull(message="Id da receita é obrigatório")
    private Long receitaId;
}
