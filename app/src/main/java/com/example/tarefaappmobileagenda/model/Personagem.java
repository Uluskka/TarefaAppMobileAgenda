package com.example.tarefaappmobileagenda.model;

import androidx.annotation.NonNull;

public class Personagem {
// constructor feito para chamar as variaveis.
    private final String nome;
    private final String altura;
    private final String nascimento;

    public Personagem(String nome, String altura, String nascimento) { //variaveis usadas no aplicativo.

        this.nome = nome;
        this.altura = altura;
        this.nascimento = nascimento;
    }

    @NonNull
    @Override
    public  String toString(){ //retorna a string nome para o aplicativo.
        return nome;


    }

  /*  public String getNome() {
        return nome;
    }

    public String getAltura() {
        return altura;
    }

    public String getNascimento() {
        return nascimento;
    }*/
}
