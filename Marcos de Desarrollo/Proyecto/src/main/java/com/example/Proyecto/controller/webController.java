package com.example.Proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class webController {

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String inicioSesion(){
            return "login";
    }

    @GetMapping("/registrar-usuario")
    public String registrarUsuario(){
        return "registro";
    }




}
