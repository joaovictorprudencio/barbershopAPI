package com.example.barbershop.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.scheduling.annotation.Scheduled;
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


  @Autowired
  ClienteService clienteService;

  public Horarios MarcarHorario(LocalDate dataDoCorte, LocalTime horario,String nome , String telefone) {
    LocalDate hoje = LocalDate.now();
    LocalTime agora = LocalTime.now();

    Optional<Barbeiro> barbeiroOptional = barbeiroRepository.findByNome("joão victor");

      if (hoje.isAfter(dataDoCorte)) {
          throw new HorarioException("A data " + dataDoCorte + " está inválida");
      }


     Cliente cliente  = clienteService.CriarCliente(
             new Cliente(nome,telefone)
     );


    Barbeiro barbeiro = barbeiroOptional.get();


    Horarios marcandoHorario = new Horarios();
    marcandoHorario.setCliente(cliente);
    marcandoHorario.setData(dataDoCorte);
    marcandoHorario.setHorario(horario);
    marcandoHorario.setBarbeiro(barbeiro);
    marcandoHorario.setStatus("indisponivel");

    return horarioRepository.save(marcandoHorario);

  }

  @Scheduled(cron = "0 0 0 * * *")
  public void gerarHorariosDiarios() {

    List<Barbeiro> barbeiros = barbeiroRepository.findAll();

    for (Barbeiro barbeiro : barbeiros) {
      gerarHorarios(barbeiro, LocalDate.now(), LocalTime.of(9, 0), LocalTime.of(12, 0));
      gerarHorarios(barbeiro, LocalDate.now(), LocalTime.of(14, 0), LocalTime.of(20, 30));
    }
  }

  private List<Horarios> horariosDoDia = new ArrayList<>();

  private void gerarHorarios(Barbeiro barbeiro, LocalDate data, LocalTime inicil, LocalTime fim) {
    Optional<Barbeiro> barbeiroOptinal = barbeiroRepository.findByNome("joão victor");
    LocalTime Ohorario = inicil;
    horariosDoDia.clear();

    if (!barbeiroOptinal.isPresent()) {
      throw new BarbeiroException("O nome de usuário não existe " + barbeiro.getNome());
    }

    while (Ohorario.isBefore(fim)) {
      boolean horarioExistente = horarioRepository.countByDataAndHorarioAndBarbeiro(data, Ohorario, barbeiro) > 0;

      if (horarioExistente) {

        Horarios novoHorario = new Horarios();
        novoHorario.setBarbeiro(barbeiro);
        novoHorario.setCliente(null);
        novoHorario.setData(data);
        novoHorario.setHorario(Ohorario);
        novoHorario.setStatus("Disponível");

        horariosDoDia.add(novoHorario);
        horarioRepository.save(novoHorario);
      }
      Ohorario = Ohorario.plusMinutes(30);
    }

  }

  public List<Horarios> horariosDisponiveisDia() {
    List<Horarios> disponiveis = new ArrayList<>();

    LocalDate dia =  LocalDate.now();

    List< Horarios> horariosDisponiveis = horarioRepository.findByStatus();

    return horariosDisponiveis;
  }


  public List<Horarios> horariosDeServicoDoDia(){

     LocalDate hoje = LocalDate.now();

      List<Horarios> horariosMarcadosHoje = horarioRepository.findByData(hoje);

      if(!horariosDoDia.isEmpty()){
        throw new HorarioException("não a cortes para hoje:" + hoje);
      }

      return horariosMarcadosHoje;
  }

}
