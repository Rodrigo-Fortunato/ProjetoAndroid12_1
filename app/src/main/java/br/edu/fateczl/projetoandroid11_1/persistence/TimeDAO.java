package br.edu.fateczl.projetoandroid11_1.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.edu.fateczl.projetoandroid11_1.model.Jogador;
import br.edu.fateczl.projetoandroid11_1.model.Time;

public class TimeDAO implements ITimeDAO,ICRUDDAO<Time> {
    private final Context CONTEXT;
    private GenericDAO genericDAO;
    private SQLiteDatabase database;

    public TimeDAO(Context CONTEXT) {
        this.CONTEXT = CONTEXT;
    }

    @Override
    public TimeDAO open() {
        genericDAO = new GenericDAO(CONTEXT);
        database = genericDAO.getWritableDatabase();
        return this;
    }

    @Override
    public void close() {
        genericDAO.close();
    }

    @Override
    public void insert(Time time) throws SQLException {
        ContentValues contentValues = getContentValues(time);
        database.insert("time",null,contentValues);
    }

    @Override
    public int update(Time time) throws SQLException {
        ContentValues contentValues = getContentValues(time);
        return database.update("time", contentValues,"codigo = "+ time.getCodigo(),null);
    }

    @Override
    public void delete(Time time) throws SQLException {
        database.delete("time", "codigo = " + time.getCodigo(),null);
    }

    @SuppressLint("Range")
    @Override
    public Time findOne(Time time) throws SQLException {
        String sql = "SELECT * FROM time WHERE codigo = "+ time.getCodigo();
        Cursor cursor = database.rawQuery(sql,null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        if (!cursor.isAfterLast()){
            time.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
            time.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            time.setCidade(cursor.getString(cursor.getColumnIndex("cidade")));
        }

        cursor.close();
        return time;
    }

    @SuppressLint("Range")
    @Override
    public List<Time> findAll() throws SQLException {
        List<Time> times = new ArrayList<>();
        String sql = "SELECT * FROM time;";
        Cursor cursor = database.rawQuery(sql,null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        while (!cursor.isAfterLast()){
            Time time = new Time();
            time.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
            time.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            time.setCidade(cursor.getString(cursor.getColumnIndex("cidade")));

            times.add(time);
            cursor.moveToNext();
        }

        cursor.close();
        return times;

    }
    private static ContentValues getContentValues(Time time) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("codigo", time.getCodigo());
        contentValues.put("nome", time.getNome());
        contentValues.put("cidade", time.getCidade());



        return contentValues;
    }
}
