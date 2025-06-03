package com.tempolivre.api.controller;

import com.tempolivre.api.entity.User;
import com.tempolivre.api.service.UserService;
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
    public ResponseEntity<User> findById(@PathVariable String id){
        return ResponseEntity.ok(userService.searchById(id));
    }

    // GET - USERNAME

    // POST
    @PostMapping
    public ResponseEntity<User> register(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.registerUser(user));
    }

    // UPDATE

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
