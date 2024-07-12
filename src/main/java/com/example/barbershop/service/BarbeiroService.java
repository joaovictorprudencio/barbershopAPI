package com.example.barbershop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.barbershop.repository.BarbeiroRepository;

@Service
public class BarbeiroService {
    @Autowired
    BarbeiroRepository barbeiroRepository;

    
}
