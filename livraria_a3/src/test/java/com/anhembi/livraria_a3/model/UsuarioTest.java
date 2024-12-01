package com.anhembi.livraria_a3.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class UsuarioTest {
     @Test
    void testGettersAndSetters() {
        Usuario usuario = new Usuario();
        
        usuario.setId(1);
        usuario.setNome("João");
        usuario.setSobrenome("Silva");
        usuario.setEmail("joao@exemplo.com");
        usuario.setSenha("senha123");
        usuario.setCelular("123456789");
        usuario.setGenero("Masculino");

        assertEquals(1, usuario.getId());
        assertEquals("João", usuario.getNome());
        assertEquals("Silva", usuario.getSobrenome());
        assertEquals("joao@exemplo.com", usuario.getEmail());
        assertEquals("senha123", usuario.getSenha());
        assertEquals("123456789", usuario.getCelular());
        assertEquals("Masculino", usuario.getGenero());
    }
}
