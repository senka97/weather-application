package com.hybrid.weatherappbackend.mapper;

import com.hybrid.weatherappbackend.dto.CityShowDTO;
import com.hybrid.weatherappbackend.model.City;
import org.springframework.stereotype.Component;

@Component
public class CityMapper {

    public CityShowDTO toDTO(City city){

        CityShowDTO cityDTO = new CityShowDTO();

        cityDTO.setId(city.getId());
        cityDTO.setName(city.getName());
        cityDTO.setCountry(city.getCountry());
        cityDTO.setAverageTemperature5Days(city.getAverageTemperature5Days());

        return cityDTO;
    }
}
