package com.techcoffee.techcoffeebillingservice.openFeign;

import com.techcoffee.techcoffeebillingservice.modeles.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustmerRestClient {
    @GetMapping(path = "/api/customers/{id}")
    Customer getCustmoer(@PathVariable(name = "id") String id);

    @GetMapping(path = "/api/customers")
    List<Customer> allCustomers();

}
