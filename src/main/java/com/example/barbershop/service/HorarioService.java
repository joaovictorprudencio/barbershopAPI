package com.example.barbershop.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public String ValidaçãoDisponibilidade(LocalDateTime inicilCorte,LocalDate dataDoCorte){
      
       horarioRepository.equals();
       
    }



  public Horarios MarcarHorario (Barbeiro barbeiro ,LocalDate dataDoCorte , LocalDateTime inicilCorte , LocalDateTime finalCorte , Cliente cliente ){
    Optional<Barbeiro> barbeiroOptinal = barbeiroRepository.findByNome(barbeiro.getNome());
    Optional<Cliente> clienteOptinal = clienteRepository.findByNome(cliente.getNome());
      if (!barbeiroOptinal.isPresent()) {
        throw new BarbeiroException("O nome de usuário não existe " + barbeiro.getNome());
      }

      if (!clienteOptinal.isPresent()) {
         throw new ClienteException("O nome de usuário já está em uso: " + cliente.getNome());
      }

      LocalDate hoje = LocalDate.now();

      if(hoje.isBefore(dataDoCorte)){
       throw new  HorarioException(" data " + dataDoCorte + " está invalida");
      }

      if(){

      }

       Barbeiro barbeiroExistente = barbeiroOptinal.get();
       Cliente clienteExistente  = clienteOptinal.get();

      Horarios marcandoHorario = new Horarios();
      marcandoHorario.setBarbeiro(barbeiroExistente);
      marcandoHorario.setCliente(clienteExistente);
      marcandoHorario.setData(dataDoCorte);
      marcandoHorario.setHorarioinicil(inicilCorte);
      marcandoHorario.setHorariofim(finalCorte);
      marcandoHorario.setStatus("Marcado");

    
      


    return"ok";
  }







}
