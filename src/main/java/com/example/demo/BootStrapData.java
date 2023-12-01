package com.example.demo;

import com.example.demo.dao.CountryRepository;
import com.example.demo.dao.CustomerRepository;
import com.example.demo.dao.DivisionRepository;
import com.example.demo.entities.Country;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Division;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner
{
    private final CustomerRepository customerRepository;
    private final DivisionRepository divisionRepository;
    private final CountryRepository countryRepository;

    public BootStrapData(CustomerRepository customerRepository, DivisionRepository divisionRepository, CountryRepository countryRepository)
    {
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public void run(String... args) throws Exception
    {
        //If there are more customers in the db than the default one (John doe) Skip
        if(customerRepository.count() > 1)
        {
            return;
        }

        Customer customer1 = new Customer();
        customer1.setFirstName("John");
        customer1.setLastName("Doe");
        customer1.setAddress("123 Main Street");
        customer1.setPostal_code("12345");
        customer1.setPhone("5551234567");

        Customer customer2 = new Customer();
        customer2.setFirstName("Alice");
        customer2.setLastName("Smith");
        customer2.setAddress("456 Oak Avenue");
        customer2.setPostal_code("67890");
        customer2.setPhone("5559876543");

        Customer customer3 = new Customer();
        customer3.setFirstName("Bob");
        customer3.setLastName("Johnson");
        customer3.setAddress("789 Pine Lane");
        customer3.setPostal_code("54321");
        customer3.setPhone("5551112233");

        Customer customer4 = new Customer();
        customer4.setFirstName("Eva");
        customer4.setLastName("Williams");
        customer4.setAddress("101 Cedar Road");
        customer4.setPostal_code("98765");
        customer4.setPhone("5554445678");

        Customer customer5 = new Customer();
        customer5.setFirstName("Chris");
        customer5.setLastName("Taylor");
        customer5.setAddress("202 Maple Street");
        customer5.setPostal_code("13579");
        customer5.setPhone("5558889999");

        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);
        customerRepository.save(customer4);
        customerRepository.save(customer5);

        customerRepository.findAll();
    }
}
