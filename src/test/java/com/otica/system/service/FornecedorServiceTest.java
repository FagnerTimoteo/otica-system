package com.otica.system.service;

import com.otica.system.DTO.request.FornecedorRequestDTO;
import com.otica.system.model.Fornecedor;
import com.otica.system.repository.FornecedorRepository;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FornecedorServiceTest {

    @Mock
    private FornecedorRepository repository;

    @InjectMocks
    private FornecedorService service;

    @Test
    @DisplayName("Deve cadastrar um fornecedor")
    void deveCadastrarFornecedor() {

        // Arrange
        FornecedorRequestDTO dto = new FornecedorRequestDTO();
        dto.setNome("Visual Lens");
        dto.setEmail("visual@lens.com");
        dto.setTelefone("87999999999");

        when(repository.save(any(Fornecedor.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Fornecedor resultado = service.save(dto);

        // Assert
        assertThat(resultado).isNotNull();
        assertThat(resultado.getNome()).isEqualTo("Visual Lens");
        assertThat(resultado.getEmail()).isEqualTo("visual@lens.com");
        assertThat(resultado.getTelefone()).isEqualTo("87999999999");
        assertThat(resultado.getProdutos()).isNull();

        verify(repository, times(1)).save(any(Fornecedor.class));
    }

    @Test
    @DisplayName("Deve buscar um fornecedor pelo ID")
    void deveBuscarFornecedorPorId() {
        // Arrange
        Long id = 1L;

        when(repository.findById(id))
            .thenReturn(Optional.empty());

        // Act e Assert
        assertThatThrownBy(() -> service.findById(id))
            .isInstanceOf(RuntimeException.class)
            .hasMessage("Fornecedor não encontrado");

        verify(repository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve excluir um fornecedor pelo ID")
    void deveExcluirFornecedorPorId() {

        // Arrange
        Long id = 1L;

        // Act
        service.deleteById(id);

        // Assert
        verify(repository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Deve atualizar um fornecedor")
    void deveAtualizarFornecedor() {

        // Arrange
        Long id = 1L;

        Fornecedor fornecedorExistente = new Fornecedor();
        fornecedorExistente.setNome("Visual Lens");
        fornecedorExistente.setEmail("visual@lens.com");
        fornecedorExistente.setTelefone("87999999999");

        when(repository.findById(id))
                .thenReturn(Optional.of(fornecedorExistente));

        when(repository.save(any(Fornecedor.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Fornecedor fornecedorAtualizado = new Fornecedor();
        fornecedorAtualizado.setNome("Visual Lentes");
        fornecedorAtualizado.setEmail("visual@lentes.com");
        fornecedorAtualizado.setTelefone("87999998888");

        // Act
        Fornecedor resultado = service.updateById(id, fornecedorAtualizado);

        // Assert
        assertThat(resultado).isNotNull();
        assertThat(resultado.getNome()).isEqualTo("Visual Lentes");
        assertThat(resultado.getEmail()).isEqualTo("visual@lentes.com");
        assertThat(resultado.getTelefone()).isEqualTo("87999998888");
        assertThat(resultado.getProdutos()).isNull();

        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(any(Fornecedor.class));
    }

    @Test
    @DisplayName("Deve lançar exceção ao atualizar fornecedor inexistente")
    void deveLancarExcecaoAoAtualizarFornecedorInexistente() {

        // Arrange
        Long id = 1L;
        Fornecedor fornecedor = new Fornecedor();

        when(repository.findById(id)).thenReturn(Optional.empty());

        // Act e Assert
        assertThatThrownBy(() -> service.updateById(id, fornecedor))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Fornecedor não encontrado");

        verify(repository, times(1)).findById(id);
        verify(repository, never()).save(any(Fornecedor.class));
    }
}
