package com.example.Proyecto.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Proyecto.modelo.Trago;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tragos")
public class tragosController {

    private final List<Trago> tragos= new ArrayList<>();
    private Long idContar=1L;

    /*Listar los tragos*/
    @GetMapping
    public List<Trago> obtenerTodosLosTragos(){
        return tragos;
    }

    /*Filtrar tragos por id*/
    @GetMapping("/{id}")
    public ResponseEntity<Trago> obtenerTragoPorId(@PathVariable Long id){
        Optional<Trago> trago=tragos.stream().filter(t -> t.getId().equals(id)).findFirst();
        return trago.map(t -> ResponseEntity.ok(t)).orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> CrearTrago(@RequestBody Trago trago){
        boolean existe = tragos.stream().anyMatch(t->t.getNombre().equalsIgnoreCase(trago.getNombre()));

        if (existe){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe un trago con ese nombre");
        }

        trago.setId(idContar++);
        tragos.add(trago);
        return ResponseEntity.status(HttpStatus.CREATED).body(trago);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trago> actualizarTragos(@PathVariable Long id, @RequestBody Trago detallesTrago){
        Optional<Trago> tragoExistente = tragos.stream().filter(t->t.getId().equals(id)).findFirst();

        return tragoExistente.map(t-> {
            t.setNombre(detallesTrago.getNombre());
            t.setDescripcion(detallesTrago.getDescripcion());
            t.setPrecio(detallesTrago.getPrecio());
            t.setActivo(detallesTrago.isActivo());
            return ResponseEntity.ok(t);
        }).orElseGet(()->ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTragos (@PathVariable Long id){
        boolean eliminado = tragos.removeIf(t-> t.getId().equals(id));
        if(eliminado){
            return ResponseEntity.ok("El trago con id : " + id  +" ha salido eliminado");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el trago con id :"+ id);
        }
    }


}
