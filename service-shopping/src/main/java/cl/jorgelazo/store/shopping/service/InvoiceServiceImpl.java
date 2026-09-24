package cl.jorgelazo.store.shopping.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.jorgelazo.store.shopping.entity.Invoice;
import cl.jorgelazo.store.shopping.repository.InvoiceItemsRepository;
import cl.jorgelazo.store.shopping.repository.InvoiceRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Service 
public class InvoiceServiceImpl implements InvoiceService {

    private InvoiceRepository invoiceRepository;

    private InvoiceItemsRepository invoiceItemsRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceItemsRepository invoiceItemsRepository) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceItemsRepository = invoiceItemsRepository;
    }

    @Override
    public List<Invoice> findInvoiceAll() {
        return  invoiceRepository.findAll();
    }


    @Override
    public Invoice createInvoice(Invoice invoice) {
        Invoice invoiceDB = invoiceRepository.findByNumberInvoice ( invoice.getNumberInvoice () );
        if (invoiceDB !=null){
            return  invoiceDB;
        }
        invoice.setState("CREATED");
        invoiceDB = invoiceRepository.save(invoice);
        
        return invoiceDB;
    }


    @Override
    public Invoice updateInvoice(Invoice invoice) {
        Invoice invoiceDB = getInvoice(invoice.getId());
        if (invoiceDB == null){
            return  null;
        }
        invoiceDB.setCustomerId(invoice.getCustomerId());
        invoiceDB.setDescription(invoice.getDescription());
        invoiceDB.setNumberInvoice(invoice.getNumberInvoice());
        invoiceDB.getItems().clear();
        invoiceDB.setItems(invoice.getItems());
        return invoiceRepository.save(invoiceDB);
    }


    @Override
    public Invoice deleteInvoice(Invoice invoice) {
        Invoice invoiceDB = getInvoice(invoice.getId());
        if (invoiceDB == null){
            return  null;
        }
        invoiceDB.setState("DELETED");
        return invoiceRepository.save(invoiceDB);
    }

    @Override
    public Invoice getInvoice(Long id) {
        return invoiceRepository.findById(id).orElse(null);
    }

}
