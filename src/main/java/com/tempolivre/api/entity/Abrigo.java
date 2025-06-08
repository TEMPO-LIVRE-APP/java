package com.tempolivre.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "abrigo")
@Entity(name = "abrigo")
@EqualsAndHashCode(of = "id")
public class Abrigo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true)
    @NotBlank(message = "O local deve ter um nome")
    private String name;

    @Column(unique = true)
    @NotBlank(message = "O local deve ter um contato")
    private String contato;

    @NotBlank(message = "O local deve ter um endereco")
    private String endereco;

    private double latitude;
    private double longitude;

}
