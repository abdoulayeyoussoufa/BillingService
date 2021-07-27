package com.techcoffee.techcoffeebillingservice.services;

import com.techcoffee.techcoffeebillingservice.dto.InvoiceRequestDto;
import com.techcoffee.techcoffeebillingservice.dto.InvoiceResponseDto;

import java.util.List;

public interface InvoiceService {
    InvoiceResponseDto save(InvoiceRequestDto invoiceRequestDto);
    InvoiceResponseDto getInvoice(String id);
    List<InvoiceResponseDto> invoiceByCustomer(String customerId);
}
