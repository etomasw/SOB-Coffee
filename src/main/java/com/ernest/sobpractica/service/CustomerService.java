package com.ernest.sobpractica.service;

import com.ernest.sobpractica.model.Customer;

public interface CustomerService {

    Customer create(Customer c);
    Customer edit(Customer c);
    void delete(Long id);
    Customer getByID(Long id);
    Customer findByName(String username);
    Customer findByEmail(String email);
}
