package com.kpaw.world.controller;

import com.kpaw.world.dto.CountryLanguageDTO;
import com.kpaw.world.entity.IsOfficial;
import com.kpaw.world.service.CountryLanguageService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.reset;

@WebMvcTest(CountryLanguageController.class)
class CountryLanguageControllerTest {

    @MockBean
    CountryLanguageService languageService;

    CountryLanguageDTO validLanguage;
    CountryLanguageDTO validLanguage2;
    List<CountryLanguageDTO> allValidLanguages;

    @BeforeEach
    void setUp() {
        validLanguage = new CountryLanguageDTO("POL", "language", IsOfficial.T, 95.0);
        validLanguage2 = new CountryLanguageDTO("POL", "language2", IsOfficial.F, 5.0);
        allValidLanguages = new ArrayList<>();
        allValidLanguages.add(validLanguage);
        allValidLanguages.add(validLanguage2);
    }

    @AfterEach
    void tearDown() {
        reset(languageService);
    }

    @Test
    void showCountryLanguages() {
    }

    @Test
    void searchByLanguageAndCountry() {
    }
}