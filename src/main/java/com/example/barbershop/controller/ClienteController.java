package com.example.barbershop.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.barbershop.entitys.Cliente;
import com.example.barbershop.service.ClienteService;

@Controller
public class ClienteController {
     
    @Autowired
    ClienteService clienteService;

    @PostMapping("/cadastro")
    public ResponseEntity<Cliente> cada(@RequestBody Cliente cliente){
         Cliente NovoCliente = clienteService.CriarCliente(cliente);
         return ResponseEntity.ok(NovoCliente);
    }

   


}
