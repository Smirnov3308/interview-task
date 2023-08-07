package ru.vasmirnov.interviewtask.dto;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CustomerValidationTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private final String validPhoneNumber = "79123412342";
    private final String validEmail = "example@example.ru";
    private final String validFullName = "Иван Иванов";

    @Test
    void validCase() {
        CreateCustomerDTO createCustomerDTO = CreateCustomerDTO.builder()
                .phoneNumber(validPhoneNumber)
                .email(validEmail)
                .fullName(validFullName)
                .build();

        Set<ConstraintViolation<CreateCustomerDTO>> violations = validator.validate(createCustomerDTO);
        assertTrue(violations.isEmpty());
    }

    @Test
    void invalidPhoneNumber() {
        CreateCustomerDTO createCustomerDTO = CreateCustomerDTO.builder()
                .phoneNumber("+79123412342")
                .email(validEmail)
                .fullName(validFullName)
                .build();

        Set<ConstraintViolation<CreateCustomerDTO>> violations = validator.validate(createCustomerDTO);
        assertFalse(violations.isEmpty());
        assertEquals("должно соответствовать \"^79\\d{9}$\"", violations.iterator().next().getMessage());
    }

    @Test
    void invalidEmailWithCyrillic() {
        CreateCustomerDTO createCustomerDTO = CreateCustomerDTO.builder()
                .phoneNumber(validPhoneNumber)
                .email("example@example.рф")
                .fullName(validFullName)
                .build();

        Set<ConstraintViolation<CreateCustomerDTO>> violations = validator.validate(createCustomerDTO);
        assertFalse(violations.isEmpty());
        assertEquals("должно соответствовать \"^[^а-яА-Я]+$\"", violations.iterator().next().getMessage());
    }

    @Test
    void invalidEmailWithoutAt() {
        CreateCustomerDTO createCustomerDTO = CreateCustomerDTO.builder()
                .phoneNumber(validPhoneNumber)
                .email("example.сom")
                .fullName(validFullName)
                .build();

        Set<ConstraintViolation<CreateCustomerDTO>> violations = validator.validate(createCustomerDTO);
        assertFalse(violations.isEmpty());
        assertEquals("должно соответствовать \"^[^а-яА-Я]+$\"", violations.iterator().next().getMessage());
    }

    @Test
    void invalidFullNameWithOneWord() {
        CreateCustomerDTO createCustomerDTO = CreateCustomerDTO.builder()
                .phoneNumber(validPhoneNumber)
                .email(validEmail)
                .fullName("Иван")
                .build();

        Set<ConstraintViolation<CreateCustomerDTO>> violations = validator.validate(createCustomerDTO);
        assertFalse(violations.isEmpty());
        assertEquals("должно соответствовать \"^\\p{L}+(\\s\\p{L}+)+$\"", violations.iterator().next().getMessage());
    }

    @Test
    void invalidFullNameIsNull() {
        CreateCustomerDTO createCustomerDTO = CreateCustomerDTO.builder()
                .phoneNumber(validPhoneNumber)
                .email(validEmail)
                .build();

        Set<ConstraintViolation<CreateCustomerDTO>> violations = validator.validate(createCustomerDTO);
        assertFalse(violations.isEmpty());
        assertEquals("не должно равняться null", violations.iterator().next().getMessage());
    }
}
