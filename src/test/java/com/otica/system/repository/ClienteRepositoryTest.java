
package com.otica.system.repository;

import com.otica.system.model.Cliente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(properties = {
        "spring.flyway.enabled=false"
})
class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    @DisplayName("Deve salvar e buscar um cliente")
    void deveSalvarEBuscarCliente() {
        // Arrange
        Cliente cliente = new Cliente();

        cliente.setNome("João da Silva");
        cliente.setCpf("12345678900");
        cliente.setTelefone("87999999999");
        cliente.setEmail("joao@email.com");
        cliente.setDataCadastro(LocalDateTime.now());

        LocalDateTime inicio = LocalDateTime.now();

        // Act
        Cliente clienteSalvo = clienteRepository.save(cliente);

        Optional<Cliente> resultado =
                clienteRepository.findById(clienteSalvo.getId());

        LocalDateTime fim = LocalDateTime.now();

        // Assert
        assertThat(clienteSalvo.getId()).isNotNull();
        assertThat(resultado).isPresent();
        assertThat(resultado.get().getNome()).isEqualTo("João da Silva");
        assertThat(resultado.get().getTelefone()).isEqualTo("87999999999");
        assertThat(resultado.get().getCpf()).isEqualTo("12345678900");
        assertThat(resultado.get().getEmail()).isEqualTo("joao@email.com");
        assertThat(resultado.get().getDataCadastro()).isBetween(inicio, fim);
        assertThat(resultado.get().getReceitas()).isNull();
    }
}
