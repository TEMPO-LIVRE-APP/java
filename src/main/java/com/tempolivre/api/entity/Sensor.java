package com.tempolivre.api.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dispositivo")
@Entity(name = "dispositivo")
public class Sensor {

    @Id
    @GeneratedValue( strategy = GenerationType.UUID)
    private String id;

    private String name;

    private double latitude;

    private double longitude;

    public Sensor(String name, double latitude, double longitude){
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}