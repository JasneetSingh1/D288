package com.app.backend.service;

import com.app.backend.model.Cart;
import com.app.backend.model.Customer;
import com.app.backend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer getCustomerById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        return optionalCustomer.orElse(null);
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            // Update the properties of the customer with the properties from updatedCustomer
            customer.setFirstName(updatedCustomer.getFirstName());
            customer.setLastName(updatedCustomer.getLastName());
            customer.setAddress(updatedCustomer.getAddress());
            customer.setPostalCode(updatedCustomer.getPostalCode());
            customer.setPhone(updatedCustomer.getPhone());
            customer.setCreateDate(updatedCustomer.getCreateDate());
            customer.setLastUpdate(updatedCustomer.getLastUpdate());
            customer.setDivision(updatedCustomer.getDivision());
            // Update other properties as needed

            return customerRepository.save(customer);
        } else {
            return null;
        }
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public List<Cart> getCartsByCustomerId(Long customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            return customer.getCarts();
        } else {
            return null;
        }
    }
}

