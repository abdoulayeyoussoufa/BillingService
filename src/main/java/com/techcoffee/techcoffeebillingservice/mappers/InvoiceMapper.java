package com.techcoffee.techcoffeebillingservice.mappers;

import com.techcoffee.techcoffeebillingservice.dto.InvoiceRequestDto;
import com.techcoffee.techcoffeebillingservice.dto.InvoiceResponseDto;
import com.techcoffee.techcoffeebillingservice.entities.Invoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    Invoice fromInvoiceRequestDto(InvoiceRequestDto invoiceRequestDto);
    InvoiceResponseDto fromInvoice(Invoice invoice);
}
