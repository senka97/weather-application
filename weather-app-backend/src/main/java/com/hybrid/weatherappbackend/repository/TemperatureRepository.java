package com.hybrid.weatherappbackend.repository;

import com.hybrid.weatherappbackend.model.City;
import com.hybrid.weatherappbackend.model.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TemperatureRepository extends JpaRepository<Temperature, Long> {

    @Query(value = "select t from Temperature t where t.city = ?1 and t.dateTime > ?2 and t.dateTime < ?3")
    List<Temperature> findForCityAndDate(City city, LocalDateTime startDate, LocalDateTime endDate);
}
