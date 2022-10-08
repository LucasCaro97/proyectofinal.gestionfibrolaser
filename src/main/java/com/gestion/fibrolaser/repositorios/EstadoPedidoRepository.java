package com.gestion.fibrolaser.repositorios;


import com.gestion.fibrolaser.entidades.EstadoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoPedidoRepository extends JpaRepository<EstadoPedido, Integer> {
    boolean existsByNombre(String nombre);
}