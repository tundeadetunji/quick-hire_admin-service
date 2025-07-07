package io.github.tundeadetunji.admin_service.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MessageController.class)
class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getMessages_shouldReturnEmptyInitially() throws Exception {
        mockMvc.perform(get("/admin/messages"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void clearMessages_shouldSucceedEvenIfEmpty() throws Exception {
        mockMvc.perform(delete("/admin/messages"))
                .andExpect(status().isOk());
    }
}
