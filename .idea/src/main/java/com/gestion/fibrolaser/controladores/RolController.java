package com.gestion.fibrolaser.controladores;

import com.gestion.fibrolaser.entidades.Rol;
import com.gestion.fibrolaser.servicios.RolServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RolController {

    private final RolServicio rolServicio;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ModelAndView getAll(){
        ModelAndView mav = new ModelAndView("tabla-roles");
        mav.addObject("roles", rolServicio.getAll());
        return mav;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/form")
    public ModelAndView getForm() {
        ModelAndView mav = new ModelAndView("rol-form");
        mav.addObject("rol", new Rol());
        mav.addObject("action", "create");
        return mav;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/form/{id}")
    public ModelAndView getFormUpdate(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("rol-form");
        mav.addObject("rol", rolServicio.getById(id));
        mav.addObject("action", "update");
        return mav;
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public RedirectView create(Rol dto, RedirectAttributes attributes){
        RedirectView redirect = new RedirectView("/roles");
        rolServicio.create(dto);
        return redirect;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update")
    public RedirectView update(Rol dto, RedirectAttributes attributes){
        RedirectView redirect = new RedirectView("/roles");
        rolServicio.update(dto);
        return redirect;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete/{id}")
    public RedirectView deleteById(@PathVariable Integer id){
        RedirectView redirect = new RedirectView("/roles");
        rolServicio.deleteById(id);
        return redirect;
    }
}
