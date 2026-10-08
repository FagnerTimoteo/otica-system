package com.otica.system.DTO.response;

import com.otica.system.model.TipoProduto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoResponseDTO {

    private Long id;

    private String nome;
    private BigDecimal preco;
    private Integer quantidadeEstoque;

    private TipoProduto tipo;
    private Long fornecedorId;
}
