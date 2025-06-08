package com.tempolivre.api.controller;

import com.tempolivre.api.dto.AuthRequestDTO;
import com.tempolivre.api.dto.AuthResponseDTO;
import com.tempolivre.api.dto.RegisterRequestDTO;
import com.tempolivre.api.entity.User;
import com.tempolivre.api.repository.UserRepository;
import com.tempolivre.api.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity login (@RequestBody @Valid AuthRequestDTO request){
        var usernamePassword = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        var auth = this.authManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterRequestDTO registerRequest){
        if(this.userRepository.findByEmail(registerRequest.email()) != null){
            return ResponseEntity.badRequest().build();
        } else {
            String encryptedPassword = new BCryptPasswordEncoder().encode(registerRequest.password());
            User newUser = new User(
                    registerRequest.role(),
                    registerRequest.name(),
                    registerRequest.email(),
                    registerRequest.username(),
                    encryptedPassword
            );
            this.userRepository.save(newUser);
            return ResponseEntity.ok().build();
        }
    }
}