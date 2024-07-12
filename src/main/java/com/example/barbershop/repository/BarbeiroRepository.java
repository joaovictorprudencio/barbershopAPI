package com.example.barbershop.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.barbershop.entitys.Barbeiro;

public interface BarbeiroRepository extends JpaRepository<Barbeiro, Long> {

    Optional<Barbeiro> findByNome(String nome);
    
}