package com.PetClinic.Management.System.service;

import com.PetClinic.Management.System.dto.PetDto;
import com.PetClinic.Management.System.entity.Pet;
import com.PetClinic.Management.System.repository.PetRepository;
import com.PetClinic.Management.System.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetServiceImpl implements PetService{


    private final PetRepository petRepository;
    private final RoleRepository roleRepository;


    public PetServiceImpl(PetRepository petRepository,
                          RoleRepository roleRepository) {
        this.petRepository = petRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Pet findPetsByPetName(String petName) {
        return petRepository.findByPetName(petName);
    }

    @Override
    public List<PetDto> findAllPets() {
        List<Pet> pets = petRepository.findAll();
        return pets.stream()
                .map((pet) -> mapToPetDto(pet))
                .collect(Collectors.toList());
    }


    private PetDto mapToPetDto(Pet pet){
        PetDto petDto = new PetDto();
        String str = pet.getName();
        petDto.setIdTag(pet.getIdTag());
        return petDto;
    }


}
