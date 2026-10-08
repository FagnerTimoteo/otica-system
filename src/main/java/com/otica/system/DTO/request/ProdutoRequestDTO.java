package com.otica.system.DTO.request;

import com.otica.system.model.TipoProduto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotNull(message="Valor é obrigatório")
    @Positive(message="Valor tem que ser obrigatoriamente positivo.")
    private BigDecimal preco;

    @NotNull(message="Quantidade é obrigatório")
    @PositiveOrZero(message = "Quantidade não pode ser negativa")
    private Integer quantidadeEstoque;

    @NotNull(message = "Tipo é obrigatório")
    private TipoProduto tipo;

    @NotNull(message = "Id de fornecedor é obrigatório")
    private Long fornecedorId;
}
