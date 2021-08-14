package org.aso.repositories;

import org.aso.domain.Cadastro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CadastroRepositoryJPA extends JpaRepository<Cadastro, Long> {

    public Cadastro findByNome(String nome);

    @Query("select cadastro from Cadastro cadastro where cadastro.email = ?1")
    public Cadastro findByEmail(String email);

    @Query(value  = "select * from cadastro where cadastro.email = ?1", nativeQuery = true)
    public List<Cadastro> findByEmailNativo(String email);
}