package com.techcoffee.techcoffeebillingservice.dto;

import com.techcoffee.techcoffeebillingservice.modeles.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor
public class InvoiceRequestDto {

    private BigDecimal amount;
    private String customerId;

}
