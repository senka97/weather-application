package com.hybrid.weatherappbackend;

import com.hybrid.weatherappbackend.model.City;
import com.hybrid.weatherappbackend.service.CityService;
import com.hybrid.weatherappbackend.service.TemperatureService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
public class WeatherAppBackendApplication {

    public final CityService cityService;

    public final TemperatureService temperatureService;

    public WeatherAppBackendApplication(CityService cityService, TemperatureService temperatureService){
        this.cityService = cityService;
        this.temperatureService = temperatureService;
    }

    public static void main(String[] args) {
        SpringApplication.run(WeatherAppBackendApplication.class, args);
    }

    @PostConstruct
    public void init() {
        List<City> cities = cityService.loadCities();
        cities.forEach(temperatureService::loadTemperature);
    }


}
