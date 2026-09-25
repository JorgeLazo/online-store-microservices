package cl.jorgelazo.store.shopping.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cl.jorgelazo.store.shopping.client.CustomerClient;
import cl.jorgelazo.store.shopping.client.ProductClient;
import cl.jorgelazo.store.shopping.entity.Invoice;
import cl.jorgelazo.store.shopping.entity.InvoiceItem;
import cl.jorgelazo.store.shopping.model.Customer;
import cl.jorgelazo.store.shopping.model.Product;
import cl.jorgelazo.store.shopping.repository.InvoiceItemsRepository;
import cl.jorgelazo.store.shopping.repository.InvoiceRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Service 
public class InvoiceServiceImpl implements InvoiceService {

    private InvoiceRepository invoiceRepository;

    private InvoiceItemsRepository invoiceItemsRepository;

    private CustomerClient customerClient;

    private ProductClient productClient;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceItemsRepository invoiceItemsRepository, CustomerClient customerClient, ProductClient productClient) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceItemsRepository = invoiceItemsRepository;
        this.customerClient = customerClient;
        this.productClient = productClient;
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

        invoiceDB.getItems().forEach( invoiceItem -> {
            productClient.updateStockProduct(invoiceItem.getProductId(), invoiceItem.getQuantity() * -1);
        });
        
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
        Invoice invoice = invoiceRepository.findById(id).orElse(null);
    
        if (null != invoice) {
            Customer customer = customerClient.getCustomer(invoice.getCustomerId()).getBody();
            invoice.setCustomer(customer);

            List<InvoiceItem> lisyItems = invoice.getItems().stream().map(invoiceItem -> {
                Product product = productClient.getProduct(invoiceItem.getProductId()).getBody();
                invoiceItem.setProduct(product);

                return invoiceItem;
            }).collect(Collectors.toList());

            invoice.setItems(lisyItems);
        }

        return  invoice;
    
    }

}
