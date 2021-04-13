package com.example.tarefaappmobileagenda.dao;

import com.example.tarefaappmobileagenda.model.Personagem;

import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO {

    private final static List<Personagem> personagens = new ArrayList<>();


    public void salva(Personagem personagemSalvo) {

        personagens.add(personagemSalvo);

    }

    public List<Personagem> todos(){       //metodo de retorno.

        return new ArrayList<>(personagens);
    }


}
