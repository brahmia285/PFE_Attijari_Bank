package com.satisfaction.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ENQUETE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@SequenceGenerator(name = "enquete_seq", sequenceName = "ENQUETE_SEQ", allocationSize = 1)
public class Enquete {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enquete_seq")
    private Long id;

    @Column(name = "TITRE", nullable = false, length = 100)
    private String titre;

    @Column(name = "DESCRIPTION", length = 500)
    private String description;
}