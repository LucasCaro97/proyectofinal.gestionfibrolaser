package com.gestion.fibrolaser.servicios;

import com.gestion.fibrolaser.entidades.Usuario;
import com.gestion.fibrolaser.repositorios.RolRepository;
import com.gestion.fibrolaser.repositorios.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServicio implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder encoder;
    private final RolRepository rolRepository;

    @Transactional
    public void create(Usuario usuarioDto){
        if(usuarioRepository.existsByUsername(usuarioDto.getUsername())){
            throw new IllegalArgumentException("Ya existe un usuario con ese nombre, elija otro");
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(usuarioDto.getUsername());
        usuario.setPassword(encoder.encode(usuarioDto.getPassword()));
        usuario.setNombreCompleto(usuarioDto.getNombreCompleto());
        usuario.setTelefono(usuarioDto.getTelefono());
        usuario.setCiudad(usuarioDto.getCiudad());


        if (usuarioDto.getRol() == null)
            usuario.setRol(rolRepository.findByName("CLIENTE").orElseThrow(() -> new IllegalArgumentException("Error")));

            usuarioRepository.save(usuario);

    }

    @Transactional
    public void updateRole(Usuario usuarioDto){
        Usuario usuario = new Usuario();
        usuario = usuarioRepository.findById(usuarioDto.getId()).get();
        usuario.setRol(usuarioDto.getRol());
        usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public List<Usuario> getAll(){
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Usuario getById(Integer id){ return usuarioRepository.findById(id).get();   }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username).
                orElseThrow(() -> new UsernameNotFoundException("No hay ningun usuario registrado con ese nombre"));

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().getName());

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

        HttpSession session = attributes.getRequest().getSession(true);

        session.setAttribute("id", usuario.getId());
        session.setAttribute("username", usuario.getUsername());
        session.setAttribute("rol", usuario.getRol().getName());

        return new org.springframework.security.core.userdetails.User(usuario.getUsername(), usuario.getPassword(), Collections.singletonList(authority));
    }
}
