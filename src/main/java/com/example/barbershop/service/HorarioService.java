package com.example.barbershop.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.barbershop.Exception.BarbeiroException;
import com.example.barbershop.Exception.ClienteException;
import com.example.barbershop.Exception.HorarioException;
import com.example.barbershop.entitys.Barbeiro;
import com.example.barbershop.entitys.Cliente;
import com.example.barbershop.entitys.Horarios;
import com.example.barbershop.repository.BarbeiroRepository;
import com.example.barbershop.repository.ClienteRepository;
import com.example.barbershop.repository.HorariosRepository;

@Service
public class HorarioService {
  @Autowired
  HorariosRepository horarioRepository;

  @Autowired
  BarbeiroRepository barbeiroRepository;

  @Autowired
  ClienteRepository clienteRepository;

  public Horarios MarcarHorario(Barbeiro barbeiro, LocalDate dataDoCorte, LocalTime horario, Cliente cliente) {
    Optional<Barbeiro> barbeiroOptinal = barbeiroRepository.findByNome(barbeiro.getNome());
    Optional<Cliente> clienteOptinal = clienteRepository.findByNome(cliente.getNome());
    if (!barbeiroOptinal.isPresent()) {
      throw new BarbeiroException("O nome de usuário não existe " + barbeiro.getNome());
    }

    if (!clienteOptinal.isPresent()) {
      throw new ClienteException("O nome de usuário já está em uso: " + cliente.getNome());
    }

    LocalDate hoje = LocalDate.now();

    if (hoje.isBefore(dataDoCorte)) {
      throw new HorarioException(" a data " + dataDoCorte + " está invalida");
    }

    LocalTime agora = LocalTime.now();
    
    if (agora.isBefore(horario)) {
      throw new HorarioException("O horario:" + horario + " é invalido");
    }

    Barbeiro barbeiroExistente = barbeiroOptinal.get();
    Cliente clienteExistente = clienteOptinal.get();

    Horarios marcandoHorario = new Horarios();
    marcandoHorario.setBarbeiro(barbeiroExistente);
    marcandoHorario.setCliente(clienteExistente);
    marcandoHorario.setData(dataDoCorte);
    marcandoHorario.setHorario(horario);
    marcandoHorario.setStatus("indisponivel");
    Horarios SalvandoHorario = horarioRepository.save(marcandoHorario);

    return SalvandoHorario;
  }

   private List<Horarios> horariosDoDia = new ArrayList<>(); 

   public List<Horarios> horariosDoDia(){
       return  horariosDoDia;
   }

  @SuppressWarnings("unused")
  private void gerarHorarios(Barbeiro barbeiro , LocalDate data , LocalTime inicil, LocalTime fim) {
    LocalTime Ohorario = inicil;

    while(Ohorario.isBefore(fim)){
      Horarios novoHorario = new Horarios();
       novoHorario.setBarbeiro(barbeiro);
       novoHorario.setCliente(null);
       novoHorario.setData(data);
       novoHorario.setHorario(Ohorario);
       novoHorario.setStatus("Disponível");
       horariosDoDia.add(novoHorario);
    }
}












}
