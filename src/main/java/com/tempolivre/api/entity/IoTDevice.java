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
public class IoTDevice {

    @Id
    @GeneratedValue( strategy = GenerationType.UUID)
    private String id;

    private String name;

    private double latitude;

    private double longitude;

    public IoTDevice(String name, double latitude, double longitude){
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}