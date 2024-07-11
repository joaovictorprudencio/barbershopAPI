package com.example.barbershop.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.barbershop.entitys.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByNome(String nome);
    
}
