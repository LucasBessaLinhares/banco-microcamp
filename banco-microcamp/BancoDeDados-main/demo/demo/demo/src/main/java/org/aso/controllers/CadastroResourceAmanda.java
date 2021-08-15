package org.aso.controllers;

import org.aso.domain.Cadastro;
import org.aso.services.CadastroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/amanda")
public class CadastroResource {


    @Autowired
    private CadastroService repository;

    @GetMapping
    public List<Cadastro> listAll() {
        return repository.findAll();
    }

    @GetMapping(value = "/{Id}")
    public @ResponseBody
    HttpEntity<Object> listById(@PathVariable(name = "Id") Long Id) {

        return ResponseEntity.ok(repository.findById(Id));
    }

    @PostMapping
    public @ResponseBody
    Cadastro create(@RequestBody Cadastro cadastro) {
        return repository.save(cadastro);
    }

    @PutMapping(value = "/{Id}")
    public @ResponseBody
    Cadastro update(
            @PathVariable(name = "Id") Long Id,
            @RequestBody Cadastro cadastro) {
        return repository.update(cadastro);
    }

    @DeleteMapping(value = "/{Id}")
    public void delete(@PathVariable(name = "Id") Long Id) {

        repository.deleteById(Id);
    }
}