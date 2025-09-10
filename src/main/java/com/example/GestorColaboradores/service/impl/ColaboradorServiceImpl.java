package com.example.GestorColaboradores.service.impl;

import com.example.GestorColaboradores.model.Colaborador;
import com.example.GestorColaboradores.repository.ColaboradorRepository;
import com.example.GestorColaboradores.service.ColaboradorService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ColaboradorServiceImpl implements ColaboradorService {

    private final ColaboradorRepository repo;

    public ColaboradorServiceImpl(ColaboradorRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Colaborador> listarTodos() {
        return repo.findAll();
    }

    @Override
    public Colaborador crear(Colaborador c) {
        if (repo.existsByEmail(c.getEmail())) {
            throw new IllegalArgumentException("Email ya registrado");
        }
        return repo.save(c);
    }
}