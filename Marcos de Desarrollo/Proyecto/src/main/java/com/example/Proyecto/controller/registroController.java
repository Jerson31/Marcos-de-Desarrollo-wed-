package com.example.Proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class registroController {
    @GetMapping("/re")
    public String mostrarRegistro(){
        return "registro";
    }
    @PostMapping("/registros")
    public String registros(@RequestParam String nombre,@RequestParam String apellido,@RequestParam int celular,@RequestParam String correo,@RequestParam String contraseña,Model model){
        model.addAttribute("nombre",nombre);
        model.addAttribute("apellido",apellido);
        model.addAttribute("celular",celular);
        model.addAttribute("correo",correo);
        model.addAttribute("contraseña",contraseña);
        return "registroex";
    }

}
