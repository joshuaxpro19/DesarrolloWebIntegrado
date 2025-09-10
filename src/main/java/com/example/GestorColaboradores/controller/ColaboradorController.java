package com.example.GestorColaboradores.controller;

import com.example.GestorColaboradores.model.Colaborador;
import com.example.GestorColaboradores.service.ColaboradorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/colaboradores")
public class ColaboradorController {

    private final ColaboradorService service;

    public ColaboradorController(ColaboradorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Colaborador>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @PostMapping
    public ResponseEntity<Colaborador> crear(@RequestBody Colaborador c) {
        Colaborador creado = service.crear(c);
        return ResponseEntity.status(201).body(creado);
    }
}