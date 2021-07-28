package com.techcoffee.techcoffeebillingservice.web;


import com.techcoffee.techcoffeebillingservice.dto.InvoiceRequestDto;
import com.techcoffee.techcoffeebillingservice.dto.InvoiceResponseDto;
import com.techcoffee.techcoffeebillingservice.services.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(path = "/invoicesByCustomer/{customerId}")
    List<InvoiceResponseDto> getInvoicesByCustomerId(@PathVariable  String customerId){
        return invoiceService.invoiceByCustomer(customerId);
    }

    @PostMapping(path="/invoices")

    InvoiceResponseDto save(@RequestBody InvoiceRequestDto invoiceRequestDto){
      return  invoiceService.save(invoiceRequestDto);
    }

    @GetMapping(path = "/invoices")
    List<InvoiceResponseDto> allInvoices(){
        return invoiceService.findAllInvoices();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
