package br.edu.fateczl.projetoandroid11_1.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.projetoandroid11_1.model.Jogador;
import br.edu.fateczl.projetoandroid11_1.model.Time;

public class JogadorDAO implements IJogadorDAO, ICRUDDAO<Jogador> {

    private final Context CONTEXT;
    private GenericDAO genericDAO;
    private SQLiteDatabase database;

    public JogadorDAO(Context context) {
        this.CONTEXT = context;
    }

    @Override
    public JogadorDAO open() throws SQLException {
        genericDAO = new GenericDAO(CONTEXT);
        database = genericDAO.getWritableDatabase();
        return this;
    }

    @Override
    public void close() {
        genericDAO.close();
    }

    @Override
    public void insert(Jogador jogador) throws SQLException {
        ContentValues contentValues = getContentValues(jogador);
        database.insert("jogador", null, contentValues);

    }

    @Override
    public int update(Jogador jogador) throws SQLException {
        ContentValues contentValues = getContentValues(jogador);
        return database.update("jogador", contentValues, "id = " + jogador.getId(), null);
    }

    @Override
    public void delete(Jogador jogador) throws SQLException {

        database.delete("jogador", "id = " + jogador.getId(), null);
    }

    @SuppressLint("Range")
    @Override
    public Jogador findOne(Jogador jogador) throws SQLException {
        String sql = "SELECT j.id as id_jogador, j.nome as nome_jogador, j.data_nasc as nasc_jogador," +
                " j.altura as altura_jogador, j.peso as peso_jogador, t.codigo as codigo_time," +
                " t.nome as nome_time , t.cidade as cidade_time" +
                " FROM jogador as j INNER JOIN time as t  ON j.codigo_time = t.codigo AND j.id = " + jogador.getId();

        Cursor cursor = database.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        if (!cursor.isAfterLast()) {
            Time time = new Time();

            time.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo_time")));
            time.setNome(cursor.getString(cursor.getColumnIndex("nome_time")));
            time.setCidade(cursor.getString(cursor.getColumnIndex("cidade_time")));

            jogador.setId(cursor.getInt(cursor.getColumnIndex("id_jogador")));
            jogador.setNome(cursor.getString(cursor.getColumnIndex("nome_jogador")));
            jogador.setDataNasc(LocalDate.parse(cursor.getString(cursor.getColumnIndex("nasc_jogador"))));
            jogador.setAltura(cursor.getInt(cursor.getColumnIndex("altura_jogador")));
            jogador.setPeso(cursor.getInt(cursor.getColumnIndex("peso_jogador")));
            jogador.setTime(time);

        }
        cursor.close();
        return jogador;
    }

    @SuppressLint("Range")
    @Override
    public List<Jogador> findAll() throws SQLException {
        List<Jogador> jogadores = new ArrayList<>();
        String sql = "SELECT j.id as id_jogador, j.nome as nome_jogador, j.data_nasc as nasc_jogador," +
                " j.altura as altura_jogador, j.peso as peso_jogador, t.codigo as codigo_time," +
                " t.nome as nome_time , t.cidade as cidade_time" +
                " FROM jogador as j INNER JOIN time as t  ON j.codigo_time = t.codigo;";

        Cursor cursor = database.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        while (!cursor.isAfterLast()) {
            Time time = new Time();
            Jogador jogador = new Jogador();
            time.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo_time")));
            time.setNome(cursor.getString(cursor.getColumnIndex("nome_time")));
            time.setCidade(cursor.getString(cursor.getColumnIndex("cidade_time")));

            jogador.setId(cursor.getInt(cursor.getColumnIndex("id_jogador")));
            jogador.setNome(cursor.getString(cursor.getColumnIndex("nome_jogador")));
            jogador.setDataNasc(LocalDate.parse(cursor.getString(cursor.getColumnIndex("nasc_jogador"))));
            jogador.setAltura(cursor.getInt(cursor.getColumnIndex("altura_jogador")));
            jogador.setPeso(cursor.getInt(cursor.getColumnIndex("peso_jogador")));
            jogador.setTime(time);

            jogadores.add(jogador);
            cursor.moveToNext();
        }
        cursor.close();
        return jogadores;
    }

    private static ContentValues getContentValues(Jogador jogador) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", jogador.getId());
        contentValues.put("nome", jogador.getNome());
        contentValues.put("data_nasc", String.valueOf(jogador.getDataNasc()));
        contentValues.put("altura", jogador.getAltura());
        contentValues.put("peso", jogador.getPeso());
        contentValues.put("time_codigo", jogador.getTime().getCodigo());
        return contentValues;
    }
}
