package com.example.barbershop.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.barbershop.dto.LoginRequestDTO;
import com.example.barbershop.dto.RegisterRequestDTO;
import com.example.barbershop.dto.ResponseDTO;
import com.example.barbershop.entitys.Barbeiro;
import com.example.barbershop.infraSecurity.TokenService;
import com.example.barbershop.repository.BarbeiroRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final BarbeiroRepository barbeiroRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;



    public AuthController(BarbeiroRepository barbeiroRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.barbeiroRepository = barbeiroRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }
     @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body){
      Barbeiro usuario = this.barbeiroRepository.findByEmail(body.email()).orElseThrow( () -> new RuntimeException("usuario não encontrado")); 
      if(passwordEncoder.matches(body.password(), usuario.getSenha())){
        String token = this.tokenService.generateToken(usuario);
        return ResponseEntity.ok( new  ResponseDTO(usuario.getNome(), token));
      }

      return ResponseEntity.badRequest().build();
    }

    @PostMapping("/registre")
    public ResponseEntity cadastro(@RequestBody RegisterRequestDTO body){
        Optional<Barbeiro> usuario = this.barbeiroRepository.findByEmail(body.email());
        if(usuario.isEmpty()){
          Barbeiro barbeiro = new Barbeiro();
           barbeiro.setSenha(passwordEncoder.encode(body.senha()));
           barbeiro.setEmail(body.email());
           barbeiro.setNome(body.nome());
           this.barbeiroRepository.save(barbeiro);

          String token = this.tokenService.generateToken(barbeiro);
          return ResponseEntity.ok(new ResponseDTO(barbeiro.getNome(), token));
        }
        return ResponseEntity.badRequest().build();

      }
   




}
