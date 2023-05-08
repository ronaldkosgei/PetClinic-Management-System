package com.PetClinic.Management.System.service;

import com.PetClinic.Management.System.dto.PetOwnerDto;
import com.PetClinic.Management.System.entity.PetOwner;

import java.util.List;

public interface PetOwnerService {

    void savePetOwner(PetOwnerDto petOwnerDto);

    PetOwner findPetOwnersByEmailAddress(String emailAddress);

    List<PetOwnerDto> findAllPetOwners();

    PetOwner findPetOwnerIdNumber(int idNumber);
}
