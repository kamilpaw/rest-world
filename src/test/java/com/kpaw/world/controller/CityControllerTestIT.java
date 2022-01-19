package com.kpaw.world.controller;

import com.kpaw.world.dto.CityDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CityControllerTestIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testfindAll() {
        CityDTO[] cityDTOList = restTemplate.getForObject("http://localhost:" + port +"/world/cities", CityDTO[].class);
        assertTrue(cityDTOList.length > 0);
    }
}
