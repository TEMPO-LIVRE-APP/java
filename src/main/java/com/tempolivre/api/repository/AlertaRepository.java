package com.tempolivre.api.repository;

import com.tempolivre.api.entity.Alert;
import com.tempolivre.api.entity.enums.AlertaNivel;
import com.tempolivre.api.entity.enums.AlertaStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertaRepository extends JpaRepository<Alert, String> {

    List<Alert> findByNivel(AlertaNivel nivel);

    List<Alert> findByStatus(AlertaStatus status);

}