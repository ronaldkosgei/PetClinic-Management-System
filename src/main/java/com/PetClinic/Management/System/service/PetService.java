package com.PetClinic.Management.System.service;

import com.PetClinic.Management.System.dto.PetDto;
import com.PetClinic.Management.System.entity.Pet;

import java.util.List;

public interface PetService {
    Pet findPetsByPetName(String petName);

    List<PetDto> findAllPets();
}
