package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.yandex.practicum.controller.UserController;
import ru.yandex.practicum.dto.UserDto;
import ru.yandex.practicum.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    @Test
    public void testAddUser() throws Exception {
        UserDto userDto = new UserDto("John Doe");
        ;

        mockMvc.perform(MockMvcRequestBuilders.put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"John Doe\"}"))
                .andExpect(status().isOk());

        verify(userRepository).add(any(UserDto.class));
    }

    @Test
    public void testGetUsers() throws Exception {
        UserDto userDto = new UserDto("John Doe");

        when(userRepository.get()).thenReturn(List.of(userDto));

        mockMvc.perform(MockMvcRequestBuilders.get("/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("John Doe")); // Assert the name of the first user

        verify(userRepository).get();
    }
}