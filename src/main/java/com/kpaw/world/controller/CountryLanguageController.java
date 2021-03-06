package com.kpaw.world.controller;

import com.kpaw.world.dto.CountryLanguageDTO;
import com.kpaw.world.dto.Mapper;
import com.kpaw.world.service.CountryLanguageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/world")
public class CountryLanguageController {

	private final CountryLanguageService countryLanguageService;
	private final Mapper mapper;

	public CountryLanguageController(CountryLanguageService theCountryLanguageService, Mapper theMapper) {
		countryLanguageService = theCountryLanguageService;
		mapper = theMapper;
	}

	@GetMapping("/countrylanguages")
	public List<CountryLanguageDTO> showCountryLanguages() {
		return countryLanguageService.findAll().stream().map(mapper::toCountryLanguageDTO).collect(Collectors.toList());
	}

	@GetMapping("/countrylanguages/search")
	public List<CountryLanguageDTO> searchByLanguageAndCountry(@RequestParam(defaultValue = "") String language,
											 @RequestParam(defaultValue = "") String country) {
		if (language.trim().isEmpty() && country.trim().isEmpty()) {
			return countryLanguageService.findAll().stream().map(mapper::toCountryLanguageDTO).collect(Collectors.toList());
		} else {
			return countryLanguageService.searchBy(language, country).stream().map(mapper::toCountryLanguageDTO).collect(Collectors.toList());
		}
	}

}
