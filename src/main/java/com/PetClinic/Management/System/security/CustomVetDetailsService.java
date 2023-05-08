package com.PetClinic.Management.System.security;


import com.PetClinic.Management.System.entity.Role;
import com.PetClinic.Management.System.entity.Vet;
import com.PetClinic.Management.System.repository.VetRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomVetDetailsService implements UserDetailsService {

        private VetRepository vetRepository;

        public CustomVetDetailsService(VetRepository vetRepository) {
            this.vetRepository = vetRepository;
        }

        @Override
        public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
            Vet vet = vetRepository.findByEmailAddress(emailAddress);

            if (vet != null) {
                return new org.springframework.security.core.userdetails.User(vet.getEmailAddress(),
                        vet.getPassword(),
                        mapRolesToAuthorities(vet.getRoles()));
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
