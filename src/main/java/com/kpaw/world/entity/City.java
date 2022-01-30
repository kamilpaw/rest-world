package com.kpaw.world.entity;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "city")
public class City {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name is required")
    @Column(name = "Name")
    private String name;

    @NotNull(message = "Wrong Country Code")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "CountryCode")
    private Country country;

    @NotBlank(message = "District is required")
    @Column(name = "District")
    private String district;

    @Positive(message = "Population can't be negative number")
    @Digits(integer = 9, fraction = 0, message = "Invalid number")
    @NotNull(message = "Population is required")
    @Column(name = "Population")
    private Integer population;

    public City() {

    }

    public City(String name, Country country, String district, Integer population) {
        this.name = name;
        this.country = country;
        this.district = district;
        this.population = population;
    }

    public City(Integer id, String name, Country country, String district, Integer population) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.district = district;
        this.population = population;
    }

    public City(Integer id, String name, String countryCode, String district, Integer population) {
        this.id = id;
        this.name = name;
        this.country.setCode(countryCode);
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
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

}