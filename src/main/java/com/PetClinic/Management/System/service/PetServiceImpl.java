package com.PetClinic.Management.System.service;

import com.PetClinic.Management.System.dto.PetDto;
import com.PetClinic.Management.System.dto.VetDto;
import com.PetClinic.Management.System.entity.Pet;
import com.PetClinic.Management.System.entity.Role;
import com.PetClinic.Management.System.entity.Vet;
import com.PetClinic.Management.System.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetServiceImpl implements PetService{


    private final PetRepository petRepository;


    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public void savePet(PetDto petDto) {
        Pet pet = new Pet();
        pet.setPetName(petDto.getPetName());
        pet.setAge(petDto.getAge());
        pet.setIdTag(petDto.getIdTag());
        petRepository.save(pet);
    }

    @Override
    public List<PetDto> findAllPets() {
        List<Pet> pets = petRepository.findAll();
        return pets.stream()
                .map((pet) -> mapToPetDto(pet))
                .collect(Collectors.toList());
    }

    @Override
    public Pet findByIdTag(String idTag) {
        return petRepository.findByIdTag(idTag);
    }


    private PetDto mapToPetDto(Pet pet){
        PetDto petDto = new PetDto();
        petDto.setPetName(pet.getPetName());
        petDto.setAge(pet.getAge());
        petDto.setIdTag(pet.getIdTag());
        return petDto;
    }


}
