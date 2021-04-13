package com.example.tarefaappmobileagenda.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Personagem implements Serializable {
// constructor feito para chamar as variaveis.
    private  String nome;
    private  String altura;
    private  String nascimento;
    private  int id = 0;

    public Personagem(String nome, String altura, String nascimento) { //variaveis usadas no aplicativo.
        // indica elas
        this.nome = nome;
        this.altura = altura;
        this.nascimento = nascimento;
    }

    public Personagem(){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    // converte o nome para string para exibiçao.
    @NonNull
    @Override
    public  String toString(){ return nome; }       //retorna a string nome para o aplicativo.

    public void setId(int id){ this.id = id;}       //posicionamento na localizaçao da lista.

    public int getId(){ return id; }


}



