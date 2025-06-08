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
@RequestMapping("/alerts")
public class AlertaController {

    @Autowired
    private AlertaService alertaService;

    // GET - ALL / FILTROS
    @GetMapping
    public ResponseEntity<Page<Alert>> listAll(
            @RequestParam(required = false) String nivel,
            @RequestParam(required = false) String status,
            Pageable pageable
    ){
        if(nivel != null){
            return ResponseEntity.ok(alertaService.searchByNivel(nivel, pageable));
        }
        if(status != null){
            return  ResponseEntity.ok(alertaService.searchByStatus(status, pageable));
        }
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
