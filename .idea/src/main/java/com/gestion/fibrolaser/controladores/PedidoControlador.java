package com.gestion.fibrolaser.controladores;

import com.gestion.fibrolaser.entidades.Pedido;
import com.gestion.fibrolaser.entidades.Usuario;
import com.gestion.fibrolaser.servicios.*;
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
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("pedidos")
@RequiredArgsConstructor
public class PedidoControlador {

    private final PedidoServicio pedidoServicio;
    private final ClienteServicio clienteServicio;
    private final EstadoPedidoServicio estadoPedidoServicio;
    private final UsuarioServicio usuarioServicio;

    @PreAuthorize("hasAnyRole('ADMIN', 'ATENCION', 'OPERADOR')")
    @GetMapping
    public ModelAndView getAll (HttpServletRequest request){
        ModelAndView mav = new ModelAndView("tabla-pedidos");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) mav.addObject("exito", inputFlashMap.get("exito"));
        mav.addObject("pedidos", pedidoServicio.getAll());
        return mav;

    }

    @PreAuthorize("hasAnyRole('CLIENTE', 'ATENCION')")
    @GetMapping("/clientes")
    public ModelAndView getByUser (HttpServletRequest request, HttpSession session){
        ModelAndView mav = new ModelAndView("tabla-mispedidos");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) mav.addObject("exito", inputFlashMap.get("exito"));
        mav.addObject("misPedidos", pedidoServicio.getByUser(4));
        return mav;

    }

    @PreAuthorize("hasAnyRole('ADMIN', 'ATENCION', 'OPERADOR', 'CLIENTE')")
    @GetMapping("/form")
    public ModelAndView getForm(HttpServletRequest request, HttpSession session){
        ModelAndView mav = new ModelAndView("form-pedidos");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if(inputFlashMap != null){
            mav.addObject("pedido", inputFlashMap.get("pedido"));
            mav.addObject("exception", inputFlashMap.get("exception"));
            //mav.addObject("rol", inputFlashMap.get("rol")); AGREGAR DESDE EL POST POR SI HAY ERROR EN LA CARGA DEL REGISTRO
        }else{

            mav.addObject("pedido", new Pedido());
            mav.addObject("clientes", clienteServicio.getAll());
            mav.addObject("estados", estadoPedidoServicio.getAll());

        }
        mav.addObject("action", "create");
        return mav;

    }


    @PreAuthorize("hasAnyRole('ADMIN', 'ATENCION', 'OPERADOR', 'CLIENTE')")
    @GetMapping("/form/{id}")
    public ModelAndView getForm(@PathVariable Integer id){
        ModelAndView mav = new ModelAndView("form-pedidos");
        mav.addObject("pedido", pedidoServicio.getById(id));
        mav.addObject("clientes", clienteServicio.getAll());
        mav.addObject("estados", estadoPedidoServicio.getAll());
        mav.addObject("action", "update");
        return mav;
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'ATENCION', 'OPERADOR', 'CLIENTE')")
    @PostMapping("/create")
    public RedirectView create(Pedido pedido, RedirectAttributes attributes, HttpSession session){
        RedirectView redirect = new RedirectView("/pedidos");

        if(session.getAttribute("rol").equals("CLIENTE")){
            redirect = new RedirectView("/pedidos/clientes");
        }

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


    @PreAuthorize("hasAnyRole('ADMIN', 'ATENCION', 'OPERADOR', 'CLIENTE')")
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


    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/delete/{id}")
    public ModelAndView deleteById(@PathVariable Integer id){
        ModelAndView mav = new ModelAndView("redirect:/pedidos");
        pedidoServicio.deleteById(id);
        return mav;
    }


}
