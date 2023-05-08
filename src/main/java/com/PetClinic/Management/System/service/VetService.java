package com.PetClinic.Management.System.service;

import com.PetClinic.Management.System.dto.VetDto;
import com.PetClinic.Management.System.entity.Vet;

import java.util.List;

public interface VetService {


    Vet findVetsByEmailAddress(String emailAddress);

    void saveVet(VetDto vetDto);


    Vet findVetByEmailAddress(String emailAddress);

    List<VetDto> findAllVets();

    Vet findVetIdNumber(int idNumber);
}

