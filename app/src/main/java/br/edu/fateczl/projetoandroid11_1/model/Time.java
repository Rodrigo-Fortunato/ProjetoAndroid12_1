package br.edu.fateczl.projetoandroid11_1.model;

import androidx.annotation.NonNull;

/*
 *@author:<Rodrigo Fortunato Martins Neves>
 */
public class Time {
    private int codigo;
     private String nome;
     private String cidade;

    @NonNull
    @Override
    public String toString() {
        return "Time{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", cidade='" + cidade + '\'' +
                '}';
    }

    public Time() {
        super();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
