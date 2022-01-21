package com.kpaw.world.controller;

import com.kpaw.world.dto.CountryDTO;
import com.kpaw.world.service.CountryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/world")
public class CountryController {

	private final CountryService countryService;

	private CountryController(CountryService theCountryService) {
		countryService = theCountryService;
	}

	@GetMapping("/countries")
	public List<CountryDTO> showCountries() {
		System.out.println(countryService.findAll().toString());
		return countryService.findAll();
	}

	@GetMapping("/countries/search")
	public List<CountryDTO> search(@RequestParam(defaultValue = "") String code, @RequestParam(defaultValue = "") String name,
								   @RequestParam(defaultValue = "") String region) {

		if (code.trim().isEmpty() && name.trim().isEmpty() && region.trim().isEmpty()) {
			return countryService.findAll();
		} else {
			return countryService.searchBy(code, name, region);
		}
	}
}
