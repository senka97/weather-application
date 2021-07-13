package com.hybrid.weatherappbackend.dto;

import java.time.LocalDate;

public class TemperatureResponseDTO {

    private CityShowDTO city;

    private Integer averageTemp;

    private LocalDate date;

    public TemperatureResponseDTO(CityShowDTO city, Integer averageTemp, LocalDate date) {
        this.city = city;
        this.averageTemp = averageTemp;
        this.date = date;
    }

    public TemperatureResponseDTO() {
    }

    public CityShowDTO getCity() {
        return city;
    }

    public void setCity(CityShowDTO city) {
        this.city = city;
    }

    public Integer getAverageTemp() {
        return averageTemp;
    }

    public void setAverageTemp(Integer averageTemp) {
        this.averageTemp = averageTemp;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
