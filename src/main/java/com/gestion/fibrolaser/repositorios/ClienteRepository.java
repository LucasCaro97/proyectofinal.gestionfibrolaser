package com.gestion.fibrolaser.repositorios;

import com.gestion.fibrolaser.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    //METODO PARA BUSCAR POR NOMBRE SIN PARAM
    @Query("SELECT c FROM Cliente c WHERE c.nombre LIKE ?1")
    List<Cliente> searchByName (String nombre);

    boolean existsByNombre(String name);

    List<Cliente> findByNombre(String nombre);


}