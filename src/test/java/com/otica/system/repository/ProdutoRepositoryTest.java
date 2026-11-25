package com.otica.system.repository;

import com.otica.system.model.Produto;
import com.otica.system.model.TipoProduto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(properties = {
        "spring.flyway.enabled=false"
})
public class ProdutoRepositoryTest {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Test
    @DisplayName("Deve salvar e buscar um produto")
    void deveSalvarEBuscarProduto() {
        // Arrange
        Produto produto = new Produto();

        produto.setNome("Freezel");
        produto.setPreco(BigDecimal.valueOf(300));
        produto.setQuantidadeEstoque(22);
        produto.setTipo(TipoProduto.LENTE);

        // Act
        Produto produtoSalvo = produtoRepository.save(produto);

        Optional<Produto> resultado =
                produtoRepository.findById(produtoSalvo.getId());

        // Assert
        assertThat(produtoSalvo.getId()).isNotNull();
        assertThat(resultado).isPresent();
        assertThat(resultado.get().getNome()).isEqualTo("Freezel");
        assertThat(resultado.get().getPreco()).isEqualTo(BigDecimal.valueOf(300));
        assertThat(resultado.get().getQuantidadeEstoque()).isEqualTo(22);
        assertThat(resultado.get().getTipo()).isEqualTo(TipoProduto.LENTE);
        assertThat(resultado.get().getFornecedor()).isNull();
        assertThat(resultado.get().getItens()).isEmpty();
    }
}
