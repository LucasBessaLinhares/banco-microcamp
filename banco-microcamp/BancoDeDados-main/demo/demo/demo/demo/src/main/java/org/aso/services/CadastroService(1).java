package org.aso.services;

import org.aso.domain.Cadastro;
import org.aso.repositories.CadastroRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CadastroService {

    @Autowired
    private CadastroRepositoryJPA repository;

    @Transactional(readOnly = true)
    public List<Cadastro> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Cadastro> findById(Long Id) {
        return repository.findById(Id);
    }

    @Transactional
    public Cadastro save(Cadastro cadastro) {
        return repository.save(cadastro);
    }

    @Transactional
    public void deleteById(Long Id) {
        repository.deleteById(Id);
    }

    public Cadastro update(Cadastro cadastro) {
        Optional<Cadastro> cadastroOptional = findById(cadastro.getId());
        if (cadastroOptional.isPresent()) {
            deleteById(cadastro.getId());
            save(cadastro);
        }
        return cadastro;
    }

}
