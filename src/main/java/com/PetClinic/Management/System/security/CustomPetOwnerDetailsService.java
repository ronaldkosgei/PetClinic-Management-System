package com.PetClinic.Management.System.security;

import com.PetClinic.Management.System.entity.PetOwner;
import com.PetClinic.Management.System.entity.Role;
import com.PetClinic.Management.System.repository.PetOwnerRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomPetOwnerDetailsService implements UserDetailsService {

    private PetOwnerRepository petOwnerRepository;

    public CustomPetOwnerDetailsService(PetOwnerRepository petOwnerRepository) {
        this.petOwnerRepository = petOwnerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        PetOwner petOwner = petOwnerRepository.findByEmailAddress(emailAddress);

        if (petOwner != null) {
            return new org.springframework.security.core.userdetails.User(petOwner.getEmailAddress(),
                    petOwner.getPassword(),
                    mapRolesToAuthorities(petOwner.getRoles()));
        }else{
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Collection <Role> roles) {
        Collection < ? extends GrantedAuthority> mapRoles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return mapRoles;
    }
}