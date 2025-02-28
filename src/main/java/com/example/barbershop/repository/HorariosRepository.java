package com.example.barbershop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.barbershop.entitys.Barbeiro;
import com.example.barbershop.entitys.Horarios;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;


public interface HorariosRepository extends JpaRepository<Horarios, Long> {
     boolean findByHorario(LocalTime horario);

    @Query("""
    SELECT COUNT(h) 
    FROM Horarios h 
    WHERE h.data = :data  AND h.horario = :horario 
        AND h.barbeiro = :barbeiro
""")
      long countByDataAndHorarioAndBarbeiro(
              @Param("data") LocalDate data,
              @Param("horario") LocalTime horario,
              @Param("barbeiro") Barbeiro barbeiro
    );

    @Query(value = "SELECT h FROM Horarios h WHERE h.data = :data")
    List<Horarios> findByData(@Param("data") LocalDate data);

    @Query("""
            SELECT h FROM Horarios h 
            WHERE h.data = :data and h.horario = :horario and h.status = :status
          """)
    Optional <Horarios> validationData(
            @Param("data") LocalDate data,
            @Param("horario") LocalTime horario,
            @Param("status") String status
    );

    @Query("SELECT h FROM Horarios h where h.status = 'Disponivel'")
    List<Horarios> findByStatus();

    @Modifying
    @Transactional
    @Query("DELETE FROM Horarios where data < :data")
    void clearDB(@Param("data") LocalDate data);


 }