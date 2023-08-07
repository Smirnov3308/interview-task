package ru.vasmirnov.interviewtask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.vasmirnov.interviewtask.dto.CreateCustomerDTO;
import ru.vasmirnov.interviewtask.dto.FindCustomerDTO;
import ru.vasmirnov.interviewtask.dto.UpdateCustomerDTO;
import ru.vasmirnov.interviewtask.mapper.CustomerMapper;
import ru.vasmirnov.interviewtask.model.Customer;
import ru.vasmirnov.interviewtask.service.CustomerService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerMapper mapper;

    @GetMapping("/find")
    public Customer findCustomer(@RequestBody @Valid FindCustomerDTO customerDTO) {
        return customerService.findCustomer(customerDTO.getId(), customerDTO.getPhoneNumber());
    }

    @PostMapping()
    public Customer createCustomer(@RequestBody @Valid CreateCustomerDTO customerDTO) {
        Customer customer = mapper.mapToCustomer(customerDTO);
        return customerService.createCustomer(customer);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@RequestBody @Valid UpdateCustomerDTO customerDTO, @PathVariable Long id) {
        Customer customer = mapper.mapToCustomer(customerDTO);
        return customerService.updateCustomer(customer, id);
    }

    @DeleteMapping("/{id}")
    void deleteCustomer(@PathVariable Long id) {
        customerService.deleteById(id);
    }
}
