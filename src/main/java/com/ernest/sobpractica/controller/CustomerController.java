package com.ernest.sobpractica.controller;

import com.ernest.sobpractica.jwt.JwtTokenProvider;
import com.ernest.sobpractica.model.Customer;
import com.ernest.sobpractica.model.HttpResponse;
import com.ernest.sobpractica.model.Product;
import com.ernest.sobpractica.model.UserPrincipal;
import com.ernest.sobpractica.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import static com.ernest.sobpractica.constant.SecurityConstant.JWT_TOKEN_HEADER;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/user")
public class CustomerController {

    private AuthenticationManager authenticationManager;
    private CustomerService customerService;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public CustomerController(AuthenticationManager authenticationManager, CustomerService customerService, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.customerService = customerService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<Customer> register(@RequestBody Customer customer) {
        customerService.create(customer);
        return new ResponseEntity<>(customer, CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Customer> login(@RequestBody Customer customer) {
        authenticate(customer.getUsername(), customer.getPassword());
        Customer loginUser = customerService.findByName(customer.getUsername());
        UserPrincipal userPrincipal = new UserPrincipal(loginUser);
        HttpHeaders jwtHeader = getJwtHeader(userPrincipal);
        return new ResponseEntity<>(loginUser, jwtHeader, OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id) {
        Customer customer = customerService.getByID(id);
        return new ResponseEntity<>(customer, CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpResponse> deleteCustomer(@PathVariable("id") Long id) {
        customerService.delete(id);
        return response(HttpStatus.OK, "Customer with ID: " + id + " deleted.");
    }

    /**
     * Auxiliary method for a custom HTTP Response.
     * @param httpStatus
     * @param message
     * @return
     */
    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    private HttpHeaders getJwtHeader(UserPrincipal user) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(user));
        return headers;
    }
}
