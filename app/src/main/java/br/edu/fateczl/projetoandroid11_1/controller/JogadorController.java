package br.edu.fateczl.projetoandroid11_1.controller;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import br.edu.fateczl.projetoandroid11_1.model.Jogador;
import br.edu.fateczl.projetoandroid11_1.persistence.JogadorDAO;

public class JogadorController implements IController<Jogador>{

    private final JogadorDAO jogadorDAO;

    public JogadorController(JogadorDAO jogadorDAO) {
        this.jogadorDAO = jogadorDAO;
    }


    @Override
    public void insert(Jogador jogador) throws SQLException {

        if (jogadorDAO.open() == null){
            jogadorDAO.open();
        }
        jogadorDAO.insert(jogador);
        jogadorDAO.close();
    }

    @Override
    public void update(Jogador jogador) throws SQLException {
        if (jogadorDAO.open() == null){
            jogadorDAO.open();
        }
        jogadorDAO.update(jogador);
        jogadorDAO.close();
    }

    @Override
    public void delete(Jogador jogador) throws SQLException {
        if (jogadorDAO.open() == null){
            jogadorDAO.open();
        }
        jogadorDAO.delete(jogador);
        jogadorDAO.close();
    }

    @Override
    public Jogador findOne(Jogador jogador) throws SQLException {
        if (jogadorDAO.open() == null){
            jogadorDAO.open();
        }
        return jogadorDAO.findOne(jogador);


    }

    @Override
    public List<Jogador> findALL() throws SQLException {
        if (jogadorDAO.open() == null){
            jogadorDAO.open();
        }
        return jogadorDAO.findAll();


    }
}
