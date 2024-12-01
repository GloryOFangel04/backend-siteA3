package com.anhembi.livraria_a3;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.anhembi.livraria_a3.Controller.LoginController;
import com.anhembi.livraria_a3.DAO.IUsuario;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginControllerTest {

    @Mock
    private IUsuario DAO;

    @Mock
    private Model model;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private LoginController loginController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSiteWithCookies() throws Exception {
        // Criando cookies simulados
        Cookie cookieNome = new Cookie("nome", "João");
        Cookie cookieUsuarioID = new Cookie("usuarioID", "1");

        // Array de cookies a ser retornado pelo método getCookies()
        Cookie[] cookies = { cookieNome, cookieUsuarioID };

        // Simulando o comportamento do request.getCookies() para retornar os cookies criados
        when(request.getCookies()).thenReturn(cookies);

        // Chamando o método site
        String resultado = loginController.site(model, request);

        // Validando as interações e o retorno
        verify(model).addAttribute("nome", "João");
        verify(model).addAttribute("usuarioID", "1");
        assertEquals("site", resultado);
    }

    @Test
    void testSiteWithoutCookies() throws Exception {
        // Simulando o caso em que não há cookies
        when(request.getCookies()).thenReturn(null);

        // Chamando o método site
        String resultado = loginController.site(model, request);

        // Validando as interações e o retorno
        verify(model).addAttribute("nome", null);
        verify(model).addAttribute("usuarioID", null);
        assertEquals("site", resultado);
    }
}