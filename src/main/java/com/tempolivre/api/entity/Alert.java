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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AlertaNivel getNivel() {
		return nivel;
	}

	public void setNivel(AlertaNivel nivel) {
		this.nivel = nivel;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public AlertaStatus getStatus() {
		return status;
	}

	public void setStatus(AlertaStatus status) {
		this.status = status;
	}

	public LocalDateTime getEmissao() {
		return emissao;
	}

	public void setEmissao(LocalDateTime emissao) {
		this.emissao = emissao;
	}
}
