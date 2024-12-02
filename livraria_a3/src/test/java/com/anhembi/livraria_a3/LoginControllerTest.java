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
       
        String resultado = loginController.login();
        assertEquals("login", resultado, "Deve retornar a página de login");
    }

    @Test
    void testSiteWithCookies() throws Exception {
        
        Cookie cookieNome = new Cookie("nome", "João");
        Cookie cookieUsuarioID = new Cookie("usuarioID", "1");

       
        Cookie[] cookies = { cookieNome, cookieUsuarioID };

      
        when(request.getCookies()).thenReturn(cookies);

        
        String resultado = loginController.site(model, request);

    
        verify(model).addAttribute("nome", "João");
        verify(model).addAttribute("usuarioID", "1");
        assertEquals("site", resultado);
    }

    @Test
    void testSiteWithoutCookies() throws Exception {
        
        when(request.getCookies()).thenReturn(null);

        
        String resultado = loginController.site(model, request);

       
        verify(model).addAttribute("nome", null);
        verify(model).addAttribute("usuarioID", null);
        assertEquals("site", resultado);
    }

    @Test
    void testLogarWithValidUser() throws Exception {
       
        Usuario user = new Usuario();
        user.setId(1);
        user.setNome("Camila");  
        user.setEmail("camila@exemplo.com");  
        user.setSenha("senha123");
    
        
        when(DAO.login("camila@exemplo.com", "senha123")).thenReturn(user);
    
        String resultado = loginController.logar(model, user, null, response);
    
        
        assertEquals("redirect:/site", resultado, "Deve redirecionar para /site após login bem-sucedido");
    
        ArgumentCaptor<Cookie> cookieCaptor = ArgumentCaptor.forClass(Cookie.class);
        verify(response, times(2)).addCookie(cookieCaptor.capture());
    
      
        Cookie usuarioIDCookie = cookieCaptor.getAllValues().get(0);
        assertEquals("usuarioID", usuarioIDCookie.getName());
        assertEquals("1", usuarioIDCookie.getValue());
    
      
        Cookie nomeCookie = cookieCaptor.getAllValues().get(1);
        assertEquals("nome", nomeCookie.getName());
        assertEquals("Camila", nomeCookie.getValue()); 
    }
    @Test
void testLogarWithInvalidUser() throws Exception {
  
    Usuario user = new Usuario();
    user.setEmail("camila@exemplo.com");
    user.setSenha("senhaErrada");

    
    when(DAO.login("camila@exemplo.com", "senhaErrada")).thenReturn(null);  

  
    String resultado = loginController.logar(model, user, null, response);

   
    assertEquals("login", resultado, "Deve retornar à página de login com erro");

    
    verify(model).addAttribute("erro", "Usuario ou senha invalidos");
}
}
