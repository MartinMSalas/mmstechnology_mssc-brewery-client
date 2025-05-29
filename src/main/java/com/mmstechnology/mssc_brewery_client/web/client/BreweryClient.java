package com.mmstechnology.mssc_brewery_client.web.client;

import com.mmstechnology.mssc_brewery_client.web.model.BeerRecord;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
@ConfigurationProperties(prefix = "mmstechnology.brewery", ignoreUnknownFields = false)
public class BreweryClient {

    public final String BEER_PATH_V1="/api/v1/beer";

    @Setter
    private String apiHost;

    private final RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();

    }

    public BeerRecord getBeerById(UUID beerId) {
        return restTemplate.getForObject(apiHost + BEER_PATH_V1 + "/" + beerId.toString(), BeerRecord.class);
    }

    public BeerRecord saveNewBeer(BeerRecord beerRecord) {
        return restTemplate.postForObject(apiHost + BEER_PATH_V1, beerRecord, BeerRecord.class);
    }


    public void updateBeer(UUID beerId, BeerRecord beerRecord) {
        String url = apiHost + BEER_PATH_V1 + "/" + beerId;
        restTemplate.put(url, beerRecord);
    }
    public void deleteBeer(UUID beerId) {
        restTemplate.delete(apiHost + BEER_PATH_V1 + "/" + beerId.toString());
    }
}
