package com.hybrid.weatherappbackend.dto;

public class TemperatureDTO {

    private SingleTemperatureDTO main;

    private String dt_txt;

    public TemperatureDTO(SingleTemperatureDTO main, String dt_txt) {
        this.main = main;
        this.dt_txt = dt_txt;
    }

    public TemperatureDTO() {
    }

    public SingleTemperatureDTO getMain() {
        return main;
    }

    public void setMain(SingleTemperatureDTO main) {
        this.main = main;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }
}
