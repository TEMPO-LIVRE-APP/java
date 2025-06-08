package com.tempolivre.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dispositivo")
@Entity(name = "dispositivo")
@EqualsAndHashCode( of = "id")
public class Sensor {

    @Id
    @GeneratedValue( strategy = GenerationType.UUID)
    private String id;

    private String name;

    @NonNull
    @Column(unique = true)
    private double latitude;

    @NonNull
    @Column(unique = true)
    private double longitude;

    public Sensor(String name, double latitude, double longitude){
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}