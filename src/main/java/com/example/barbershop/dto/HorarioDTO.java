package com.example.barbershop.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record HorarioDTO (LocalTime horario, LocalDate data, String nome, String BarbeiroNome, String telefone ) {
}
