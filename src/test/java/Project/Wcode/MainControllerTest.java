package Project.Wcode;

import Project.Wcode.MainController;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserRepository userRepository;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/data/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, World")));
    }
    @Test
    public void shouldReturnUser() throws Exception {
        this.mockMvc.perform(get("/data/user/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(""));


    }
    @Test
    public void shouldPostUser() throws Exception {
        this.mockMvc.perform(post("/data/register")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("name", "Roy Java")
                .param("phone", String.valueOf(1234567890))
                .param("email", "Roy@Java.com")
                .param("pass", "Hackerman"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Saved"));
    }
    @Test
    public void shouldDeleteUser() throws Exception {
        this.mockMvc.perform(post("/data/delete")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("id", "5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted succesfully"));
    }

    @Test
    public void shouldDeleteUserPath() throws Exception {
        this.mockMvc.perform(delete("/data/delete/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted succesfully"));
        //Expected: Deleted id: 2
    }
    /*@Test
    public void getUser() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/data/user/1"))
                .andExpect((ResultMatcher) jsonPath("$").isMap())
                .andExpect((ResultMatcher) jsonPath("nombreUsuario").value("eric"))
                .andExpect((ResultMatcher) jsonPath("telefono").value("3315732625"))
                .andExpect((ResultMatcher) jsonPath("email").value("eric@gmail.com"))
                .andExpect((ResultMatcher) jsonPath("contrasena").value("contraseña"));
    }*/
}
