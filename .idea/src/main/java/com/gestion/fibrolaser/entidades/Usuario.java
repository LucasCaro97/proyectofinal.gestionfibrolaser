package com.gestion.fibrolaser.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer id;
    @Column(name = "nombre_usuario", unique = true, nullable = false)
    private String username;
    @Column(name = "contraseña_usuario", nullable = false)
    private String password;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_rol", nullable = false)
    private Rol rol;
    @Column(name = "nombrecompleto_usuario", nullable = false)
    private String nombreCompleto;
    @Column(name = "telefono_usuario", nullable = false)
    private Long telefono;
    @Column(name = "cuidad_usuario")
    private String ciudad;



}
