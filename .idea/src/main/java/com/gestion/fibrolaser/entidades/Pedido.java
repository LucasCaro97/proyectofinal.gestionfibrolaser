package com.gestion.fibrolaser.entidades;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Integer id;
    @Column(name = "fechaingreso_pedido")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaIngreso;
    @Column(name = "descripcion_pedido")
    private String descripcion;
    @OneToOne
    @JoinColumn(name = "fk_cliente")
    private Cliente cliente;
    @OneToOne
    @JoinColumn(name = "fk_estado")
    private EstadoPedido estadoPedido;
    @Column(name = "senia_pedido")
    private Integer senia;
    @Column(name = "fechaentrega_pedido")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaEntrega;
    @OneToOne
    @JoinColumn(name = "fk_usuario")
    private Usuario usuario;

    
}
