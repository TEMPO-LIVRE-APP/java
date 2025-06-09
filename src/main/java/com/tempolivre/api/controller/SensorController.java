package com.tempolivre.api.controller;

import com.tempolivre.api.entity.Sensor;
import com.tempolivre.api.service.SensorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    // GET - ALL
    @GetMapping
    public ResponseEntity<Page<Sensor>> listAll(Pageable pageable){
        return ResponseEntity.ok(sensorService.listDevices(pageable));
    }

    // GET - ID
    @GetMapping("/{id}")
    public ResponseEntity<Sensor> findById(@PathVariable @Valid String id){
        return ResponseEntity.ok(sensorService.searchById(id));
    }

    // POST
    @PostMapping
    public ResponseEntity<Sensor> register(@RequestBody @Valid Sensor sensor){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(sensorService.registerDevice(sensor));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Sensor> update(@PathVariable @Valid String id, @RequestBody @Valid Sensor sensor){
        return ResponseEntity.ok(sensorService.updateDevice(id, sensor));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Valid String id){
        sensorService.deleteDevice(id);
        return ResponseEntity.noContent().build();
    }
}