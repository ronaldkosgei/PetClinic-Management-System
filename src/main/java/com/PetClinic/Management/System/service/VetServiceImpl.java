package com.PetClinic.Management.System.service;

import com.PetClinic.Management.System.dto.VetDto;
import com.PetClinic.Management.System.entity.PetOwner;
import com.PetClinic.Management.System.entity.Role;
import com.PetClinic.Management.System.entity.Vet;
import com.PetClinic.Management.System.repository.RoleRepository;
import com.PetClinic.Management.System.repository.VetRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VetServiceImpl implements VetService {

    private final VetRepository vetRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    public VetServiceImpl(VetRepository vetRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder) {
        this.vetRepository = vetRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Vet findVetsByEmailAddress(String emailAddress) {
        return vetRepository.findByEmailAddress(emailAddress);
    }

    @Override
    public void saveVet(VetDto vetDto) {
        Vet vet = new Vet();
        vet.setName(vetDto.getFirstName() + " " + vetDto.getLastName());
        vet.setEmailAddress(vetDto.getEmailAddress());
        vet.setPassword(passwordEncoder.encode(vetDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_ADMIN");
        if (role == null) {
            role = checkRoleExist();
        }
        vet.setRoles(Arrays.asList(role));
        vetRepository.save(vet);
    }

    @Override
    public Vet findVetByEmailAddress(String emailAddress) {
        return vetRepository.findByEmailAddress(emailAddress);
    }

    @Override
    public List<VetDto> findAllVets() {
        List<Vet> vets = vetRepository.findAll();
        return vets.stream()
                .map((vet) -> mapToVetDto(vet))
                .collect(Collectors.toList());
    }

    @Override
    public Vet findVetIdNumber(int idNumber) {
        return vetRepository.findByIdNumber(idNumber);
    }

    private VetDto mapToVetDto(Vet vet){
        VetDto vetDto = new VetDto();
        String[] str = vet.getName().split(" ");
        vetDto.setFirstName(str[0]);
        vetDto.setLastName(str[1]);
        vetDto.setEmailAddress(vet.getEmailAddress());
        return vetDto;
    }

    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

}
