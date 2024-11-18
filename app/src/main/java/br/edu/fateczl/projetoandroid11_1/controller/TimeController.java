package br.edu.fateczl.projetoandroid11_1.controller;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import br.edu.fateczl.projetoandroid11_1.model.Time;
import br.edu.fateczl.projetoandroid11_1.persistence.TimeDAO;

public class TimeController implements IController<Time>{
    private final TimeDAO timeDAO;

    public TimeController(TimeDAO timeDAO) {
        this.timeDAO = timeDAO;
    }

    @Override
    public void insert(Time time) throws SQLException {
        if (timeDAO.open() == null){
            timeDAO.open();
        }
        timeDAO.insert(time);
        timeDAO.close();
    }

    @Override
    public void update(Time time) throws SQLException {
        if (timeDAO.open() == null){
            timeDAO.open();
        }
        timeDAO.update(time);
        timeDAO.close();
    }

    @Override
    public void delete(Time time) throws SQLException {
        if (timeDAO.open() == null){
            timeDAO.open();
        }
        timeDAO.delete(time);
        timeDAO.close();
    }

    @Override
    public Time findOne(Time time) throws SQLException {
        if (timeDAO.open() == null){
            timeDAO.open();
        }
        return timeDAO.findOne(time);

    }

    @Override
    public List<Time> findALL() throws SQLException {
        if (timeDAO.open() == null){
            timeDAO.open();
        }
        return timeDAO.findAll();
    }
}
