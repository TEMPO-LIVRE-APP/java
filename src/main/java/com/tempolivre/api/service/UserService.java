package com.tempolivre.api.service;

import com.tempolivre.api.entity.User;
import com.tempolivre.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Listar Todos
    public Page<User> listUsers(Pageable pageable){
        return userRepository.findAll(pageable);
    }

    // Buscar por Id
    public User searchById(String id){
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Usuários não encontrado"));
    }

    // Buscar por Username
    public User searchByUsername(String username){
        return (User) userRepository.findByUsername(username);
    }

    // Buscar por Email
    public User searchByEmail(String email){
        return (User) userRepository.findByEmail(email);
    }

    // Registrar
    public User registerUser(User user){
        return userRepository.save(user);
    }

    // Atualizar
    public User updateUser(String id, User userUpdated){
        User user = searchById(id);

        user.setRole(userUpdated.getRole());
        user.setName(userUpdated.getName());
        user.setEmail(userUpdated.getEmail());
        user.setUsername(userUpdated.getUsername());
        user.setPassword(userUpdated.getPassword());

        return userRepository.save(user);
    }

    // Deletar
    public void deleteUser(String id){
        userRepository.deleteById(id);
    }
}
