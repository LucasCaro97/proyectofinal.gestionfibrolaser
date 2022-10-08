package com.gestion.fibrolaser.repositorios;

import com.gestion.fibrolaser.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    boolean existsByUsername(String username);

    Optional<Usuario> findByUsername(String username);


}