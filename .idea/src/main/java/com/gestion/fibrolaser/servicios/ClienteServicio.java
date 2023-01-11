package com.gestion.fibrolaser.servicios;

import com.gestion.fibrolaser.entidades.Cliente;
import com.gestion.fibrolaser.repositorios.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServicio {

    private final ClienteRepository clienteRepositorio;

    @Transactional
    public void createClient(Cliente clienteDto){
        if (clienteRepositorio.existsByNombre(clienteDto.getNombre())) {
            throw new IllegalArgumentException("No pueden existir dos clientes con el mismo nombre");}

            Cliente cliente = new Cliente();
            cliente.setNombre(clienteDto.getNombre());
            cliente.setEmail(clienteDto.getEmail());
            cliente.setTelefono(clienteDto.getTelefono());
            cliente.setAlta(true);

            clienteRepositorio.save(clienteDto);

    }

    @Transactional(readOnly = true)
    public List<Cliente> getAll(){
        return clienteRepositorio.findAll();
    }

    @Transactional (readOnly = true)
    public Cliente getById(Integer id){
        return clienteRepositorio.findById(id).get();
    }

    @Transactional
    public void update(Cliente clienteDto){
            Cliente cliente = clienteRepositorio.findById(clienteDto.getId()).get();

            cliente.setNombre(clienteDto.getNombre());
            cliente.setEmail(clienteDto.getEmail());
            cliente.setTelefono(clienteDto.getTelefono());

            clienteRepositorio.save(cliente);
    }

    @Transactional
    public void deleteById(Integer id){
    clienteRepositorio.deleteById(id);
    }


}
