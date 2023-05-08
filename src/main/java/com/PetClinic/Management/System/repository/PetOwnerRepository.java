package com.PetClinic.Management.System.repository;


import com.PetClinic.Management.System.entity.PetOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetOwnerRepository extends JpaRepository<PetOwner, Long> {

    PetOwner findByEmailAddress(String emailAddress);

    PetOwner findByIdNumber(Integer idNumber);

}

