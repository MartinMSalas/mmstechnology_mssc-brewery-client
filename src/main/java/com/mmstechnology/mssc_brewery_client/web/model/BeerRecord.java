package com.mmstechnology.mssc_brewery_client.web.model;


import lombok.Builder;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;


@Builder
public record BeerRecord(UUID beerId, Integer version, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate,
                         String beerName, BeerStyleEnum beerStyle, Long upc, BigDecimal price, Integer quantityOnHand){

}
