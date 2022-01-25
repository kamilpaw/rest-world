package com.kpaw.world.controller;

import com.kpaw.world.dto.CountryDTO;
import com.kpaw.world.dto.Mapper;
import com.kpaw.world.service.CountryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/world")
public class CountryController {

	private final CountryService countryService;
	private final Mapper mapper;

	private CountryController(CountryService theCountryService, Mapper theMapper) {
		countryService = theCountryService;
		mapper = theMapper;
	}

	@GetMapping("/countries")
	public List<CountryDTO> showCountries() {
		return countryService.findAll().stream().map(mapper::toCountryDTO).collect(Collectors.toList());
	}

	@GetMapping("/countries/search")
	public List<CountryDTO> search(@RequestParam(defaultValue = "") String code, @RequestParam(defaultValue = "") String name,
								   @RequestParam(defaultValue = "") String region) {

		if (code.trim().isEmpty() && name.trim().isEmpty() && region.trim().isEmpty()) {
			return countryService.findAll().stream().map(mapper::toCountryDTO).collect(Collectors.toList());
		} else {
			return countryService.searchBy(code, name, region).stream().map(mapper::toCountryDTO).collect(Collectors.toList());
		}
	}
}
