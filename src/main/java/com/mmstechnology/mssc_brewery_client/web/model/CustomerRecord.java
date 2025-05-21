package com.mmstechnology.mssc_brewery_client.web.model;

import lombok.Builder;

import java.util.UUID;

@Builder
public record CustomerRecord(UUID customerId, String customerName) {
}
