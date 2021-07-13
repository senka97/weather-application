package com.hybrid.weatherappbackend.service;

import com.google.gson.Gson;
import com.hybrid.weatherappbackend.dto.APIResponseDTO;
import com.hybrid.weatherappbackend.dto.TemperatureDTO;
import com.hybrid.weatherappbackend.dto.TemperatureResponseDTO;
import com.hybrid.weatherappbackend.mapper.TemperatureMapper;
import com.hybrid.weatherappbackend.model.City;
import com.hybrid.weatherappbackend.model.Temperature;
import com.hybrid.weatherappbackend.repository.TemperatureRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class TemperatureService {

    public final TemperatureRepository repository;
    public final TemperatureMapper mapper;
    public final CityService cityService;
    public final RestTemplate restTemplate;

    public TemperatureService(TemperatureRepository repository, TemperatureMapper mapper,
                              CityService cityService, RestTemplate restTemplate){
        this.repository = repository;
        this.mapper = mapper;
        this.cityService = cityService;
        this.restTemplate = restTemplate;
    }

    @Value("${api_key}")
    private String apiKey;

    public void loadTemperature(City city){
        ResponseEntity<String> response;
        try{
            response = restTemplate.getForEntity("http://api.openweathermap.org/data/2.5/forecast?" +
                            "q=" + city.getName() +
                            "&units=metric" +
                            "&appid=" + apiKey,
                            String.class);

            APIResponseDTO responseDTO = new Gson().fromJson(response.getBody(), APIResponseDTO.class);

            List<Integer> temperatures = new ArrayList<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            responseDTO.getList().forEach(
                    temperatureDTO -> {saveTemperature(temperatureDTO, city, formatter);
                    temperatures.add(temperatureDTO.getMain().getTemp().intValue());});

            Integer averageTemp = temperatures.stream()
                    .collect(Collectors.averagingInt(Integer::intValue)).intValue();

            city.setAverageTemperature5Days(averageTemp);
            cityService.save(city);

        }catch (RestClientException e){
            e.printStackTrace();
        }
    }

    private void saveTemperature(TemperatureDTO temperatureDTO, City city, DateTimeFormatter formatter) {
        LocalDateTime dateTime = LocalDateTime.parse(temperatureDTO.getDt_txt(), formatter);
        Temperature temperature = new Temperature(dateTime, temperatureDTO.getMain().getTemp(), city);
        repository.save(temperature);
    }

    public List<TemperatureResponseDTO> getForAllCities(Integer numOfDays){
        List<TemperatureResponseDTO> responseList = new ArrayList<>();
        List<City> cities = cityService.getAll();
        LocalDate startDate = LocalDate.now();
        cities.forEach(city -> responseList.addAll(
                        getForCity(city.getName().toLowerCase(Locale.ROOT), numOfDays, startDate)));
        return responseList;
    }

    public List<TemperatureResponseDTO> getForCity(String name, Integer numOfDays, LocalDate startDate){
        City city = cityService.findByName(name);
        List<TemperatureResponseDTO> responseList = new ArrayList<>();

        for(int i = 0; i < numOfDays; i++){
            LocalDate loopDate = startDate.plusDays(i);
            LocalDateTime startDateTime = LocalDateTime.of(loopDate, LocalTime.MIN);
            LocalDateTime endDateTime = LocalDateTime.of(loopDate, LocalTime.MAX);
            List<Temperature> temperaturesForDay = repository.findForCityAndDate(city, startDateTime, endDateTime);
            List<Integer> temperatures = new ArrayList<>();
            temperaturesForDay.forEach(temperature -> temperatures.add((temperature.getTemperature().intValue())));
            Integer averageTemperature = temperatures.stream()
                    .collect(Collectors.averagingInt(Integer::intValue)).intValue();
            responseList.add(mapper.toDTO(city, averageTemperature, loopDate));
        }

        return responseList;
    }

}
