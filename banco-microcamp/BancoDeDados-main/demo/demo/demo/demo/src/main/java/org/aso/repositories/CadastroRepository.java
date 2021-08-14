package org.aso.repositories;

import org.aso.domain.Cadastro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CadastroRepository {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private org.aso.repositories.CadastroRepositoryJPA cadastroRepository;

    public Cadastro findById(Long id) {
        return cadastroRepository.findById(id).get();
    }

    public Cadastro procuraNome(String nome){
        return cadastroRepository.findByNome(nome);
    }

    public List<Cadastro> findAll() {
        return cadastroRepository.findAll();
    }

    public Cadastro save(Cadastro cadastro) {
        return cadastroRepository.save(cadastro);
    }

    public void update(Cadastro cadastro) {
        cadastroRepository.save(cadastro);
    }

    public void deleteById(Long id) {
        cadastroRepository.deleteById(id);
    }

    public boolean exist(Long id) {
        return cadastroRepository.existsById(id);
    }

    public void insertCadastroTest() {

        try (Connection conn = dataSource.getConnection()) {

            PreparedStatement statement = conn.prepareStatement("insert into cadastro ( id, usuario, email, senha, confirmarSenha, "
                    + "nome, sobrenome, cidade, estado, genero) values (?, ?, ?, ?, ?, ?, ?, ?) ");
            statement.setString(1, "123");
            statement.setString(2, "cliente");
            statement.setString(3, "exemplo@gmail.com");
            statement.setString(4, "0001");
            statement.setString(5, "0001");
            statement.setString(6, "Fulano");
            statement.setString(7, "de Tal");
            statement.setString(8, "Uberlândia");
            statement.setString(9, "Minas Gerais");
            statement.setString(10, "M");

            int n = statement.executeUpdate();

            if(n == 0) {
                System.out.println("Não salvou!");
            }
        } catch (Exception ex){

            throw new RuntimeException(ex);
        }

    }

    public List<Cadastro> findByQQ(String nome) {

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("select * from Cadastro where nome like ?");
            statement.setString(1, "%" + nome + "%");

            ResultSet rs = statement.executeQuery();
            List<Cadastro> cadastros = new ArrayList<>();

            while (rs.next()) {

                Cadastro cadastro = new Cadastro();
                cadastro.setId(rs.getLong("id"));
                cadastro.setUsuario(rs.getString("cliente"));
                cadastro.setEmail(rs.getString("email"));
                cadastro.setSenha(rs.getString("senha"));
                cadastro.setConfirmarSenha(rs.getString("confirmarSenha"));
                cadastro.setNome(rs.getString("nome"));
                cadastro.setSobrenome(rs.getString("sobrenome"));
                cadastro.setCidade(rs.getString("cidade"));
                cadastro.setEstado(rs.getString("estado"));
                cadastro.setGenero(rs.getString("M"));

                cadastros.add(cadastro);
            }
            return cadastros;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
