package cl.jorgelazo.store.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.jorgelazo.store.shopping.entity.InvoiceItem;

public interface InvoiceItemsRepository extends JpaRepository<InvoiceItem, Long> {

}
