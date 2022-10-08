package com.gestion.fibrolaser.repositorios;


import com.gestion.fibrolaser.entidades.Pagos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagosRepository extends JpaRepository<Pagos, Integer> {
}