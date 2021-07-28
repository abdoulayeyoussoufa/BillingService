package com.techcoffee.techcoffeebillingservice.servicesImp;

import com.techcoffee.techcoffeebillingservice.dto.InvoiceRequestDto;
import com.techcoffee.techcoffeebillingservice.dto.InvoiceResponseDto;
import com.techcoffee.techcoffeebillingservice.entities.Invoice;
import com.techcoffee.techcoffeebillingservice.exceptions.CustomNotFoundException;
import com.techcoffee.techcoffeebillingservice.mappers.InvoiceMapper;
import com.techcoffee.techcoffeebillingservice.modeles.Customer;
import com.techcoffee.techcoffeebillingservice.openFeign.CustmerRestClient;
import com.techcoffee.techcoffeebillingservice.repositories.InvoiceRepository;
import com.techcoffee.techcoffeebillingservice.services.InvoiceService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

    private InvoiceRepository invoiceRepository;
    private InvoiceMapper invoiceMapper;
    private CustmerRestClient custmerRestClient;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper,
                              CustmerRestClient custmerRestClient) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
        this.custmerRestClient = custmerRestClient;
    }

    @Override
    public InvoiceResponseDto save(InvoiceRequestDto invoiceRequestDto) {
        Customer customer = null;
        /*
        V2rifier l'ntegrite referentiel Invoice/ Customer before save it
         */
        try {
            customer = custmerRestClient.getCustmoer(invoiceRequestDto.getCustomerId());
        }catch (Exception e){
            throw new CustomNotFoundException("Customer not found");
        }

        Invoice invoice = invoiceMapper.fromInvoiceRequestDto(invoiceRequestDto);
        invoice.setId(UUID.randomUUID().toString());
        invoice.setDate(new Date());

        Invoice savedInvoice = invoiceRepository.save(invoice);
        savedInvoice.setCustomer(customer);
        return invoiceMapper.fromInvoice(savedInvoice);
    }

    @Override
    public InvoiceResponseDto getInvoice(String id) {
        Invoice invoice = invoiceRepository.getById(id);
        Customer customer = custmerRestClient.getCustmoer(invoice.getCustomerId());
        invoice.setCustomer(customer);
        return invoiceMapper.fromInvoice(invoice);
    }

    @Override
    public List<InvoiceResponseDto> invoiceByCustomer(String customerId) {
        List<Invoice> invoiceList = invoiceRepository.findByCustomerId(customerId);

        return invoiceList.
                stream()
                .map(invoice -> invoiceMapper.fromInvoice(invoice)).collect(Collectors.toList());
    }

    @Override
    public List<InvoiceResponseDto> findAllInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
              for (Invoice invoic : invoices){
                  Customer customer = custmerRestClient.getCustmoer(invoic.getCustomerId());
                   invoic.setCustomer(customer);
              }
        return invoices.stream().map(invoice->
            invoiceMapper.fromInvoice(invoice)
        ).collect(Collectors.toList());
    }
}
