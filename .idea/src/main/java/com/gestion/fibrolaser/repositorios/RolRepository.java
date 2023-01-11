package com.gestion.fibrolaser.repositorios;

import com.gestion.fibrolaser.entidades.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByName(String user);
}