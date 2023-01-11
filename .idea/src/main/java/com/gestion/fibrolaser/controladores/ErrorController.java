package com.gestion.fibrolaser.controladores;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static javax.servlet.RequestDispatcher.ERROR_STATUS_CODE;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    public ModelAndView handleError (HttpServletRequest request){
        ModelAndView mav = new ModelAndView("error");
        Integer status =(int)request.getAttribute(ERROR_STATUS_CODE);
        String message;

        switch (status){
            case 403:
                message = "No tienes los permisos suficientes para ingresar a esta seccion";
                break;
            case 404:
                message = "El recurso requerido no fue encontrado";
                break;
            case 500:
                message = "Error interno del servidor";
                break;
            default:
                message ="Error inesperado";

        }

        mav.addObject("message", message);
        mav.addObject("status", status);
        return mav;

    }

}
