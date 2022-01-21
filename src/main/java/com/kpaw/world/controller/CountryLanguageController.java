package com.kpaw.world.controller;

import com.kpaw.world.dto.CountryLanguageDTO;
import com.kpaw.world.service.CountryLanguageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/world")
public class CountryLanguageController {

	private final CountryLanguageService countryLanguageService;

	public CountryLanguageController(CountryLanguageService theCountryLanguageService) {
		countryLanguageService = theCountryLanguageService;
	}

	@GetMapping("/countrylanguages")
	public List<CountryLanguageDTO> showCountryLanguages() {
		return countryLanguageService.findAll();
	}

	@GetMapping("/countrylanguages/search")
	public List<CountryLanguageDTO> searchByLanguageAndCountry(@RequestParam(defaultValue = "") String language,
											 @RequestParam(defaultValue = "") String country) {
		if (language.trim().isEmpty() && country.trim().isEmpty()) {
			return countryLanguageService.findAll();
		} else {
			return countryLanguageService.searchBy(language, country);
		}
	}

}
