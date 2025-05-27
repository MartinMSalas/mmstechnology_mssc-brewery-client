package com.mmstechnology.mssc_brewery_client.web.model;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Builder
public record BeerRecord(
        UUID beerId,
        Integer version,
        OffsetDateTime createdDate,
        OffsetDateTime lastModifiedDate,
        String beerName,
        BeerStyleEnum beerStyle,
        Long upc,
        BigDecimal price,
        Integer quantityOnHand
) {
    /**
     * Compact constructor: if any of the “optional” fields come in null
     * (e.g. via the Lombok builder if you didn’t explicitly set them),
     * we replace them with our defaults here.
     */
    public BeerRecord {
        if (version == null)           version          = 1;
        if (createdDate == null)       createdDate      = OffsetDateTime.now();
        if (lastModifiedDate == null)  lastModifiedDate = OffsetDateTime.now();
        if (upc == null)               upc              = 0L;
        if (price == null)             price            = BigDecimal.ZERO;
        if (quantityOnHand == null)    quantityOnHand   = 0;
    }
}