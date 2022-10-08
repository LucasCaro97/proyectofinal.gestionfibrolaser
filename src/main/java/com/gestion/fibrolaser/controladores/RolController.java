package com.gestion.fibrolaser.controladores;

import com.gestion.fibrolaser.entidades.Rol;
import com.gestion.fibrolaser.servicios.RolServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RolController {

    private final RolServicio rolServicio;


    @GetMapping
    public ModelAndView getAll(){
        ModelAndView mav = new ModelAndView("tabla-roles");
        mav.addObject("roles", rolServicio.getAll());
        return mav;
    }

    @GetMapping("/form")
    public ModelAndView getForm() {
        ModelAndView mav = new ModelAndView("rol-form");
        mav.addObject("rol", new Rol());
        return mav;
    }


    @PostMapping("/create")
    public RedirectView create(Rol dto){
        RedirectView redirect = new RedirectView("/roles");
        rolServicio.create(dto);
        return redirect;
    }

    @PostMapping("/delete/{id}")
    public RedirectView deleteById(@PathVariable Integer id){
        RedirectView redirect = new RedirectView("/roles");
        rolServicio.deleteById(id);
        return redirect;
    }
}
