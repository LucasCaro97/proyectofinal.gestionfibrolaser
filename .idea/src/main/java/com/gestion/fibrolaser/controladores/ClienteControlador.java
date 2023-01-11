package com.gestion.fibrolaser.controladores;


import com.gestion.fibrolaser.entidades.Cliente;
import com.gestion.fibrolaser.servicios.ClienteServicio;
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
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteControlador {

    private final ClienteServicio clienteServicio;


    @PreAuthorize("hasAnyRole('ADMIN', 'ATENCION')")
    @GetMapping
    public ModelAndView getClients (HttpServletRequest request){
    ModelAndView mav = new ModelAndView("tabla-clientes");
    Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

    if (inputFlashMap != null) mav.addObject("exito", inputFlashMap.get("exito"));
    mav.addObject("clientes", clienteServicio.getAll());
    return mav;

    }


    @PreAuthorize("hasAnyRole('ADMIN', 'ATENCION')")
    @GetMapping("/form")
    public ModelAndView getForm(HttpServletRequest request){
    ModelAndView mav = new ModelAndView("form-clientes");
    Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if(inputFlashMap != null){
            mav.addObject("cliente", inputFlashMap.get("cliente"));
            mav.addObject("exception", inputFlashMap.get("exception"));
        }else {
            mav.addObject("cliente", new Cliente());
        }

        mav.addObject("action", "create");
        return mav;
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'ATENCION')")
    @GetMapping("/form/{id}")
    public ModelAndView getFormById(@PathVariable Integer id){
        ModelAndView mav = new ModelAndView("form-clientes");
        mav.addObject("cliente", clienteServicio.getById(id));
        mav.addObject("action", "update");
        return mav;
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'ATENCION')")
    @PostMapping("/create")
    public RedirectView create(Cliente cliente, RedirectAttributes attributes){
        RedirectView redirect = new RedirectView("/clientes");
        try{
            clienteServicio.createClient(cliente);
            attributes.addFlashAttribute("exito", "La operacion se ha realizado con exito");
        } catch(IllegalArgumentException e){
            attributes.addFlashAttribute("cliente", cliente);
            attributes.addFlashAttribute("exception", e.getMessage());
            redirect.setUrl("/clientes/form");
        }
        return redirect;
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'ATENCION')")
    @PostMapping("/update")
    public RedirectView update(Cliente cliente, RedirectAttributes attributes){
        RedirectView redirect = new RedirectView("/clientes");
        try{
            clienteServicio.update(cliente);
            attributes.addFlashAttribute("exito", "La operacion se ha realizado con exito");
        } catch(IllegalArgumentException e){
            attributes.addFlashAttribute("cliente", cliente);
            attributes.addFlashAttribute("exception", e.getMessage());
        }
        return redirect;
    }



    @PreAuthorize("hasAnyRole('ADMIN', 'ATENCION')")
    @PostMapping("/delete/{id}")
    public RedirectView delete(@PathVariable Integer id, RedirectAttributes attributes){
        RedirectView redirect = new RedirectView("/clientes");
        clienteServicio.deleteById(id);
        attributes.addFlashAttribute("exito", "Se ha eliminado el registro correctamente");
        return redirect;
    }





}
