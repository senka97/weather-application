package com.hybrid.weatherappbackend.service;

import com.hybrid.weatherappbackend.model.City;
import com.hybrid.weatherappbackend.repository.CityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CityServiceTest {

    @Mock
    private CityRepository repository;

    @InjectMocks
    private CityService service;

    private static final Long city1Id = 1L;
    private static final Long city2Id = 2L;

    @Test
     void testGetAllSuccess() {

        City city1 = new City(city1Id, "London", "UK");
        City city2 = new City(city2Id, "Bristol", "UK");
        List<City> cities = List.of(city1, city2);

        when(repository.findAll()).thenReturn(cities);
        List<City> foundCities = service.getAll();

        verify(repository, times(1)).findAll();
        Assertions.assertEquals(cities, foundCities);
    }

    @Test
    void testFindByNameSuccess() {

        String name = "London";
        City city = new City(city1Id, "London", "UK");

        when(repository.findByName(name)).thenReturn(city);
        City foundCity = service.findByName(name);

        verify(repository, times(1)).findByName(name);
        Assertions.assertEquals(city, foundCity);
    }

    //TODO finish unit tests for city service

}
