package com.hybrid.weatherappbackend.dto;

public class SingleTemperatureDTO {

    private Double temp;

    public SingleTemperatureDTO(Double temp) {
        this.temp = temp;
    }

    public SingleTemperatureDTO() {
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }
}
