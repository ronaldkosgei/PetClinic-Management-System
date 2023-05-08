package com.PetClinic.Management.System.dto;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PetDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long is;

    @NotEmpty(message = "The Pet name should not be Empty!")
    private String petName;

    @NotEmpty
    private  String name;

    @NotEmpty(message = "The Id tag should not be Empty!")
    private String idTag;
}
