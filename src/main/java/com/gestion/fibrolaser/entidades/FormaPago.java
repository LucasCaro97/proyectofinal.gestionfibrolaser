package com.gestion.fibrolaser.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "forma_de_pago")
public class FormaPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "formadepago_id")
    private Integer id;
    @Column(name = "formadepago_nombre")
    private String nombre;

}
