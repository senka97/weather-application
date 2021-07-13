package com.hybrid.weatherappbackend.dto;

import java.util.List;

public class APIResponseDTO {

    List<TemperatureDTO> list;

    public APIResponseDTO(List<TemperatureDTO> list) {
        this.list = list;
    }

    public APIResponseDTO() {
    }

    public List<TemperatureDTO> getList() {
        return list;
    }

    public void setList(List<TemperatureDTO> list) {
        this.list = list;
    }
}
