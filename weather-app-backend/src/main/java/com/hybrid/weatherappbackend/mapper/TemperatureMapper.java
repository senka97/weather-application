package com.hybrid.weatherappbackend.mapper;

import com.hybrid.weatherappbackend.dto.TemperatureResponseDTO;
import com.hybrid.weatherappbackend.model.City;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TemperatureMapper {

    private final CityMapper cityMapper;

    public TemperatureMapper(CityMapper cityMapper) {
        this.cityMapper = cityMapper;
    }

    public TemperatureResponseDTO toDTO(City city, Integer averageTemperature, LocalDate date){

        TemperatureResponseDTO temperatureDTO = new TemperatureResponseDTO();

        temperatureDTO.setCity(cityMapper.toDTO(city));
        temperatureDTO.setAverageTemp(averageTemperature);
        temperatureDTO.setDate(date);

        return temperatureDTO;
    }
}
