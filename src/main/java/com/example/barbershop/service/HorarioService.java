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

  public Horarios MarcarHorario(LocalDate dataDoCorte, LocalTime horario, String nome, String barbeiroNome, String telefone) {
    LocalDate hoje = LocalDate.now();
    LocalTime agora = LocalTime.now();

    Barbeiro barbeiro = barbeiroRepository.findByNome(barbeiroNome)
            .orElseThrow(() -> new BarbeiroException("barbeiro não encontrado"));

      Optional <Horarios> horarioIndisponivelOptional = horarioRepository.validationData(dataDoCorte,horario,"indisponivel");

      Optional <Horarios> horarioDisponivelOptional = horarioRepository.validationData(dataDoCorte,horario,"Disponível");


      if (hoje.isAfter(dataDoCorte)) {
          throw new HorarioException("A data " + dataDoCorte + " está inválida");
      }
      
      
      if(!horarioIndisponivelOptional.isEmpty()) {
        throw new HorarioException("Horario indisponivel");
      }

      if(!horarioDisponivelOptional.isEmpty()){
          Horarios horarioExistente = horarioDisponivelOptional.get();

          Cliente cliente  = clienteService.CriarCliente(
                  new Cliente(nome,telefone)
          );

          horarioExistente.setCliente(cliente);
          horarioExistente.setStatus("indisponivel");

          return horarioRepository.save(horarioExistente);
      }



     Cliente cliente  = clienteService.CriarCliente(
             new Cliente(nome,telefone)
     );



    Horarios marcandoHorario = new Horarios();
    marcandoHorario.setCliente(cliente);
    marcandoHorario.setData(dataDoCorte);
    marcandoHorario.setHorario(horario);
    marcandoHorario.setBarbeiro(barbeiro);
    marcandoHorario.setStatus("indisponivel");

    return horarioRepository.save(marcandoHorario);

  }

  @Scheduled(cron = "0 1 0 * * *", zone="America/Sao_Paulo")
  public void gerarHorariosDiarios() {

    List<Barbeiro> barbeiros = barbeiroRepository.findAll();

    for (Barbeiro barbeiro : barbeiros) {
      gerarHorarios(barbeiro, LocalDate.now(), LocalTime.of(9, 0), LocalTime.of(12, 0));
      gerarHorarios(barbeiro, LocalDate.now(), LocalTime.of(14, 0), LocalTime.of(20, 0));
    }

    limparBanco();
  }

  private List<Horarios> horariosDoDia = new ArrayList<>();

  private void gerarHorarios(Barbeiro barbeiro, LocalDate data, LocalTime inicil, LocalTime fim) {
    Optional<Barbeiro> barbeiroOptinal = barbeiroRepository.findByNome(barbeiro.getNome());
    LocalTime horarioInicil = inicil;
    horariosDoDia.clear();

    if (!barbeiroOptinal.isPresent()) {
      throw new BarbeiroException("O nome de usuário não existe " + barbeiro.getNome());
    }

    while (horarioInicil.isBefore(fim.plusMinutes(30))) {
      boolean horarioExistente = horarioRepository.countByDataAndHorarioAndBarbeiro(data, horarioInicil, barbeiro) > 0;

      if (!horarioExistente) {

        Optional<Cliente> clientePadrao = clienteRepository.findById(7L);


        Horarios novoHorario = new Horarios();
        novoHorario.setBarbeiro(barbeiro);
        novoHorario.setCliente(clientePadrao.orElse(null));
        novoHorario.setData(data);
        novoHorario.setHorario(horarioInicil);
        novoHorario.setStatus("Disponível");

        horariosDoDia.add(novoHorario);
        horarioRepository.save(novoHorario);
      }
      horarioInicil = horarioInicil.plusMinutes(30);
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

      if(horariosMarcadosHoje.isEmpty()){
        throw new HorarioException("não a cortes para hoje: " + hoje);
      }

      return horariosMarcadosHoje;
  }

  private void limparBanco (){
     LocalDate hoje = LocalDate.now();

     horarioRepository.clearDB(hoje);
  }


  public Horarios DesmarcarHorario(Long HorarioId){

      Optional<Horarios> HorarioOptinal = horarioRepository.findById(HorarioId);

      if(!HorarioOptinal.isPresent()){
          throw new HorarioException("O Horario não econtrado");

      }

      Horarios horarioDesmarcar = HorarioOptinal.get();

      horarioDesmarcar.setStatus("Disponível");

      return horarioRepository.save(horarioDesmarcar);

  }

}
