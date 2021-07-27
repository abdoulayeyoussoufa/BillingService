package com.techcoffee.techcoffeebillingservice.web;


import com.techcoffee.techcoffeebillingservice.dto.InvoiceRequestDto;
import com.techcoffee.techcoffeebillingservice.dto.InvoiceResponseDto;
import com.techcoffee.techcoffeebillingservice.services.InvoiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class InvoiceRestController {
    private InvoiceService invoiceService;

    public InvoiceRestController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping(path = "/invoices/{id}")
    InvoiceResponseDto getInvoice(@PathVariable(name="id") String invoiceId){
        return  invoiceService.getInvoice(invoiceId);
    }

    @GetMapping(path = "/invoices/{customerId}")
    List<InvoiceResponseDto> getInvoicesByCustomerId(@PathVariable  String customerId){
        return invoiceService.invoiceByCustomer(customerId);
    }

    @PostMapping(path="/invoices")

    InvoiceResponseDto save(@RequestBody InvoiceRequestDto invoiceRequestDto){
      return  invoiceService.save(invoiceRequestDto);
    }

}
