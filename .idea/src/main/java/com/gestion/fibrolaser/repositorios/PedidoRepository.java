package com.gestion.fibrolaser.repositorios;

import com.gestion.fibrolaser.entidades.Pedido;
import com.gestion.fibrolaser.entidades.Usuario;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findAll(Sort sort);



    @Query( value= "SELECT * FROM pedidos WHERE fk_usuario = ?1", nativeQuery = true)
    List<Pedido> searchByUsuarioNativeQUery(Integer id);

    List<Pedido> findByUsuario(Usuario usuario);

}