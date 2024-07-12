package com.example.barbershop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.barbershop.entitys.Barbeiro;
import com.example.barbershop.repository.BarbeiroRepository;

@Service
public class BarbeiroService {
    @Autowired
    BarbeiroRepository barbeiroRepository;

    public Barbeiro CriarBarbeiro(Barbeiro barbeiro){
        Optional<Barbeiro> barbeiroOptinal = barbeiroRepository.findByNome(barbeiro.getNome());
         
        if(barbeiroOptinal.isPresent()){
            throw new ClienteException("O nome de usuário já está em uso: " + barbeiro.getNome());
        }
         
        return barbeiroRepository.save(barbeiro);
      
    }

    

    
}
