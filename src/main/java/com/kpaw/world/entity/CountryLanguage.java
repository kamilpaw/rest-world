package com.kpaw.world.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@IdClass(CountryLanguageKey.class)
@Table(name = "countrylanguage")
public class CountryLanguage {

	@Id
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH })
	@JoinColumn(name = "CountryCode")
	private Country countryCode;

	@Id
	@Column(name = "Language")
	private String language;

	@Column(name = "IsOfficial", columnDefinition = "ENUM('T','F')")
	@Enumerated(EnumType.STRING)
	private IsOfficial isOfficial;

	@Column(name = "Percentage")
	private Double percentage;

	public CountryLanguage() {

	}

	public CountryLanguage(IsOfficial isOfficial, Double percentage) {
		this.isOfficial = isOfficial;
		this.percentage = percentage;
	}

	public CountryLanguage(Country countryCode, String language, IsOfficial isOfficial, Double percentage) {
		this.countryCode = countryCode;
		this.language = language;
		this.isOfficial = isOfficial;
		this.percentage = percentage;
	}

	public Country getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(Country countryCode) {
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
