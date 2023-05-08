package com.PetClinic.Management.System.repository;

import com.PetClinic.Management.System.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    Pet findByPetName(String petName);
}
