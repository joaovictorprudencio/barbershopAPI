package com.example.barbershop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.barbershop.entitys.Barbeiro;
import com.example.barbershop.entitys.Horarios;
import java.time.LocalDate;
import java.time.LocalTime;





public interface HorariosRepository extends JpaRepository<Horarios, Long> {
     boolean findByHorario(LocalTime horario);

     @Query("SELECT COUNT(h) FROM Horarios h WHERE h.data = :data AND h.horario = :horario AND h.barbeiro = :barbeiro")
      long countByDataAndHorarioAndBarbeiro(@Param("data") LocalDate data, @Param("horario") LocalTime horario, @Param("barbeiro") Barbeiro barbeiro);
}