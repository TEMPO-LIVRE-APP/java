package com.tempolivre.api.service;

import ch.qos.logback.core.read.ListAppender;
import com.tempolivre.api.entity.Alert;
import com.tempolivre.api.entity.enums.AlertaNivel;
import com.tempolivre.api.entity.enums.AlertaStatus;
import com.tempolivre.api.repository.AlertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertaService {

    @Autowired
    private AlertaRepository alertaRepository;

    // Listar todos
    public Page<Alert> listAlertas(Pageable pageable){
        return alertaRepository.findAll(pageable);
    }

    // Listar por id
    public Alert searchById(String id){
        return alertaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Alerta não encontrado"));
    }

    // Listar por Nivel
    public Page<Alert> searchByNivel(String nivel, Pageable pageable){
        List<Alert> alerts = alertaRepository.findByNivel(AlertaNivel.valueOf(nivel));
        return new PageImpl<>(alerts, pageable, alerts.size());
    }

    // Listar por Status
    public Page<Alert> searchByStatus(String status, Pageable pageable){
        List<Alert> alerts = alertaRepository.findByStatus(AlertaStatus.valueOf(status));
        return new PageImpl<>(alerts, pageable, alerts.size());
    }

    // Registrar
    public Alert registerAlerta(Alert alerta){
        return alertaRepository.save(alerta);
    }

    // Atualizar
    public Alert updateAlerta(String id, Alert newAlerta){
        Alert alerta = searchById(id);

        alerta.setTipo(newAlerta.getTipo());
        alerta.setNivel(newAlerta.getNivel());
        alerta.setStatus(newAlerta.getStatus());
        alerta.setMessage(newAlerta.getMessage());

        return alertaRepository.save(alerta);
    }

    // Deletar
    public void deleteAlerta(String id){
        alertaRepository.deleteById(id);
    }
}
