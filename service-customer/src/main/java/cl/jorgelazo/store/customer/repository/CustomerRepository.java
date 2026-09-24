package cl.jorgelazo.store.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.jorgelazo.store.customer.entity.Customer;
import cl.jorgelazo.store.customer.entity.Region;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    public Customer findByNumberID(String numberID);
    public List<Customer> findByLastName(String lastName);
    public List<Customer> findByRegion(Region region);
    
} 
