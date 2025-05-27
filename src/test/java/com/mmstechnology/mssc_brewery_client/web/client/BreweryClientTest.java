package com.mmstechnology.mssc_brewery_client.web.client;

import com.mmstechnology.mssc_brewery_client.web.model.BeerRecord;
import com.mmstechnology.mssc_brewery_client.web.model.BeerStyleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
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

    @Test
    void testSaveNewBeer() {
        BeerRecord beerRecord = BeerRecord.builder()
                .beerId(UUID.randomUUID())
                .beerName("Test Beer")
                .beerStyle(BeerStyleEnum.IPA)
                .upc(123456789012L)
                .price(BigDecimal.valueOf(12.99))
                .quantityOnHand(100)
                .build();



        BeerRecord savedBeer = breweryClient.saveNewBeer(beerRecord);

        assertNotNull(savedBeer);
        assertEquals(beerRecord.beerName(), savedBeer.beerName());
    }
}