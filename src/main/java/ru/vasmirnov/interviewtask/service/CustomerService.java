package ru.vasmirnov.interviewtask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vasmirnov.interviewtask.model.Customer;
import ru.vasmirnov.interviewtask.repository.CustomerRepository;


@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Customer newCustomer, Long id) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setEmail(newCustomer.getEmail());
                    customer.setFullName(newCustomer.getFullName());
                    return customerRepository.save(customer);
                })
                .orElseGet(() -> {
                    newCustomer.setId(id);
                    return customerRepository.save(newCustomer);
                });
    }

    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    public Customer findCustomer(Long id, String phoneNumber) {
        return customerRepository.findByIdOrPhoneNumber(id, phoneNumber);
    }
}
