package com.kpaw.world.controller;

import com.kpaw.world.dto.CityDTO;
import com.kpaw.world.service.CityService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CityController.class)
class CityControllerTest extends ControllerTest {

    @MockBean
    CityService cityService;
    CityDTO validCity;
    CityDTO validCity2;
    List<CityDTO> allValidCities;

    @BeforeEach
    void setUp() {
        validCity = new CityDTO(1, "city", "countryCode", "District", 1);
        validCity2 = new CityDTO(2, "city2", "countryCode2", "District2", 2);
        allValidCities = new ArrayList<>();
        allValidCities.add(validCity);
        allValidCities.add(validCity2);
    }

    @AfterEach
    void teardown() {
        reset(cityService);
    }

    @Test
    void testFindCityById() throws Exception {
        given(cityService.findById(anyInt())).willReturn(validCity);
        MvcResult mvcResult = mockMvc.perform(get("/world/cities/" + validCity.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(validCity.getId())))
                .andExpect(jsonPath("$.district", is(validCity.getDistrict())))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }


    @Test
    void testFindCityByIdThrowExc() throws Exception {
        given(cityService.findById(anyInt())).willThrow(new CityNotFoundException("message"));
       MvcResult mvcResult = mockMvc.perform(get("/world/cities/" + validCity.getId()))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", is("message")))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    void testFindAll() throws Exception {
        given(cityService.findAll()).willReturn(allValidCities);
        MvcResult mvcResult = mockMvc.perform(get("/world/cities"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        CityDTO[] cities = super.mapFromJson(content, CityDTO[].class);
        assertTrue(cities.length > 0);
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    void testAddCity() throws Exception {
        CityDTO city3 = new CityDTO(3, "city3", "countryCode3", "District3", 3);
        MvcResult mvcResult = mockMvc.perform(post("/world/cities").contentType(MediaType.APPLICATION_JSON_VALUE).content(super.mapToJson(city3)))
                .andExpect(status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        city3.setId(0);
        String content = mvcResult.getResponse().getContentAsString();
        CityDTO cityDTO = this.mapFromJson(content, CityDTO.class);
        assertEquals(cityDTO.toString(), city3.toString());
    }

    @Test
    void testUpdateCity() throws Exception {
        validCity.setPopulation(1000);
        MvcResult mvcResult = mockMvc.perform(put("/world/cities").contentType(MediaType.APPLICATION_JSON_VALUE).content(this.mapToJson(validCity)))
                .andExpect(status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        CityDTO cityDTO = this.mapFromJson(content, CityDTO.class);
        assertEquals(cityDTO.toString(), validCity.toString());
    }

    @Test
    void testDeleteCityById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/world/cities/" + validCity2.getId()))
                .andExpect(status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Delete city id - " + validCity2.getId());

    }

    @Test
    void testSearchByNameAndCountry() throws Exception {
        given(cityService.searchBy(anyString(), anyString())).willReturn(allValidCities);
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