package com.kpaw.world.controller;

import com.kpaw.world.dto.CountryDTO;
import com.kpaw.world.service.CountryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CountryController.class)
class CountryControllerTest extends ControllerTest{

    @MockBean
    CountryService countryService;

    CountryDTO validCountry;
    CountryDTO validCountry2;
    List<CountryDTO> allValidCountries;

    @BeforeEach
    void setUp() {
        validCountry = new CountryDTO("code","name", "regoin", 10.0, (short) 100, 10, 100.0, 100.0, 100.0, "localName", "governmentForm", "headOfState", 1, "codeB");
        validCountry2 = new CountryDTO("code2","name2", "regoin2", 10.0, (short) 100, 10, 100.0, 100.0, 100.0, "localName2", "governmentForm", "headOfState", 2, "codeB2");
        allValidCountries = new ArrayList<>();
        allValidCountries.add(validCountry);
        allValidCountries.add(validCountry2);
    }

    @AfterEach
    void tearDown() {
        reset(countryService);
    }

    @Test
    void testShowCountries() throws Exception {
        given(countryService.findAll()).willReturn(allValidCountries);

        MvcResult result = mockMvc.perform(get("/world/countries"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        CountryDTO[] countries = this.mapFromJson(content, CountryDTO[].class);
        assertEquals(countries.length, 2);
    }

    @Test
    void testSearch() throws Exception {
        given(countryService.searchBy(anyString(),anyString(), anyString())).willReturn(allValidCountries);
        MvcResult result = mockMvc.perform(get("/world/countries/search").param("code", "code").param("name","name")
                .param("region","region"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        CountryDTO[] countries = this.mapFromJson(content, CountryDTO[].class);
        assertEquals(countries.length, 2);
    }
}