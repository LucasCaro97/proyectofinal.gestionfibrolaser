package com.gestion.fibrolaser.servicios;

import com.gestion.fibrolaser.entidades.FormaPago;
import com.gestion.fibrolaser.repositorios.FormaPagoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FormaPagoServicio {

    private final FormaPagoRepository formaPagoRepository;

    public void create(FormaPago formaPagoDto){
        FormaPago formaPago = new FormaPago();
        formaPago.setNombre(formaPagoDto.getNombre());
        formaPagoRepository.save(formaPago);
    }

    public void update(FormaPago formaPagoDto){
        FormaPago formaPago = formaPagoRepository.findById(formaPagoDto.getId()).get();
        formaPago.setNombre(formaPagoDto.getNombre());
        formaPagoRepository.save(formaPago);
    }

    public List<FormaPago> getAll(){ return formaPagoRepository.findAll(); }

    public FormaPago getById(Integer id){ return formaPagoRepository.findById(id).get(); }

    public void deleteById(Integer id){ formaPagoRepository.deleteById(id); }


}
