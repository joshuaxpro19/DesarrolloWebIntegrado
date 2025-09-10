package com.example.GestorColaboradores.service;

import com.example.GestorColaboradores.model.Colaborador;
import java.util.List;

public interface ColaboradorService {
    List<Colaborador> listarTodos();
    Colaborador crear(Colaborador c);
}