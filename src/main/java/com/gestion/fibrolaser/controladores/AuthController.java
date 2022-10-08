package com.gestion.fibrolaser.controladores;


import com.gestion.fibrolaser.entidades.Usuario;
import com.gestion.fibrolaser.servicios.UsuarioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioServicio usuarioServicio;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, Principal principal) {
        ModelAndView mav = new ModelAndView("login-form");

        if (error != null) mav.addObject("error", "Invalid email or password");
        if (logout != null) mav.addObject("logout", "You have successfully exited the platform");
        if (principal != null) mav.setViewName("redirect:/");

        return mav;
    }

    @GetMapping("/sign-up")
    public ModelAndView signup(HttpServletRequest request, Principal principal) {
        ModelAndView mav = new ModelAndView("sign-up-form");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (principal != null) mav.setViewName("redirect:/");

        if (inputFlashMap != null) {
            mav.addObject("exception", inputFlashMap.get("exception"));
            mav.addObject("user", inputFlashMap.get("user"));
        } else {
            mav.addObject("user", new Usuario());
        }

        return mav;
    }

    @PostMapping("/register")
    public RedirectView signup(Usuario userDto, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/");

        try {
            usuarioServicio.create(userDto);
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("user", userDto);
            attributes.addFlashAttribute("exception", e.getMessage());
            redirect.setUrl("/auth/sign-up");
        }

        return redirect;
    }

}
