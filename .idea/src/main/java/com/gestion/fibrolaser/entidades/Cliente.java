package com.gestion.fibrolaser.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente", nullable = false)
    private Integer id;
    @Column(name = "nombre_cliente", nullable = false)
    private String nombre;
    @Column(name = "contacto_cliente", nullable = false)
    private Long telefono;
    @Column(name = "email_cliente")
    private String email;
    @Column(name ="alta_cliente")
    private Boolean alta;


}
