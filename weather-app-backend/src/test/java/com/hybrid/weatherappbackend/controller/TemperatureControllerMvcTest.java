package com.hybrid.weatherappbackend.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TemperatureControllerMvcTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void testMvcGetForAllCitiesFor1DaySuccess() throws Exception {

        mvc.perform(get("/api/temperatures?numOfDays=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect((jsonPath("$", hasSize(3)))).andReturn();
    }

    @Test
    void testMvcGetForAllCitiesFor5DaysSuccess() throws Exception {

        mvc.perform(get("/api/temperatures?numOfDays=5")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect((jsonPath("$", hasSize(15)))).andReturn();
    }

    @Test
    void testMvcGetForCityFor1DaySuccess() throws Exception {

        mvc.perform(get("/api/temperatures/city?name=sombor&numOfDays=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect((jsonPath("$", hasSize(1))));
    }

    @Test
    void testMvcGetForCityFor5DaysSuccess() throws Exception {

        mvc.perform(get("/api/temperatures/city?name=sombor&numOfDays=5")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect((jsonPath("$", hasSize(5))));

    }
}
