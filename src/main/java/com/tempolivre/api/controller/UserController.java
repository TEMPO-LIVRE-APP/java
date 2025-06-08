package com.tempolivre.api.controller;

import com.tempolivre.api.entity.User;
import com.tempolivre.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // GET - ALL
    @GetMapping
    public ResponseEntity<Page<User>> listAll(Pageable pageable){
        return ResponseEntity.ok(userService.listUsers(pageable));
    }

    // GET - ID
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable @Valid String id){
        return ResponseEntity.ok(userService.searchById(id));
    }

    // GET - EMAIL
    @GetMapping("/email/{email}")
    public ResponseEntity<User> findByEmail(@PathVariable @Valid String email){
        return ResponseEntity.ok(userService.searchByEmail(email));
    }

    // GET - USERNAME
    @GetMapping("/username/{username}")
    public ResponseEntity<User> findByUsername(@PathVariable @Valid String username){
        return ResponseEntity.ok(userService.searchByUsername(username));
    }

    // POST
    @PostMapping
    public ResponseEntity<User> register(@RequestBody @Valid User user){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.registerUser(user));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<User> update(
            @RequestBody @Valid User user,
            @PathVariable @Valid String id
    ){ return ResponseEntity.ok(userService.updateUser(id, user)); }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Valid String id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
