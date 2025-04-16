package com.satisfaction.controller;

import com.satisfaction.entity.Enquete;
import com.satisfaction.repository.EnqueteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enquetes")
public class EnqueteController {

    private final EnqueteRepository repository;

    public EnqueteController(EnqueteRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Enquete> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Enquete create(@RequestBody Enquete e) {
        return repository.save(e);
    }
}
