package com.gestion.fibrolaser.controladores;

import com.gestion.fibrolaser.entidades.EstadoPedido;
import com.gestion.fibrolaser.servicios.EstadoPedidoServicio;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RequestMapping("/estadospedidos")
public class EstadoPedidoControlador {

    private final EstadoPedidoServicio estadoPedidoServicio;

    @GetMapping
    public ModelAndView getAll(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("tabla-estadopedido");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) mav.addObject("success", inputFlashMap.get("success"));
        mav.addObject("estados", estadoPedidoServicio.getAll());

        return mav;

    }

    @GetMapping("/form")
    public ModelAndView getForm(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("form-estadopedido");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) {
            mav.addObject("estado", inputFlashMap.get("estado"));
            mav.addObject("exception", inputFlashMap.get("exception"));
        } else {
            mav.addObject("estado", new EstadoPedido());
        }
        mav.addObject("action", "create");
        return mav;
    }

    @GetMapping("/form/{id}")
    public ModelAndView getFormById(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("form-estadopedido");
        mav.addObject("estado", estadoPedidoServicio.getById(id));
        mav.addObject("action", "update");
        return mav;
    }

    @PostMapping("/create")
    public RedirectView create(EstadoPedido estadoPedidoDto, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/estadospedidos");
        try {
            estadoPedidoServicio.create(estadoPedidoDto);
            attributes.addFlashAttribute("success", "La operacion se ha realizado con exito");
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("estado", estadoPedidoDto);
            attributes.addFlashAttribute("exception", e.getMessage());
            redirect.setUrl("/estadospedidos/form");
        }
        return redirect;

    }

    @PostMapping("/update")
    public RedirectView update(EstadoPedido estadoPedidoDto, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/estadospedidos");
        try {
            estadoPedidoServicio.update(estadoPedidoDto);
            attributes.addFlashAttribute("success", "La operacion se ha realizado con exito");
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("estado", estadoPedidoDto);
            attributes.addFlashAttribute("exception", e.getMessage());
            redirect.setUrl("/estadospedidos/form");
        }
        return redirect;
    }

    @PostMapping("/delete/{id}")
    public RedirectView delete(@PathVariable Integer id) {
        RedirectView redirect = new RedirectView("redirect:/estadospedidos");
        estadoPedidoServicio.deleteById(id);
        return redirect;
    }


}
