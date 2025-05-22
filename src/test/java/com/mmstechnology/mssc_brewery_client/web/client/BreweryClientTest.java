package com.mmstechnology.mssc_brewery_client.web.client;

import com.mmstechnology.mssc_brewery_client.web.model.BeerRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BreweryClientTest {

    @Autowired
    BreweryClient breweryClient;

    @Test
    void getBeerById() {
        BeerRecord beerRecord = breweryClient.getBeerById(UUID.randomUUID());
        assertNotNull(beerRecord);
    }
}