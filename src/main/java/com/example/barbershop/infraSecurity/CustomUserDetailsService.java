package com.example.barbershop.infraSecurity;

import java.util.ArrayList;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.barbershop.entitys.Barbeiro;
import com.example.barbershop.repository.BarbeiroRepository;


 @Service
public class CustomUserDetailsService implements UserDetailsService {

    private BarbeiroRepository barbeiroRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Barbeiro user = this.barbeiroRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getSenha(),
                new ArrayList<>());
    }
}
