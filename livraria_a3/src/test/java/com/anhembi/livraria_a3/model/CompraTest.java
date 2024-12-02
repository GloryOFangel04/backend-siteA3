package com.anhembi.livraria_a3.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompraTest {
        private Compra compra;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        // Inicializando um usuário fictício para testar
        usuario = new Usuario();
        usuario.setId(1);
        usuario.setNome("João");
        usuario.setEmail("joao@exemplo.com");

        // Inicializando a compra
        compra = new Compra();
    }

    @Test
    void testCompraConstructor() {
        // Testando o construtor completo
        LocalDateTime dataCompra = LocalDateTime.now();
        Compra compraCompleta = new Compra(usuario, "joao@exemplo.com", "Rua Teste", "São Paulo", "SP", dataCompra, 2, 150.0);

        assertNotNull(compraCompleta);
        assertEquals(usuario, compraCompleta.getUsuario());
        assertEquals("joao@exemplo.com", compraCompleta.getEmail());
        assertEquals("Rua Teste", compraCompleta.getEndereco());
        assertEquals("São Paulo", compraCompleta.getCidade());
        assertEquals("SP", compraCompleta.getEstado());
        assertEquals(dataCompra, compraCompleta.getDataCompra());
        assertEquals(2, compraCompleta.getQuantidade());
        assertEquals(150.0, compraCompleta.getTotal());
    }

    @Test
    void testGettersAndSetters() {
        // Testando o funcionamento dos getters e setters
        compra.setUsuario(usuario);
        compra.setEmail("joao@exemplo.com");
        compra.setEndereco("Rua Teste");
        compra.setCidade("São Paulo");
        compra.setEstado("SP");
        compra.setDataCompra(LocalDateTime.now());
        compra.setQuantidade(2);
        compra.setTotal(150.0);

        assertEquals(usuario, compra.getUsuario());
        assertEquals("joao@exemplo.com", compra.getEmail());
        assertEquals("Rua Teste", compra.getEndereco());
        assertEquals("São Paulo", compra.getCidade());
        assertEquals("SP", compra.getEstado());
        assertNotNull(compra.getDataCompra());  // Apenas verificar se a data não é nula
        assertEquals(2, compra.getQuantidade());
        assertEquals(150.0, compra.getTotal());
    }

    @Test
    void testCompraWithoutUsuario() {
        // Testando se a compra pode ser criada sem usuário pq vai que
        compra.setUsuario(null);
        compra.setEmail("semusuario@exemplo.com");
        compra.setEndereco("Av. Sem Rua");
        compra.setCidade("Cidade Exemplo");
        compra.setEstado("XX");
        compra.setDataCompra(LocalDateTime.now());
        compra.setQuantidade(5);
        compra.setTotal(200.0);

        assertNull(compra.getUsuario());
        assertEquals("semusuario@exemplo.com", compra.getEmail());
        assertEquals("Av. Sem Rua", compra.getEndereco());
        assertEquals("Cidade Exemplo", compra.getCidade());
        assertEquals("XX", compra.getEstado());
        assertNotNull(compra.getDataCompra());
        assertEquals(5, compra.getQuantidade());
        assertEquals(200.0, compra.getTotal());
    }

    @Test
    void testCompraWithMissingFields() {
        // Testando o comportamento com alguns campos ausentes
        compra.setUsuario(usuario);
        compra.setEmail(null);  // Email nulo
        compra.setEndereco("Rua Teste");
        compra.setCidade("São Paulo");
        compra.setEstado("SP");
        compra.setDataCompra(LocalDateTime.now());
        compra.setQuantidade(2);
        compra.setTotal(100.0);

        assertNull(compra.getEmail());  // Email não atribuído
        assertEquals("Rua Teste", compra.getEndereco());
        assertEquals("São Paulo", compra.getCidade());
        assertEquals("SP", compra.getEstado());
        assertNotNull(compra.getDataCompra());
        assertEquals(2, compra.getQuantidade());
        assertEquals(100.0, compra.getTotal());
    }
}
