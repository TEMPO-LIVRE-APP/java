package com.tempolivre.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tempolivre.api.entity.enums.AlertaNivel;
import com.tempolivre.api.entity.enums.AlertaStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "alerta")
@Entity(name = "alerta")
@EqualsAndHashCode(of = "id")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private AlertaNivel nivel;

    @NotBlank(message = "Tipo do alerta deve ser definido")
    private String tipo;

    private String message;

    private AlertaStatus status;

    @JsonFormat(pattern = "dd/MM/yy HH:mm:ss")
    @CreationTimestamp
    private LocalDateTime emissao;
}
