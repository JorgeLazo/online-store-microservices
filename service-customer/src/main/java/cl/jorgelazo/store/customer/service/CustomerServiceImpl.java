package cl.jorgelazo.store.customer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.jorgelazo.store.customer.entity.Customer;
import cl.jorgelazo.store.customer.entity.Region;
import cl.jorgelazo.store.customer.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Service 
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findCustomerAll() {
        log.info("Call: findCustomerAll()");
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> findCustomersByRegion(Region region) {
        log.info("Call: findCustomersByRegion()");
        return customerRepository.findByRegion(region);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        log.info("Call: createCustomer()");
        Customer customerDB = customerRepository.findByNumberID(customer.getNumberID());
        if (customerDB != null) {
            return customerDB;
        }   
        customer.setState("CREATED");
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        log.info("Call: updateCustomer()");
        Customer customerDB = getCustomer(customer.getId());
        if (customerDB == null) {
            return null;
        }
        customerDB.setFirstName(customer.getFirstName());
        customerDB.setLastName(customer.getLastName());
        customerDB.setEmail(customer.getEmail());
        customerDB.setPhotoUrl(customer.getPhotoUrl());
        customerDB.setRegion(customer.getRegion());
        customerDB.setState(customer.getState());
        return customerRepository.save(customerDB);
    }

    @Override
    public Customer deleteCustomer(Customer customer) {
        log.info("Call: deleteCustomer()");
        Customer customerDB = getCustomer(customer.getId());
        if (customerDB == null) {
            return null;
        }
        customerDB.setState("DELETED");
        return customerRepository.save(customerDB);
    }

    @Override
    public Customer getCustomer(Long id) {
        log.info("Call: getCustomer()");
        return customerRepository.findById(id).orElse(null);
    }

}
