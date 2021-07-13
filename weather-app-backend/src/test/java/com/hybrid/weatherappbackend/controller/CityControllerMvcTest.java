package com.hybrid.weatherappbackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hybrid.weatherappbackend.model.City;
import com.hybrid.weatherappbackend.repository.CityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CityControllerMvcTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CityRepository repository;

    @Test
    void testMvcGetAllSuccess() throws Exception {

        MvcResult result = mvc.perform(get("/api/cities")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect((jsonPath("$", hasSize(3)))).andReturn();

        String resultJson = result.getResponse().getContentAsString();
        List<City> returnedCities = List.of(objectMapper.readValue(resultJson, City[].class));
        List<City> cities = repository.findAll();

        Assertions.assertTrue(cities.containsAll(returnedCities));
    }

    @Test
    void testMvcGetAllFail() throws Exception {

        mvc.perform(get("/api/cities")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((jsonPath("$", not(0))));
    }

    //TODO tests for returning cities sorted by average temperature

}
