package com.hybrid.weatherappbackend.dto;

public class CityShowDTO {

    private Long id;

    private String name;

    private String country;

    private Integer averageTemperature5Days;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getAverageTemperature5Days() {
        return averageTemperature5Days;
    }

    public void setAverageTemperature5Days(Integer averageTemperature5Days) {
        this.averageTemperature5Days = averageTemperature5Days;
    }
}
