package com.gestion.fibrolaser.controladores;

import com.gestion.fibrolaser.entidades.Usuario;
import com.gestion.fibrolaser.servicios.RolServicio;
import com.gestion.fibrolaser.servicios.UsuarioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioControlador {

    private final UsuarioServicio usuarioServicio;
    private final RolServicio rolServicio;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ModelAndView getAll(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("tabla-usuarios");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) mav.addObject("exito", inputFlashMap.get("exito"));

        mav.addObject("usuarios", usuarioServicio.getAll());
        return mav;
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/form/{id}")
    ModelAndView getForm(@PathVariable Integer id){

        /* Aplicar cuando cada usuario tenga la opcion de modificar sus datos del perfil
        *  y agregar como parametro del metodo -> HttpSession session

        if (!session.getId().equals(id)) return new ModelAndView("redirect:/");
        */
        ModelAndView mav = new ModelAndView("usuario-form");
        mav.addObject("usuario", usuarioServicio.getById(id));
        mav.addObject("roles", rolServicio.getAll());
        return mav;
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update")
    RedirectView update(Usuario dto, RedirectAttributes attributes){
        RedirectView redirect = new RedirectView("/usuarios");
        usuarioServicio.updateRole(dto);
        System.out.println(dto.getUsername() + " - " + dto.getRol().getName() + dto.getId());
        attributes.addFlashAttribute("exito", "Se ha modificado el rol del usuario correctamente");
        return redirect;
    }

    /*
    @PostMapping("/update")
    RedirectView update(Usuario dto, RedirectAttributes attributes){
        RedirectView redirect = new RedirectView("/usuarios");
        usuarioServicio.updateRole(dto);
        System.out.println(dto.getUsername() + " - " + dto.getRol().getName());
        attributes.addFlashAttribute("exito", "Se ha modificado el rol del usuario correctamente");
        return redirect;
    }
    */

}
