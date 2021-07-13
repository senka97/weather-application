package com.hybrid.weatherappbackend.service;

import com.hybrid.weatherappbackend.model.City;
import com.hybrid.weatherappbackend.repository.CityRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;

@Service
public class CityService {

    public final CityRepository repository;

    @Value("${city1}")
    private String city1;

    @Value("${city2}")
    private String city2;

    @Value("${city3}")
    private String city3;

    @Value("${city1_country}")
    private String city1Country;

    @Value("${city2_country}")
    private String city2Country;

    @Value("${city3_country}")
    private String city3Country;

    public CityService(CityRepository repository){
        this.repository = repository;
    }

    @Transactional
    public List<City> loadCities() {
        City create1 = new City(city1, city1Country);
        City create2 = new City(city2, city2Country);
        City create3 = new City(city3, city3Country);

        this.repository.save(create1);
        this.repository.save(create2);
        this.repository.save(create3);

        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public List<City> getAll(){
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public List<City> getAllSorted(){
        List<City> cities = repository.findAll();
        Collections.sort(cities);
        return cities;
    }

    @Transactional(readOnly = true)
    public City findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("City with id %d not found",id)));
    }

    @Transactional(readOnly = true)
    public City findByName(String name) {
        return repository.findByName(name);
    }

    @Transactional
    public void save(City city){
        repository.save(city);
    }

}
