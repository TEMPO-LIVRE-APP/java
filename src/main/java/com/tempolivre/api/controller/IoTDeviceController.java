package com.tempolivre.api.controller;

import com.tempolivre.api.entity.IoTDevice;
import com.tempolivre.api.service.IoTDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/devices")
public class IoTDeviceController {

    @Autowired
    private IoTDeviceService ioTDeviceService;

    // GET - ALL
    @GetMapping
    public ResponseEntity<Page<IoTDevice>> listAll(Pageable pageable){
        return ResponseEntity.ok(ioTDeviceService.listDevices(pageable));
    }

    /*
    // GET - ALL
    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<Device>>> listAll(
        Pageable pageable,
        PagedResourcesAssembler<Device> assembler
    ){
        Page<Device> page = deviceService.listDevices(pageable);
        return ResponseEntity.ok(assembler.toModel(page));
    }
    */

    // GET - ID
    @GetMapping("/{id}")
    public ResponseEntity<IoTDevice> findById(@PathVariable String id){
        return ResponseEntity.ok(ioTDeviceService.searchById(id));
    }

    // POST
    @PostMapping
    public ResponseEntity<IoTDevice> register(@RequestBody IoTDevice ioTDevice){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ioTDeviceService.registerDevice(ioTDevice));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<IoTDevice> update(@PathVariable String id, @RequestBody IoTDevice ioTDevice){
        return ResponseEntity.ok(ioTDeviceService.updateDevice(id, ioTDevice));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        ioTDeviceService.deleteDevice(id);
        return ResponseEntity.noContent().build();
    }
}
