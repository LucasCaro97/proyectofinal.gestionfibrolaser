package com.gestion.fibrolaser.servicios;

import com.gestion.fibrolaser.entidades.Cliente;
import com.gestion.fibrolaser.entidades.Rol;
import com.gestion.fibrolaser.repositorios.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolServicio {

    private final RolRepository rolRepository;

    @Transactional
    public void create(Rol dto){
        Rol rol = new Rol();
        rol.setName(dto.getName());
        rolRepository.save(rol);
    }

    @Transactional(readOnly = true)
    public List<Rol> getAll(){
        return rolRepository.findAll();
    }


    @Transactional
    public void deleteById(Integer id) { rolRepository.deleteById(id); }

    @Transactional (readOnly = true)
    public Rol getById(Integer id) { return rolRepository.findById(id).get(); }

    @Transactional
    public void update(Rol dto) {
        Rol rol = rolRepository.findById(dto.getId()).get();
        rol.setName(dto.getName());
        rolRepository.save(rol);
    }
}
