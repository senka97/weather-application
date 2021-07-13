package com.hybrid.weatherappbackend.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;

@Entity
public class City implements Comparable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String country;

    private Integer averageTemperature5Days;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<Temperature> temperatureList;

    public City(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public City(Long id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return id.equals(city.id) && name.equals(city.name) && country.equals(city.country) && Objects.equals(averageTemperature5Days, city.averageTemperature5Days);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country, averageTemperature5Days);
    }

    public City() {

    }

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

    public List<Temperature> getTemperatureList() {
        return temperatureList;
    }

    public void setTemperatureList(List<Temperature> temperatureList) {
        this.temperatureList = temperatureList;
    }

    public Integer getAverageTemperature5Days() {
        return averageTemperature5Days;
    }

    public void setAverageTemperature5Days(Integer averageTemperature5Days) {
        this.averageTemperature5Days = averageTemperature5Days;
    }

    @Override
    public int compareTo(Object comparestu) {
        int compareage=((City)comparestu).getAverageTemperature5Days();
        return this.getAverageTemperature5Days()-compareage;
    }
}
