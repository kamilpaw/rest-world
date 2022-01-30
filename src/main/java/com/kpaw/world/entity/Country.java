package com.kpaw.world.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;


@Entity
@Table(name = "country")
public class Country {

    @Id
    @Column(name = "Code")
    @Pattern(regexp = "^[A-Z]{3}", message = "only 3 capital letters")
    private String code;

    @Column(name = "Name")
    private String name;

    @Column(name = "Region")
    private String region;

    @Column(name = "SurfaceArea")
    private Double surfaceArea;

    @Column(name = "IndepYear")
    private Short indepYear;

    @Column(name = "Population")
    private Integer population;

    @Column(name = "LifeExpectancy")
    private Double lifeExpectancy;

    @Column(name = "GNP")
    private Double gNP;

    @Column(name = "GNPOld")
    private Double gNPOld;

    @Column(name = "LocalName")
    private String localName;

    @Column(name = "GovernmentForm")
    private String governmentForm;

    @Column(name = "HeadOfState")
    private String headOfState;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "Capital")
    private City capital;

    @Column(name = "Code2")
    private String code2;


    public Country() {

    }


    public Country(String code, String name, String region, Double surfaceArea, Short indepYear, Integer population,
                   Double lifeExpectancy, Double gNP, Double gNPOld, String localName, String governmentForm,
                   String headOfState, City capital, String code2) {
        this.code = code;
        this.name = name;
        this.region = region;
        this.surfaceArea = surfaceArea;
        this.indepYear = indepYear;
        this.population = population;
        this.lifeExpectancy = lifeExpectancy;
        this.gNP = gNP;
        this.gNPOld = gNPOld;
        this.localName = localName;
        this.governmentForm = governmentForm;
        this.headOfState = headOfState;
        this.capital = capital;
        this.code2 = code2;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Double getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(Double surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public Short getIndepYear() {
        return indepYear;
    }

    public void setIndepYear(Short indepYear) {
        this.indepYear = indepYear;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Double getLifeExpectancy() {
        return lifeExpectancy;
    }

    public void setLifeExpectancy(Double lifeExpectancy) {
        this.lifeExpectancy = lifeExpectancy;
    }

    public Double getgNP() {
        return gNP;
    }

    public void setgNP(Double gNP) {
        this.gNP = gNP;
    }

    public Double getgNPOld() {
        return gNPOld;
    }

    public void setgNPOld(Double gNPOld) {
        this.gNPOld = gNPOld;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getGovernmentForm() {
        return governmentForm;
    }

    public void setGovernmentForm(String governmentForm) {
        this.governmentForm = governmentForm;
    }

    public String getHeadOfState() {
        return headOfState;
    }

    public void setHeadOfState(String headOfState) {
        this.headOfState = headOfState;
    }

    public City getCapital() {
        return capital;
    }

    public void setCapital(City capital) {
        this.capital = capital;
    }

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }


}
