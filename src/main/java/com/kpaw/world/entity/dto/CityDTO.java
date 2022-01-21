package com.kpaw.world.entity.dto;


import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CityDTO {


    @Digits(integer = 9, fraction = 0, message = "ID must be a number")
    private Integer id;
    @NotBlank(message = "Name is required")
    private String name;
    @NotNull(message = "Wrong Country Code")
    private String countryCode;
    @NotBlank(message = "District is required")
    private String district;
    @Digits(integer = 9, fraction = 0, message = "Invalid number")
    @NotNull(message = "Population is required")
    private Integer population;

    public CityDTO(){

    }

    public CityDTO(Integer id, String name, String countryCode,
                   String district, Integer population) {
        this.id = id;
        this.name = name;
        this.countryCode = countryCode;
        this.district = district;
        this.population = population;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }


    @Override
    public String toString() {
        return "CityDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", district='" + district + '\'' +
                ", population=" + population +
                '}';
    }
}
