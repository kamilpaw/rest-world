package com.kpaw.world.controller;

import com.kpaw.world.dto.CountryLanguageDTO;
import com.kpaw.world.entity.IsOfficial;
import com.kpaw.world.service.CountryLanguageService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CountryLanguageController.class)
class CountryLanguageControllerTest extends ControllerTest {

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
    void testShowCountryLanguages() throws Exception {
        given(languageService.findAll()).willReturn(allValidLanguages);
        MvcResult mvcResult = mockMvc.perform(get("/world/countrylanguages"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        CountryLanguageDTO[] languages = this.mapFromJson(content, CountryLanguageDTO[].class);
        assertEquals(languages.length, 2);
        System.out.println(mvcResult.getResponse().getContentAsString());

    }

    @Test
    void testSearchByLanguageAndCountry() throws Exception {
        given(languageService.searchBy(anyString(),anyString())).willReturn(allValidLanguages);
        MvcResult mvcResult = mockMvc.perform(get("/world/countrylanguages/search")
                .param("language", "language").param("country", "POL"))
                .andExpect(status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        CountryLanguageDTO[] languages = this.mapFromJson(content, CountryLanguageDTO[].class);
        assertEquals(languages.length, 2);
        System.out.println(mvcResult.getResponse().getContentAsString());
    }
}