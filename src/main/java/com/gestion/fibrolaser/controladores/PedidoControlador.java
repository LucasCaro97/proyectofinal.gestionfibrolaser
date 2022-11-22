package com.gestion.fibrolaser.controladores;

import com.gestion.fibrolaser.entidades.Pedido;
import com.gestion.fibrolaser.servicios.ClienteServicio;
import com.gestion.fibrolaser.servicios.EstadoPedidoServicio;
import com.gestion.fibrolaser.servicios.PedidoServicio;
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
@RequestMapping("pedidos")
@RequiredArgsConstructor
public class PedidoControlador {

    private final PedidoServicio pedidoServicio;
    private final ClienteServicio clienteServicio;
    private final EstadoPedidoServicio estadoPedidoServicio;


    @GetMapping
    public ModelAndView getAll (HttpServletRequest request){
        ModelAndView mav = new ModelAndView("tabla-pedidos");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) mav.addObject("exito", inputFlashMap.get("exito"));
        mav.addObject("pedidos", pedidoServicio.getAll());
        return mav;

    }

    @GetMapping("/form")
    public ModelAndView getForm(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("form-pedidos");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if(inputFlashMap != null){
            mav.addObject("pedido", inputFlashMap.get("pedido"));
            mav.addObject("exception", inputFlashMap.get("exception"));
        }else{
            mav.addObject("pedido", new Pedido());
            mav.addObject("clientes", clienteServicio.getAll());
            mav.addObject("estados", estadoPedidoServicio.getAll());
        }
        mav.addObject("action", "create");
        return mav;

    }

    @GetMapping("/form/{id}")
    public ModelAndView getForm(@PathVariable Integer id){
        ModelAndView mav = new ModelAndView("form-pedidos");
        mav.addObject("pedido", pedidoServicio.getById(id));
        mav.addObject("clientes", clienteServicio.getAll());
        mav.addObject("estados", estadoPedidoServicio.getAll());
        mav.addObject("action", "update");
        return mav;
    }

    @PostMapping("/create")
    public RedirectView create(Pedido pedido, RedirectAttributes attributes){
        RedirectView redirect = new RedirectView("/pedidos");
        try{
            pedidoServicio.create(pedido);
            attributes.addFlashAttribute("exito", "La operacion se ha realizado con exito");
        } catch (IllegalArgumentException e){
            attributes.addFlashAttribute("pedido", pedido);
            attributes.addFlashAttribute("exception", e.getMessage());
            redirect.setUrl("/pedidos/form");
        }
        return redirect;
    }

    @PostMapping("/update")
    public RedirectView update(Pedido pedido, RedirectAttributes attributes){
        RedirectView redirect = new RedirectView("/pedidos");
        try{
            pedidoServicio.update(pedido);
            attributes.addFlashAttribute("exito", "La operacion se ha relizado con exito");
        }catch(IllegalArgumentException e){
            attributes.addFlashAttribute("pedido", pedido);
            attributes.addFlashAttribute("exception", e.getMessage());
            redirect.setUrl("/pedidos/form");
        }
        return redirect;

    }

    @PostMapping("/delete/{id}")
    public ModelAndView deleteById(@PathVariable Integer id){
        ModelAndView mav = new ModelAndView("redirect:/pedidos");
        pedidoServicio.deleteById(id);
        return mav;
    }


}
