package com.mmstechnology.mssc_brewery_client.web.client;

import com.mmstechnology.mssc_brewery_client.web.model.CustomerRecord;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest
public class CustomerClientTest {


    @Autowired
    CustomerClient customerClient;

    @Test
    void getCustomerById() {
        CustomerRecord customerRecord = customerClient.getCustomerById(UUID.randomUUID());
        log.info(customerRecord.toString());
        assertNotNull(customerRecord);
    }

    @Test
    void saveNewCustomer() {
        CustomerRecord customerRecord = CustomerRecord.builder()
                .customerId(UUID.randomUUID())
                .customerName("Test Customer")
                .build();

        CustomerRecord savedCustomer = customerClient.saveNewCustomer(customerRecord);


        assertEquals(customerRecord.customerId(), savedCustomer.customerId());
        assertEquals(customerRecord.customerName(), savedCustomer.customerName());


    }

    @Test
    void updateCustomer() {

        CustomerRecord customerRecord = CustomerRecord.builder()
                .customerId(UUID.randomUUID())
                .customerName("Test Customer")
                .build();

        customerClient.updateCustomer(UUID.randomUUID(), customerRecord);
    }

    @Test
    void deleteCustomer() {

        customerClient.deleteCustomer(UUID.randomUUID());
    }
}
