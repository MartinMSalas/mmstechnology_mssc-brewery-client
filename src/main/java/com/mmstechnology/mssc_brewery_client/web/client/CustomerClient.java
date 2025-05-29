package com.mmstechnology.mssc_brewery_client.web.client;


import com.mmstechnology.mssc_brewery_client.web.model.CustomerRecord;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
@ConfigurationProperties(prefix = "mmstechnology.brewery", ignoreUnknownFields = false)
public class CustomerClient {
    public final String CUSTOMER_PATH_V1="/api/v1/customer";

    @Setter
    private String apiHost;

    private final RestTemplate restTemplate;

    public CustomerClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();

    }

    public CustomerRecord getCustomerById(UUID customerId) {
        return restTemplate.getForObject(apiHost + CUSTOMER_PATH_V1 + "/" + customerId.toString(), CustomerRecord.class);

    }

    public CustomerRecord saveNewCustomer(CustomerRecord customerRecord) {
        return restTemplate.postForObject(apiHost + CUSTOMER_PATH_V1, customerRecord, CustomerRecord.class);

    }

    public void updateCustomer(UUID customerId, CustomerRecord customerRecord) {
        restTemplate.put(apiHost + CUSTOMER_PATH_V1 + "/" + customerId.toString(), customerRecord, CustomerRecord.class);


    }

    public void deleteCustomer(UUID customerId) {
        restTemplate.delete(apiHost + CUSTOMER_PATH_V1 + "/" + customerId.toString());


    }
}
