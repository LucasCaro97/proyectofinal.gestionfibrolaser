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
@Table(name = "pagos")
public class Pagos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "fk_cliente", nullable = false)
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "fk_pedido", nullable = false)
    private Pedido pedido;

    @Column(name = "fecha_pago", nullable = false)
    private Date fechaEntrega;

    @Column(name = "monto_pago")
    private Double monto;

    @OneToOne
    @JoinColumn(name = "fk_formadepago", nullable = false)
    private FormaPago formaPago;

    @OneToOne
    @JoinColumn(name = "fk_concepto", nullable = false)
    private ConceptoPago concepto;

}
