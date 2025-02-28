package com.example.barbershop.controller;

import java.util.Optional;

import com.example.barbershop.dto.HorarioDTO;
import com.example.barbershop.entitys.Horarios;
import com.example.barbershop.service.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.barbershop.entitys.Cliente;
import com.example.barbershop.service.ClienteService;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    HorarioService horarioService;

    @PostMapping("/cadastro")
    public ResponseEntity<Cliente> cadastro(@RequestBody Cliente cliente){
         Cliente NovoCliente = clienteService.CriarCliente(cliente);
         return ResponseEntity.ok(NovoCliente);
    }


    @PostMapping("/march")
    public ResponseEntity<?> MarcarHorario(@RequestBody HorarioDTO horario) {

       Horarios NovoHorario = horarioService
               .MarcarHorario(horario.data(), horario.horario(), horario.nome(), horario.BarbeiroNome(), horario.telefone());
       return ResponseEntity.ok(NovoHorario);
    }

   


}
