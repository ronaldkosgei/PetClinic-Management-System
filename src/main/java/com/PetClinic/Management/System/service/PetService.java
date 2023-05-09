package com.PetClinic.Management.System.service;

import com.PetClinic.Management.System.dto.PetDto;
import com.PetClinic.Management.System.dto.VetDto;
import com.PetClinic.Management.System.entity.Pet;

import java.util.List;

public interface PetService {

    void savePet(PetDto petDto);

    List<PetDto> findAllPets();

    Pet findByIdTag(String idTag);
}
