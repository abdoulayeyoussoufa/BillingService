package com.techcoffee.techcoffeebillingservice;

import com.techcoffee.techcoffeebillingservice.dto.InvoiceRequestDto;
import com.techcoffee.techcoffeebillingservice.services.InvoiceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
@EnableFeignClients
public class TechCoffeeBillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechCoffeeBillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(InvoiceService invoiceService){

        return args -> {
          invoiceService.save(new InvoiceRequestDto(BigDecimal.
                  valueOf(789222),"Co1"));
            invoiceService.save(new InvoiceRequestDto(BigDecimal.
                    valueOf(989000),"Co1"));
            invoiceService.save(new InvoiceRequestDto(BigDecimal.
                    valueOf(650000),"Co2"));
        };
}
}


