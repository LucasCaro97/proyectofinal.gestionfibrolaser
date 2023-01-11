package com.gestion.fibrolaser.servicios;

import com.gestion.fibrolaser.entidades.ConceptoPago;
import com.gestion.fibrolaser.repositorios.ConceptoPagoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConceptoPagoServicio {

    private final ConceptoPagoRepository conceptoPagoRepository;

    @Transactional
    public void create(ConceptoPago conceptoDto){
        ConceptoPago concepto = new ConceptoPago();
        concepto.setNombre(conceptoDto.getNombre());
        conceptoPagoRepository.save(concepto);
    }

    @Transactional
    public void update(ConceptoPago conceptoDto){
        ConceptoPago concepto = conceptoPagoRepository.findById(conceptoDto.getId()).get();
        concepto.setNombre(conceptoDto.getNombre());
        conceptoPagoRepository.save(concepto);
    }

    @Transactional
    public List<ConceptoPago> getAll(){
        return conceptoPagoRepository.findAll();
    }

    @Transactional
    public ConceptoPago getById(Integer id){
        return conceptoPagoRepository.findById(id).get();
    }

    @Transactional
    public void deleteById(Integer id){
        conceptoPagoRepository.deleteById(id);
    }


}
