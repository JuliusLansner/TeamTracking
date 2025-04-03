package dk.teamtracker.teamtrackingbackend.controller;

import dk.teamtracker.teamtrackingbackend.entity.User;
import dk.teamtracker.teamtrackingbackend.repository.UserRepo;
import dk.teamtracker.teamtrackingbackend.util.UserRole;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import java.util.Optional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WithMockUser(username = "testUser",roles={"USER"})
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserRepo userRepo;
    @Test
    void createUserTest() throws Exception {

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setUsername("testUser");
        savedUser.setEmail("test@example.com");
        savedUser.setPassword("password");
        savedUser.setRole(UserRole.DEVELOPER);




        Mockito.when(userRepo.save(Mockito.any(User.class))).thenReturn(savedUser);


        String requestJson = """
            {
              "username": "testUser",
              "email": "test@example.com",
              "password": "password",
              "role": "DEVELOPER"
            }
            """;


        mockMvc.perform(post("/api/users/create")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("testUser"))
                .andExpect(jsonPath("$.email").value("test@example.com"))
                .andExpect(jsonPath("$.role").value("DEVELOPER"));
    }

    @Test
    void updateUserTest() throws Exception{
        Long userId = 1L;

        User existingUser = new User();
        existingUser.setId(userId);
        existingUser.setUsername("oldUsername");
        existingUser.setEmail("old@test.com");
        existingUser.setRole(UserRole.DEVELOPER);


        User savedUser = new User();
        savedUser.setId(userId);
        savedUser.setEmail("new@test.com");
        savedUser.setUsername("newUsername");
        savedUser.setRole(UserRole.DEVELOPER);

        Mockito.when(userRepo.findById(userId)).thenReturn(Optional.of(existingUser));
        Mockito.when(userRepo.save(Mockito.any(User.class))).thenReturn(savedUser);
        String requestJson = """
            {
              "username": "newUsername",
              "email": "new@test.com"
        
            }
            """;

        mockMvc.perform(put("/api/users/" +userId)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userId))
                .andExpect(jsonPath("$.username").value("newUsername"))
                .andExpect(jsonPath("$.email").value("new@test.com"));




    }


}