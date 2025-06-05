package com.tempolivre.api.controller;

import com.tempolivre.api.entity.Alert;
import com.tempolivre.api.service.AlertaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alertas")
public class AlertaController {

    @Autowired
    private AlertaService alertaService;

    // GET - ALL
    @GetMapping
    public ResponseEntity<Page<Alert>> listAll(Pageable pageable){
        return ResponseEntity.ok(alertaService.listAlertas(pageable));
    }


    // GET - ID
    @GetMapping("/{id}")
    public ResponseEntity<Alert> findById(@PathVariable @Valid String id){
        return ResponseEntity.ok(alertaService.searchById(id));
    }


    // POST
    @PostMapping
    public ResponseEntity<Alert> register(@RequestBody @Valid Alert alert){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(alertaService.registerAlerta(alert));
    }


    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<Alert> update(
            @RequestBody @Valid Alert alert,
            @PathVariable @Valid String id
    ){ return ResponseEntity.ok(alertaService.updateAlerta(id, alert)); }


    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Valid String id){
        alertaService.deleteAlerta(id);
        return ResponseEntity.noContent().build();
    }


}
