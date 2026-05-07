package com.labcorp.employee.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.labcorp.employee.dto.WorkRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldWorkSuccessfully() throws Exception {

        WorkRequest request = new WorkRequest(20);

        mockMvc.perform(
                        post("/api/v1/employees/1/work")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnBadRequestForInvalidWorkDays() throws Exception {

        WorkRequest request = new WorkRequest(0);

        mockMvc.perform(
                        post("/api/v1/employees/1/work")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnNotFoundForUnknownEmployee() throws Exception {

        WorkRequest request = new WorkRequest(10);

        mockMvc.perform(
                        post("/api/v1/employees/999/work")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnBadRequestForMalformedJson() throws Exception {

        mockMvc.perform(
                        post("/api/v1/employees/1/work")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{invalid}")
                )
                .andExpect(status().isBadRequest());
    }

}