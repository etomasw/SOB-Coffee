package com.ernest.sobpractica.service.impl;

import com.ernest.sobpractica.enumeration.Role;
import com.ernest.sobpractica.model.Customer;
import com.ernest.sobpractica.model.UserPrincipal;
import com.ernest.sobpractica.repository.CustomerRepository;
import com.ernest.sobpractica.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.ernest.sobpractica.enumeration.Role.ROLE_USER;

@Service
@Transactional
@Qualifier("userDetailsService")
public class CustomerServiceImpl implements CustomerService, UserDetailsService {

    private CustomerRepository customerRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, BCryptPasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUsername(username);
        if(customer == null) {
            LOGGER.error("No user found with username: " + username);
            throw new UsernameNotFoundException("NO USER FOUND WITH USERNAME: " + username);
        }
        UserPrincipal userPrincipal = new UserPrincipal(customer);
        LOGGER.info("Found user with username: " + username);
        return userPrincipal;
    }

    @Override
    public Customer create(Customer c) {
        Customer customer = new Customer();
        LOGGER.info("Usuari " + c.getUsername() + "  creat amb password: " + c.getPassword());
        customer.setEmail(c.getEmail());
        customer.setName(c.getName());
        customer.setLastName(c.getLastName());
        customer.setPhone(c.getPhone());
        customer.setUsername(c.getUsername());
        customer.setPassword(encodePassword(c.getPassword()));
        customer.setRole(ROLE_USER.name());
        customer.setAuthorities(ROLE_USER.getAuthorities());
        customerRepository.save(customer);
        return customer;
    }

    @Override
    public Customer edit(Customer c) {
        return customerRepository.save(c);
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Customer getByID(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer findByName(String username) {
        return customerRepository.findByUsername(username);
    }

    @Override
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private Role getRoleEnumName(String role) {
        return Role.valueOf(role.toUpperCase());
    }

}
