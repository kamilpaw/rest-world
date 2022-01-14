package com.kpaw.world.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kpaw.world.entity.Country;
import com.kpaw.world.service.CountryService;

@Controller
@RequestMapping("/countries")
public class CountryController {

	private CountryService countryService;

	private CountryController(CountryService theCountryService) {
		countryService = theCountryService;
	}

	@GetMapping("/list")
	public String showCountries(Model theModel) {
		List<Country> theCountries = countryService.findAll();
		theModel.addAttribute("countries", theCountries);
		return "countries/list-countries";
	}

	@GetMapping("/search")
	public String search(@RequestParam("code") String theCode, @RequestParam("name") String theName,
			@RequestParam("region") String theRegion, Model theModel) {

		if (theCode.trim().isEmpty() && theName.trim().isEmpty() && theRegion.trim().isEmpty()) {
			return "redirect:/countries/list";
		} else {
			List<Country> theCountries = countryService.searchBy(theCode, theName, theRegion);
			theModel.addAttribute("countries", theCountries);
			return "countries/list-countries";
		}
	}

	@GetMapping("/sortByRegion")
	public String sortByRegion(Model theModel) {
		List<Country> theCountries = countryService.orderByRegion();
		theModel.addAttribute("countries", theCountries);
		return "countries/list-countries";
	}

	@GetMapping("sortByName")
	public String sortByName(Model theModel) {
		List<Country> theCountries = countryService.orderByName();
		theModel.addAttribute("countries", theCountries);
		return "countries/list-countries";
	}

	@GetMapping("/sortBySurface")
	public String sortBySurface(Model theModel) {
		List<Country> theCountries = countryService.orderBySurface();
		theModel.addAttribute("countries", theCountries);
		return "countries/list-countries";
	}

	@GetMapping("/sortByCode")
	public String sortByCode(Model theModel) {
		List<Country> theCountries = countryService.orderByCode();
		theModel.addAttribute("countries", theCountries);
		return "countries/list-countries";
	}

	@GetMapping("/advSearchSite")
	public String advSearchSite() {
		return "countries/advanced-search";
	}
}
