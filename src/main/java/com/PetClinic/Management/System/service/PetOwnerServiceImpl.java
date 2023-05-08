package com.PetClinic.Management.System.service;



import com.PetClinic.Management.System.dto.PetOwnerDto;
import com.PetClinic.Management.System.entity.PetOwner;
import com.PetClinic.Management.System.entity.Role;
import com.PetClinic.Management.System.repository.PetOwnerRepository;
import com.PetClinic.Management.System.repository.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PetOwnerServiceImpl implements PetOwnerService{


    private final PetOwnerRepository petOwnerRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    public PetOwnerServiceImpl(PetOwnerRepository petOwnerRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder) {
        this.petOwnerRepository = petOwnerRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public PetOwner findPetOwnersByEmailAddress(String emailAddress) {
        return petOwnerRepository.findByEmailAddress(emailAddress);
    }

    @Override
    public void savePetOwner(PetOwnerDto petOwnerDto) {
        PetOwner petOwner = new PetOwner();
        petOwner.setFirstName(petOwnerDto.getFirstName());
        petOwner.setLastName(petOwnerDto.getLastName());
        petOwner.setIdNumber(petOwnerDto.getIdNumber());
        petOwner.setEmailAddress(petOwnerDto.getEmailAddress());
        petOwner.setHomeAddress(petOwnerDto.getHomeAddress());
        petOwner.setPassword(passwordEncoder.encode(petOwnerDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_USER");
        if (role == null) {
            role = checkRoleExist();
        }
        petOwner.setRoles(List.of(role));
        petOwnerRepository.save(petOwner);
    }

    @Override
    public List<PetOwnerDto> findAllPetOwners() {
        List<PetOwner> petOwners = petOwnerRepository.findAll();
        return petOwners.stream()
                .map((petOwner) -> mapToPetOwnerDto(petOwner))
                .collect(Collectors.toList());
    }

    @Override
    public PetOwner findPetOwnerIdNumber(int idNumber) {
        return petOwnerRepository.findByIdNumber(idNumber);
    }


    private PetOwnerDto mapToPetOwnerDto(PetOwner petOwner){
        PetOwnerDto petOwnerDto = new PetOwnerDto();
        petOwnerDto.setFirstName(petOwner.getFirstName());
        petOwnerDto.setLastName(petOwner.getLastName());
        petOwnerDto.setEmailAddress(petOwner.getEmailAddress());
        return petOwnerDto;
    }

    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_USER");
        return roleRepository.save(role);
    }

}


