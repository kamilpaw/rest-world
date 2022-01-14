package com.kpaw.world.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kpaw.world.entity.CountryLanguage;
import com.kpaw.world.service.CountryLanguageService;

@Controller
@RequestMapping("/languages")
public class CountryLanguageController {

	private CountryLanguageService countryLanguageService;

	public CountryLanguageController(CountryLanguageService theCountryLanguageService) {
		countryLanguageService = theCountryLanguageService;
	}

	@GetMapping("/list")
	public String showCountryLanguages(Model theModel) {
		List<CountryLanguage> countryLanguages = countryLanguageService.findAll();
		theModel.addAttribute("languages", countryLanguages);
		return "/languages/list-languages";
	}

	@GetMapping("/search")
	public String search(@RequestParam("language") String theLanguage, @RequestParam("country") String theCountry,
			Model theModel) {
		if (theLanguage.trim().isEmpty() && theCountry.trim().isEmpty()) {
			return "redirect:/languages/list";
		} else {
			List<CountryLanguage> countryLanguages = countryLanguageService.searchBy(theLanguage, theCountry);
			theModel.addAttribute("languages", countryLanguages);
			return "/languages/list-languages";
		}
	}

	@GetMapping("/orderByCountry")
	public String sortByCountry(Model theModel) {
		List<CountryLanguage> countryLanguages = countryLanguageService.orderByName();
		theModel.addAttribute("languages", countryLanguages);
		return "/languages/list-languages";
	}

	@GetMapping("/orderByLanguage")
	public String sortByLanguage(Model theModel) {
		List<CountryLanguage> countryLanguages = countryLanguageService.orderByLanguage();
		theModel.addAttribute("languages", countryLanguages);
		return "/languages/list-languages";
	}

}
