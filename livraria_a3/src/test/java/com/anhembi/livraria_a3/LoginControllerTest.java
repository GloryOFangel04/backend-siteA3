package com.anhembi.livraria_a3;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.mockito.ArgumentCaptor;
import jakarta.servlet.http.Cookie;
import com.anhembi.livraria_a3.Controller.LoginController;
import com.anhembi.livraria_a3.DAO.IUsuario;
import com.anhembi.livraria_a3.model.Usuario;
import com.anhembi.livraria_a3.service.CookieService;

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
    void testLoginPage() {
        // Verificando o retorno da página de login
        String resultado = loginController.login();
        assertEquals("login", resultado, "Deve retornar a página de login");
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

    @Test
    void testLogarWithValidUser() throws Exception {
        // Criando um usuário válido para o login
        Usuario user = new Usuario();
        user.setId(1);
        user.setNome("Camila");  // Alterado para Camila
        user.setEmail("camila@exemplo.com");  // Alterado para Camila
        user.setSenha("senha123");
    
        // Simulando o comportamento do DAO para retornar um usuário válido
        when(DAO.login("camila@exemplo.com", "senha123")).thenReturn(user);
    
        // Chamando o método logar
        String resultado = loginController.logar(model, user, null, response);
    
        // Verificando o redirecionamento para a página /site após o login bem-sucedido
        assertEquals("redirect:/site", resultado, "Deve redirecionar para /site após login bem-sucedido");
    
        // Usando ArgumentCaptor para capturar os cookies adicionados ao response
        ArgumentCaptor<Cookie> cookieCaptor = ArgumentCaptor.forClass(Cookie.class);
        verify(response, times(2)).addCookie(cookieCaptor.capture());
    
        // Verificando se o Cookie com o nome "usuarioID" e valor "1" foi adicionado
        Cookie usuarioIDCookie = cookieCaptor.getAllValues().get(0);
        assertEquals("usuarioID", usuarioIDCookie.getName());
        assertEquals("1", usuarioIDCookie.getValue());
    
        // Verificando se o Cookie com o nome "nome" e valor "Camila" foi adicionado
        Cookie nomeCookie = cookieCaptor.getAllValues().get(1);
        assertEquals("nome", nomeCookie.getName());
        assertEquals("Camila", nomeCookie.getValue());  // Alterado para Camila
    }
    @Test
void testLogarWithInvalidUser() throws Exception {
    // Criando um usuário com senha errada
    Usuario user = new Usuario();
    user.setEmail("camila@exemplo.com");  // Alterado para Camila
    user.setSenha("senhaErrada");

    // Simulando o comportamento do DAO para não retornar usuário (login inválido)
    when(DAO.login("camila@exemplo.com", "senhaErrada")).thenReturn(null);  // Alterado para Camila

    // Chamando o método logar
    String resultado = loginController.logar(model, user, null, response);

    // Verificando o retorno para login inválido
    assertEquals("login", resultado, "Deve retornar à página de login com erro");

    // Verificando se o modelo recebeu o atributo "erro" com a mensagem correta
    verify(model).addAttribute("erro", "Usuario ou senha invalidos");
}
}
