package com.gestion.fibrolaser.servicios;

import com.gestion.fibrolaser.entidades.Pedido;
import com.gestion.fibrolaser.repositorios.EstadoPedidoRepository;
import com.gestion.fibrolaser.repositorios.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoServicio {

    private final PedidoRepository pedidoRepository;
    private final EstadoPedidoRepository estadoPedidoRepository;

    @Transactional
    public void create(Pedido pedidoDto){
        Pedido pedido = new Pedido();
        pedido.setCliente(pedidoDto.getCliente());
        pedido.setDescripcion(pedidoDto.getDescripcion());
        pedido.setFechaIngreso(LocalDate.now());
        pedido.setFechaEntrega(pedidoDto.getFechaEntrega());
        pedido.setEstadoPedido(pedidoDto.getEstadoPedido());
        pedido.setSenia(pedidoDto.getSenia());
        pedidoRepository.save(pedido);

    }

    @Transactional
    public void update(Pedido pedidoDto){
        Pedido pedido = pedidoRepository.findById(pedidoDto.getId()).get();
        pedido.setCliente(pedidoDto.getCliente());
        pedido.setDescripcion(pedidoDto.getDescripcion());
        pedido.setFechaIngreso(pedidoDto.getFechaIngreso());
        pedido.setFechaEntrega(pedidoDto.getFechaEntrega());
        pedido.setEstadoPedido(pedidoDto.getEstadoPedido());
        pedido.setSenia(pedido.getSenia());
        pedidoRepository.save(pedido);
    }

    @Transactional
    public List<Pedido> getAll(){
        Sort sort = Sort.by("fechaEntrega").ascending();
        return pedidoRepository.findAll(sort);

    }

    @Transactional
    public Pedido getById(Integer id){ return pedidoRepository.findById(id).get(); }

    @Transactional
    public void deleteById(Integer id) { pedidoRepository.deleteById(id);}

}

