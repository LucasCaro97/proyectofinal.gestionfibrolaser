package com.gestion.fibrolaser.servicios;

import com.gestion.fibrolaser.entidades.EstadoPedido;
import com.gestion.fibrolaser.repositorios.EstadoPedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadoPedidoServicio {

    private final EstadoPedidoRepository estadoPedidoRepository;

    @Transactional
    public void create(EstadoPedido estadoDto){
        if (estadoPedidoRepository.existsByNombre(estadoDto.getNombre()))
            throw new IllegalArgumentException("Ya existe un estado con este nombre");

        EstadoPedido estado = new EstadoPedido();
        estado.setNombre(estadoDto.getNombre());
        estadoPedidoRepository.save(estado);
    }

    @Transactional
    public void update(EstadoPedido estadoDto){
        if (estadoPedidoRepository.existsByNombre(estadoDto.getNombre()))
            throw new IllegalArgumentException("Ya existe un estado con este nombre");

        EstadoPedido estado = estadoPedidoRepository.findById(estadoDto.getId()).get();
        estado.setNombre(estadoDto.getNombre());
        estadoPedidoRepository.save(estado);
    }

    @Transactional(readOnly = true)
    public List<EstadoPedido> getAll(){
        return estadoPedidoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public EstadoPedido getById(Integer id){
        return estadoPedidoRepository.findById(id).get();
    }

    @Transactional
    public void deleteById(Integer id){ estadoPedidoRepository.deleteById(id);}







}
