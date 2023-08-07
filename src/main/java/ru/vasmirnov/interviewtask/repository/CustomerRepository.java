package ru.vasmirnov.interviewtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vasmirnov.interviewtask.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByIdOrPhoneNumber(Long id, String phoneNumber);
}
