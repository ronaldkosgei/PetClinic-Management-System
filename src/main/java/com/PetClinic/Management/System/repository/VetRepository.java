package com.PetClinic.Management.System.repository;

import com.PetClinic.Management.System.entity.Vet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VetRepository extends JpaRepository<Vet, Long> {

    Vet findByIdNumber(int idNumber);

    Vet findByEmailAddress(String emailAddress);
}
