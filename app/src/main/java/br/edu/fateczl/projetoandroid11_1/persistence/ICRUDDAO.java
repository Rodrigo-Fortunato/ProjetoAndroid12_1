package br.edu.fateczl.projetoandroid11_1.persistence;

import java.sql.SQLException;
import java.util.List;
/*
 *@author:<Rodrigo Fortunato Martins Neves>
 */
public interface ICRUDDAO<T>{

    public void insert(T t) throws SQLException;
    public int update(T t) throws SQLException;
    public void delete(T t) throws SQLException;
    public T findOne(T t) throws SQLException;
    public List<T> findAll() throws SQLException;

}
