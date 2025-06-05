package com.tempolivre.api.controller;

import com.tempolivre.api.entity.Abrigo;
import com.tempolivre.api.service.AbrigoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/abrigos")
public class AbrigoController {

    @Autowired
    private AbrigoService abrigoService;

    // GET ALL / FILTROS
    @GetMapping
    public ResponseEntity<Page<Abrigo>> listAll(
        @RequestParam(required = false) String nome,
        @RequestParam(required = false) String endereco,
        Pageable pageable
    ){
        if(nome != null) {
            return ResponseEntity.ok(abrigoService.searchByNome(nome, pageable));
        }
        if(endereco != null) {
            return ResponseEntity.ok(abrigoService.searchByEndereco(endereco, pageable));
        }
        return ResponseEntity.ok(abrigoService.listAbrigos(pageable));
    }

    // GET - ID
    @GetMapping("/{id}")
    public ResponseEntity<Abrigo> findById(@PathVariable @Valid String id){
        return ResponseEntity.ok(abrigoService.searchById(id));
    }

    // POST
    @PostMapping
    public ResponseEntity<Abrigo> register(@RequestBody @Valid Abrigo abrigo){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(abrigoService.registerAbrigo(abrigo));
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<Abrigo> update(
            @RequestBody @Valid Abrigo abrigo,
            @PathVariable @Valid String id
    ){ return ResponseEntity.ok(abrigoService.updateAbrigo(id, abrigo)); }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Valid String id){
        abrigoService.deleteAbrigo(id);
        return ResponseEntity.noContent().build();
    }

}
