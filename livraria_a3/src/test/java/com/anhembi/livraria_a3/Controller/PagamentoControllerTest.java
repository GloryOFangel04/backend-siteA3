package com.anhembi.livraria_a3.Controller;

import static org.assertj.core.api.Assertions.assertThat; // Importa a biblioteca AssertJ para fazer verificações mais claras e fluentes
import static org.mockito.Mockito.*; // Importa o Mockito para criar mocks e verificar interações

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired; // Permite injetar dependências automaticamente
import org.springframework.boot.test.context.SpringBootTest; // Carrega o contexto completo do Spring Boot para testes
import org.springframework.boot.test.mock.mockito.MockBean; // Substitui o bean real por um mock para simular comportamentos

import com.anhembi.livraria_a3.DAO.CompraRepository; // O DAO que será mockado
import com.anhembi.livraria_a3.model.Compra; // Modelo que representa uma compra

@SpringBootTest 
class PagamentoControllerTest {

    @Autowired 
    private PagamentoController pagamentoController;

    @MockBean // Cria um mock do repositório para simular chamadas ao banco de dados
    private CompraRepository compraRepository;

    @Test
    void testListaCompras() {
        // Mockando os dados que o banco retornaria
        Compra compra1 = new Compra(null, "cliente1@example.com", "Rua A, 123", "São Paulo", "SP", null, 2, 100.0);
        Compra compra2 = new Compra(null, "cliente2@example.com", "Rua B, 456", "Rio de Janeiro", "RJ", null, 3, 150.0);

        // Quando o método findAll() do repositório for chamado, ele vai retornar essa lista de compras mockada
        when(compraRepository.findAll()).thenReturn(Arrays.asList(compra1, compra2));

        // Chamando o método que queremos testar
        List<Compra> compras = pagamentoController.listaCompras();

        // Fazendo as verificações para garantir que o método está funcionando corretamente

        assertThat(compras).isNotNull().hasSize(2); // Verifica que a lista não é nula e tem 2 itens
        assertThat(compras.get(0).getEmail()).isEqualTo("cliente1@example.com"); // Verifica o email do primeiro item
        assertThat(compras.get(1).getEmail()).isEqualTo("cliente2@example.com"); // Verifica o email do segundo item

        // Verifica se o método findAll() foi chamado 1 vez 
        verify(compraRepository, times(1)).findAll();
    }

    @Test
    void testNovaCompra() {
      
        Compra novaCompra = new Compra(null, "cliente@example.com", "Rua C, 789", "Salvador", "BA", null, 1, 50.0);

        // Quando o método save() do repositório for chamado, ele vai retornar a compra mockada

        when(compraRepository.save(any(Compra.class))).thenReturn(novaCompra);

        // Chamando o método que queremos testar
        Compra resultado = pagamentoController.novaCompra(novaCompra);

        // Fazendo as verificações para garantir que o método está funcionando corretamente

        assertThat(resultado).isNotNull(); // Verifica que o resultado não é nulo

        assertThat(resultado.getEmail()).isEqualTo("cliente@example.com"); // Verifica o email da compra

        assertThat(resultado.getEndereco()).isEqualTo("Rua C, 789"); // Verifica o endereço

        assertThat(resultado.getQuantidade()).isEqualTo(1); // Verifica a quantidade

        assertThat(resultado.getTotal()).isEqualTo(50.0); // Verifica o valor total

        // Verifica se o método save() foi chamado exatamente 1 vez no mock
        verify(compraRepository, times(1)).save(novaCompra);
    }
}
