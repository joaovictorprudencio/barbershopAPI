package com.example.barbershop.controller;

import com.example.barbershop.Exception.BarbeiroException;
import com.example.barbershop.Exception.HorarioException;
import com.example.barbershop.entitys.Barbeiro;
import com.example.barbershop.entitys.Horarios;
import com.example.barbershop.infraSecurity.TokenService;
import com.example.barbershop.service.BarbeiroService;
import com.example.barbershop.service.ClienteService;
import com.example.barbershop.service.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class BarbeiroController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    BarbeiroService barbeiroService;

    @Autowired
    HorarioService horarioService;

    @Autowired
    TokenService tokenService;



    @GetMapping("/disponiveis")
    public ResponseEntity<List<Horarios>> HorariosDisponiveis(){

        List<Horarios> horariosDisponiveis = horarioService.horariosDisponiveisDia();

        return ResponseEntity.ok(horariosDisponiveis);
    }


    @GetMapping("/horarios")
    public ResponseEntity<List<Horarios>> HorariosMarcadosDoDia(){

       List<Horarios> horarios = horarioService.horariosDeServicoDoDia();

        if(horarios.isEmpty()) {
            throw new HorarioException("Não A horarios para hoje");
        }

        return ResponseEntity.ok(horarios);
    };


    @PutMapping("/desmarcar/{horarioId}")
    public ResponseEntity<Horarios> desmarcarHorario(@PathVariable Long horarioId){

        Horarios horario = horarioService.DesmarcarHorario(horarioId);

        if(horario == null ) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Horario" + horarioId +"Não encontrato");
        }



        return ResponseEntity.ok(horario);


    }
}
