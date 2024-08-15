package com.example.barbershop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.barbershop.entitys.Horarios;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


public interface HorariosRepository extends JpaRepository<Horarios, Long> {


}