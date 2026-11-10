package com.otica.system.repository;

import com.otica.system.model.Fornecedor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(properties = {
        "spring.flyway.enabled=false"
})
public class FornecedorRepositoryTest {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Test
    @DisplayName("Deve salvar e buscar um fornecedor")
    void deveSalvarEBuscarFornecedor() {
        // Arrange
        Fornecedor fornecedor = new Fornecedor();

        fornecedor.setNome("João da Silva");
        fornecedor.setTelefone("87999999999");
        fornecedor.setEmail("joao@email.com");

        // Act
        Fornecedor fornecedorSalvo = fornecedorRepository.save(fornecedor);

        Optional<Fornecedor> resultado = fornecedorRepository
                .findById(fornecedorSalvo.getId());

        // Assert
        assertThat(fornecedorSalvo.getId()).isNotNull();
        assertThat(resultado).isPresent();
        assertThat(resultado.get().getNome()).isEqualTo("João da Silva");
        assertThat(resultado.get().getTelefone()).isEqualTo("87999999999");
        assertThat(resultado.get().getEmail()).isEqualTo("joao@email.com");
        assertThat(resultado.get().getProdutos()).isNull();
    }
}
