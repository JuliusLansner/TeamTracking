package dk.teamtracker.teamtrackingbackend.controller;
import dk.teamtracker.teamtrackingbackend.config.SecurityConfig;
import dk.teamtracker.teamtrackingbackend.util.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(AuthController.class)
@Import(SecurityConfig.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthenticationManager authenticationManager;

    @MockitoBean
    private JwtTokenProvider tokenProvider;

    @Test
    public void testAuthenticateUser_success() throws Exception{
        String loginJson = """
            {
                "username": "testuser",
                "password": "password"
            }
            """;


        Authentication dummyAuth = new UsernamePasswordAuthenticationToken("testuser","password",Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));

        Mockito.when(authenticationManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class))).thenReturn(dummyAuth);

        String dummyToken = "dummy.jwt.token";

        Mockito.when(tokenProvider.generateToken(dummyAuth)).thenReturn(dummyToken);

        mockMvc.perform(post("/api/auth/login")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value(dummyToken))
                .andExpect(jsonPath("$.tokenType").value("Bearer"));




    }






}