package ru.vasmirnov.interviewtask.mapper;

import org.mapstruct.Mapper;
import ru.vasmirnov.interviewtask.dto.CreateCustomerDTO;
import ru.vasmirnov.interviewtask.dto.UpdateCustomerDTO;
import ru.vasmirnov.interviewtask.model.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer mapToCustomer(CreateCustomerDTO customer);

    Customer mapToCustomer(UpdateCustomerDTO customer);
}
