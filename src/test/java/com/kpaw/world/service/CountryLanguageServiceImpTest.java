package com.kpaw.world.service;

import com.kpaw.world.dao.CountryLanguageRepository;
import com.kpaw.world.entity.Country;
import com.kpaw.world.entity.CountryLanguage;
import com.kpaw.world.entity.IsOfficial;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CountryLanguageServiceImpTest {

    @Mock
    private CountryLanguageRepository countryLanguageRepository;

    @Mock
    Country country;

    @InjectMocks
    CountryLanguageServiceImp service;


    CountryLanguage language, language2;
    List<CountryLanguage> languages;

    @BeforeEach
    void setUp() {
        language = new CountryLanguage(country, "language", IsOfficial.T, 95.0);
        language2 = new CountryLanguage(country, "language2", IsOfficial.F, 5.0);
        languages = new ArrayList<>();
        languages.add(language);
        languages.add(language2);
    }

    @Test
    void findAll() {
        given(countryLanguageRepository.findAll()).willReturn(languages);
        service.findAll();
        then(countryLanguageRepository).should().findAll();
        assertTrue(countryLanguageRepository.findAll().size() == 2);
    }

    @Test
    void searchBy() {
        service.searchBy("language", "country");
        then(countryLanguageRepository).should().searchBy("language", "country");
    }
}