package br.edu.fateczl.projetoandroid11_1.persistence;

import java.sql.SQLException;

public interface IJogadorDAO {
    public JogadorDAO open() throws SQLException;
    public void close();
}
