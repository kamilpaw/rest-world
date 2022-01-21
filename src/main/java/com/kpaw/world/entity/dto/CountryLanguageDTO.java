package com.kpaw.world.entity.dto;

import com.kpaw.world.entity.IsOfficial;

public class CountryLanguageDTO {


    private String countryCode;

    private String language;

    private IsOfficial isOfficial;

    private Double percentage;

    public CountryLanguageDTO(){

    }

    public CountryLanguageDTO(String countryCode, String language, IsOfficial isOfficial, Double percentage) {
        this.countryCode = countryCode;
        this.language = language;
        this.isOfficial = isOfficial;
        this.percentage = percentage;
    }


    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public IsOfficial getIsOfficial() {
        return isOfficial;
    }

    public void setIsOfficial(IsOfficial isOfficial) {
        this.isOfficial = isOfficial;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }


}
