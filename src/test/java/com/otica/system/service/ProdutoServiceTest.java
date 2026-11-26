package com.otica.system.service;

import com.otica.system.DTO.request.ProdutoRequestDTO;
import com.otica.system.model.Fornecedor;
import com.otica.system.model.Produto;
import com.otica.system.model.TipoProduto;
import com.otica.system.repository.FornecedorRepository;
import com.otica.system.repository.ProdutoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {

    @Mock
    private ProdutoRepository repository;

    @Mock
    private FornecedorRepository fornecedorRepository;

    @InjectMocks
    private ProdutoService service;

    @Test
    @DisplayName("Deve cadastrar um produto")
    void deveCadastrarProduto() {

        // Arrange
        ProdutoRequestDTO dto = new ProdutoRequestDTO();
        dto.setNome("Lente");
        dto.setPreco(BigDecimal.valueOf(300));
        dto.setQuantidadeEstoque(22);
        dto.setTipo(TipoProduto.LENTE);

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(1L);
        fornecedor.setNome("Visual Lens");
        fornecedor.setEmail("visual@lens.com");
        fornecedor.setTelefone("87999999999");

        dto.setFornecedorId(1L);

        when(fornecedorRepository.findById(1L))
                .thenReturn(Optional.of(fornecedor));

        when(repository.save(any(Produto.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Produto resultado = service.save(dto);

        // Assert
        assertThat(resultado).isNotNull();
        assertThat(resultado.getNome()).isEqualTo("Lente");
        assertThat(resultado.getPreco()).isEqualTo(BigDecimal.valueOf(300));
        assertThat(resultado.getQuantidadeEstoque()).isEqualTo(22);
        assertThat(resultado.getTipo()).isEqualTo(TipoProduto.LENTE);
        assertThat(resultado.getFornecedor()).isNotNull();
        assertThat(resultado.getFornecedor().getNome()).isEqualTo("Visual Lens");
        assertThat(resultado.getItens()).isEmpty();

        verify(fornecedorRepository, times(1)).findById(1L);
        verify(repository, times(1)).save(any(Produto.class));
    }

    @Test
    @DisplayName("Deve buscar um produto pelo ID")
    void deveBuscarProdutoPorId() {

        // Arrange
        Long id = 1L;

        Produto produto = new Produto();
        produto.setId(id);
        produto.setNome("João da Silva");

        when(repository.findById(id)).thenReturn(Optional.of(produto));

        // Act
        Produto resultado = service.findById(id);

        // Assert
        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo(id);
        assertThat(resultado.getNome()).isEqualTo("João da Silva");

        verify(repository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar produto inexistente")
    void deveLancarExcecaoAoBuscarProdutoInexistente() {

        // Arrange
        Long id = 1L;

        when(repository.findById(id))
                .thenReturn(Optional.empty());

        // Act e Assert
        assertThatThrownBy(() -> service.findById(id))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Produto não encontrado");

        verify(repository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve atualizar um produto")
    void deveAtualizarProduto() {

        // Arrange
        Long id = 1L;
        Long fornecedorId = 10L;

        Produto produtoExistente = new Produto();
        produtoExistente.setId(id);
        produtoExistente.setNome("Freezel");
        produtoExistente.setPreco(BigDecimal.valueOf(300));
        produtoExistente.setQuantidadeEstoque(22);
        produtoExistente.setTipo(TipoProduto.LENTE);

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(fornecedorId);
        fornecedor.setNome("Visual Lens");
        fornecedor.setEmail("visual@lens.com");
        fornecedor.setTelefone("87999999999");

        ProdutoRequestDTO produtoAtualizado = new ProdutoRequestDTO();
        produtoAtualizado.setNome("Lenses");
        produtoAtualizado.setPreco(BigDecimal.valueOf(230));
        produtoAtualizado.setQuantidadeEstoque(12);
        produtoAtualizado.setTipo(TipoProduto.ARMACAO);
        produtoAtualizado.setFornecedorId(fornecedorId);

        when(repository.findById(id))
                .thenReturn(Optional.of(produtoExistente));

        when(fornecedorRepository.findById(fornecedorId))
                .thenReturn(Optional.of(fornecedor));

        when(repository.save(any(Produto.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Produto resultado = service.updateById(id, produtoAtualizado);

        // Assert
        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo(id);
        assertThat(resultado.getNome()).isEqualTo("Lenses");
        assertThat(resultado.getPreco()).isEqualTo(BigDecimal.valueOf(230));
        assertThat(resultado.getQuantidadeEstoque()).isEqualTo(12);
        assertThat(resultado.getTipo()).isEqualTo(TipoProduto.ARMACAO);
        assertThat(resultado.getFornecedor()).isNotNull();
        assertThat(resultado.getFornecedor().getNome()).isEqualTo("Visual Lens");
        assertThat(resultado.getItens()).isEmpty();

        verify(repository, times(1)).findById(id);
        verify(fornecedorRepository, times(1)).findById(fornecedorId);
        verify(repository, times(1)).save(any(Produto.class));
    }

    @Test
    @DisplayName("Deve lançar exceção ao atualizar produto inexistente")
    void deveLancarExcecaoAoAtualizarProdutoInexistente() {

        // Arrange
        Long id = 1L;
        ProdutoRequestDTO produto = new ProdutoRequestDTO();

        when(repository.findById(id)).thenReturn(Optional.empty());

        // Act e Assert
        assertThatThrownBy(() -> service.updateById(id, produto))
            .isInstanceOf(RuntimeException.class)
            .hasMessage("Produto não encontrado");

        verify(repository, times(1)).findById(id);
        verify(repository, never()).save(any(Produto.class));

        verifyNoInteractions(fornecedorRepository);
    }
}
