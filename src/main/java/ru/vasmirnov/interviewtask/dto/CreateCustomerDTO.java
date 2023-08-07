package ru.vasmirnov.interviewtask.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static ru.vasmirnov.interviewtask.utils.CustomerValidationConstants.*;

@Data
@Builder
public class CreateCustomerDTO {

    @NotNull
    @Pattern(regexp = TELEPHONE_NUMBER_REGEX)
    private String phoneNumber;

    @Email
    @NotNull
    @Pattern(regexp = NOT_CYRILLIC_REGEX)
    private String email;

    @NotNull
    @Pattern(regexp = TWO_OR_MORE_WORDS_REGEX)
    private String fullName;
}
