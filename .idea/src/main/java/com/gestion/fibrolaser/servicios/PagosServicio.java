package com.gestion.fibrolaser.servicios;


import com.gestion.fibrolaser.entidades.Pagos;
import com.gestion.fibrolaser.repositorios.PagosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PagosServicio {

     private final PagosRepository pagosRepository;

     @Transactional
     public void create(Pagos pagosDto){
         Pagos pagos = new Pagos();
         pagos.setCliente(pagosDto.getCliente());
         pagos.setPedido(pagos.getPedido());
         pagos.setConcepto(pagosDto.getConcepto());
         pagos.setFormaPago(pagosDto.getFormaPago());
         pagos.setFechaEntrega(pagosDto.getFechaEntrega());
         pagos.setMonto(pagosDto.getMonto());
         pagosRepository.save(pagos);

     }

    @Transactional
     public void update(Pagos pagosDto){
         Pagos pagos = pagosRepository.findById(pagosDto.getId()).get();
         pagos.setCliente(pagosDto.getCliente());
         pagos.setPedido(pagos.getPedido());
         pagos.setConcepto(pagosDto.getConcepto());
         pagos.setFormaPago(pagosDto.getFormaPago());
         pagos.setFechaEntrega(pagosDto.getFechaEntrega());
         pagos.setMonto(pagosDto.getMonto());
         pagosRepository.save(pagos);
     }

     @Transactional
     public List<Pagos> getAll(){ return pagosRepository.findAll(); }

     @Transactional
     public Pagos getById(Integer id) { return pagosRepository.findById(id).get(); }

     @Transactional
     public void deleteById(Integer id) { pagosRepository.deleteById(id);}



}
