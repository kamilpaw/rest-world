package com.kpaw.world.controller;

import com.kpaw.world.dto.CityDTO;
import com.kpaw.world.entity.City;
import com.kpaw.world.entity.Country;
import com.kpaw.world.service.CityService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CityController.class)
class CityControllerTest extends ControllerTest {

    @MockBean
    CityService cityService;

    @Mock
    Country country;

    City city1;
    City  city2;
    CityDTO cityDTO1;
    CityDTO cityDTO2;
    List<City> cities;
    List<CityDTO> citiesDTO;

    @BeforeEach
    void setUp() {
        city1 = new City(1, "city", country, "District", 1);
        city2 = new City(2, "city2", country, "District2", 2);
        cityDTO1 = new CityDTO(3, "city3", "countryCode3", "District3", 3);
        cityDTO2 = new CityDTO(4, "city4", "countryCode4", "District4", 4);
        cities = new ArrayList<>();
        cities.add(city1);
        cities.add(city2);
        citiesDTO = new ArrayList<>();
        citiesDTO.add(cityDTO1);
        citiesDTO.add(cityDTO2);
    }

    @AfterEach
    void teardown() {
        reset(cityService);
    }

    @Test
    void testFindCityById() throws Exception {
        given(mapper.toCityDto(cityService.findById(cityDTO1.getId()))).willReturn(cityDTO1);
        MvcResult mvcResult = mockMvc.perform(get("/world/cities/" + cityDTO1.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(cityDTO1.getId())))
                .andExpect(jsonPath("$.district", is(cityDTO1.getDistrict())))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    void testFindAllCities() throws Exception {
        given(cityService.findAll()).willReturn(cities);
        MvcResult mvcResult = mockMvc.perform(get("/world/cities"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        CityDTO[] cities = this.mapFromJson(content, CityDTO[].class);
        assertTrue(cities.length == 2);
    }


    @Test
    void testAddCity() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/world/cities").
                        contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(super.mapToJson(cityDTO1)))
                .andExpect(status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        cityDTO1.setId(0);
        String content = mvcResult.getResponse().getContentAsString();
        CityDTO cityDTO = this.mapFromJson(content, CityDTO.class);
        assertEquals(cityDTO.toString(), cityDTO1.toString());
    }

    @Test
    void testUpdateCity() throws Exception {
        MvcResult mvcResult = mockMvc.perform(put("/world/cities").
                        contentType(MediaType.APPLICATION_JSON_VALUE).
                        content(super.mapToJson(cityDTO1)))
                .andExpect(status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        CityDTO cityDTO = this.mapFromJson(content, CityDTO.class);
        assertEquals(cityDTO.toString(), cityDTO1.toString());
    }

    @Test
    void testDeleteCityById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/world/cities/" + city2.getId()))
                .andExpect(status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Delete city id - " + city2.getId());

    }

    @Test
    void testSearchByNameAndCountry() throws Exception {
        given(cityService.searchBy(anyString(), anyString())).willReturn(cities);
        MvcResult mvcResult = mockMvc.perform(get("/world/cities/search").param("name", "country")
                        .param("country", "countryCode"))
                .andExpect(status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        CityDTO[] cities = this.mapFromJson(content, CityDTO[].class);
        assertEquals(cities.length, 2);
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

}