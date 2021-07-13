package com.hybrid.weatherappbackend.repository;

import com.hybrid.weatherappbackend.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CityRepository extends JpaRepository<City, Long> {

    @Query(value = "select c from City c where lower(c.name) = ?1")
    City findByName(String name);
}
