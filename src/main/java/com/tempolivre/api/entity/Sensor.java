package com.tempolivre.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dispositivo")
@Entity(name = "dispositivo")
@EqualsAndHashCode(of = "id")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "O sensor deve ter um nome")
    private String name;

    @NotNull(message = "A latitude é obrigatória")
    @Column(nullable = false)
    private Double latitude;

    @NotNull(message = "A longitude é obrigatória")
    @Column(nullable = false)
    private Double longitude;

    public Sensor(String name, double latitude, double longitude){
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
}