package com.PetClinic.Management.System.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VetDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    private int idNumber;

    @NotEmpty(message = "The Email Address should not be empty")
    @Email
    private String emailAddress;

    @NotEmpty(message = "Home Address field should not be Empty!")
    private String homeAddress;

    @NotEmpty(message = "Password should not be empty")
    private String password;
}
