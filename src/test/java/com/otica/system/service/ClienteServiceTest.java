
package com.otica.system.service;

import com.otica.system.DTO.request.ClienteRequestDTO;
import com.otica.system.model.Cliente;
import com.otica.system.repository.ClienteRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository repository;

    @InjectMocks
    private ClienteService service;

    @Test
    @DisplayName("Deve cadastrar um cliente")
    void deveCadastrarCliente() {

        // Arrange
        ClienteRequestDTO dto = new ClienteRequestDTO();
        dto.setNome("João da Silva");
        dto.setCpf("12345678900");
        dto.setTelefone("87999999999");
        dto.setEmail("joao@email.com");

        when(repository.save(any(Cliente.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        LocalDateTime inicio = LocalDateTime.now();

        // Act
        Cliente resultado = service.save(dto);

        LocalDateTime fim = LocalDateTime.now();

        // Assert
        assertThat(resultado).isNotNull();
        assertThat(resultado.getNome()).isEqualTo("João da Silva");
        assertThat(resultado.getCpf()).isEqualTo("12345678900");
        assertThat(resultado.getTelefone()).isEqualTo("87999999999");
        assertThat(resultado.getEmail()).isEqualTo("joao@email.com");
        assertThat(resultado.getDataCadastro()).isBetween(inicio, fim);
        assertThat(resultado.getReceitas()).isNull();

        verify(repository, times(1)).save(any(Cliente.class));
    }

    @Test
    @DisplayName("Deve buscar um cliente pelo ID")
    void deveBuscarClientePorId() {

        // Arrange
        Long id = 1L;

        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setNome("João da Silva");

        when(repository.findById(id)).thenReturn(Optional.of(cliente));

        // Act
        Cliente resultado = service.findById(id);

        // Assert
        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo(id);
        assertThat(resultado.getNome()).isEqualTo("João da Silva");

        verify(repository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar cliente inexistente")
    void deveLancarExcecaoAoBuscarClienteInexistente() {

        // Arrange
        Long id = 1L;

        when(repository.findById(id))
                .thenReturn(Optional.empty());

        // Act e Assert
        assertThatThrownBy(() -> service.findById(id))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Cliente não encontrado");

        verify(repository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve excluir um cliente pelo ID")
    void deveExcluirClientePorId() {

        // Arrange
        Long id = 1L;

        // Act
        service.deleteById(id);

        // Assert
        verify(repository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Deve atualizar um cliente")
    void deveAtualizarCliente() {

        // Arrange
        Long id = 1L;

        Cliente clienteExistente = new Cliente();
        clienteExistente.setId(id);
        clienteExistente.setNome("João");
        clienteExistente.setCpf("11111111111");
        clienteExistente.setTelefone("11111111111");
        clienteExistente.setEmail("joao@email.com");
        clienteExistente.setDataCadastro(LocalDateTime.now().minusDays(10));

        Cliente clienteAtualizado = new Cliente();
        clienteAtualizado.setNome("João da Silva");
        clienteAtualizado.setCpf("12345678900");
        clienteAtualizado.setTelefone("87999999999");
        clienteAtualizado.setEmail("joao.silva@email.com");
        clienteAtualizado.setDataCadastro(LocalDateTime.now());

        when(repository.findById(id))
                .thenReturn(Optional.of(clienteExistente));

        when(repository.save(any(Cliente.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Cliente resultado = service.updateById(id, clienteAtualizado);

        // Assert
        assertThat(resultado.getId()).isEqualTo(id);
        assertThat(resultado.getNome()).isEqualTo("João da Silva");
        assertThat(resultado.getCpf()).isEqualTo("12345678900");
        assertThat(resultado.getTelefone()).isEqualTo("87999999999");
        assertThat(resultado.getEmail()).isEqualTo("joao.silva@email.com");
        assertThat(resultado.getDataCadastro())
                .isEqualTo(clienteAtualizado.getDataCadastro());

        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(any(Cliente.class));
    }

    @Test
    @DisplayName("Deve lançar exceção ao atualizar cliente inexistente")
    void deveLancarExcecaoAoAtualizarClienteInexistente() {

        // Arrange
        Long id = 1L;
        Cliente cliente = new Cliente();

        when(repository.findById(id)).thenReturn(Optional.empty());

        // Act e Assert
        assertThatThrownBy(() -> service.updateById(id, cliente))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Cliente não encontrado");

        verify(repository, times(1)).findById(id);
        verify(repository, never()).save(any(Cliente.class));
    }
}
