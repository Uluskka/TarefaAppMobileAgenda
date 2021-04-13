package com.example.tarefaappmobileagenda.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tarefaappmobileagenda.R;
import com.example.tarefaappmobileagenda.dao.PersonagemDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaPersonagemActivity extends AppCompatActivity { // Codigo para selecionar o personagem desejado.

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem);
        setTitle("Lista de Personagens");

        PersonagemDAO dao = new PersonagemDAO();

        //List<String> personagem = new ArrayList<>(Arrays.asList("Alex", "Ken", "Ryu", "Guile"));

     FloatingActionButton botaoNovoPersonagem = findViewById(R.id.fab_add);
     botaoNovoPersonagem.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             startActivity(new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class)); //direciona para o formulario.
         }
     });



        ListView listaDePersonagens = findViewById(R.id.activity_main_list_personagem); //Lista de personagens.
        listaDePersonagens.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dao.todos()));

/*
        TextView primeiroPersonagem = findViewById(R.id.textView);
        TextView segundoPersonagem = findViewById(R.id.textView2);
        TextView terceiroPersonagem = findViewById(R.id.textView3);
        primeiroPersonagem.setText(personagem.get(0));
        segundoPersonagem.setText(personagem.get(1));
        terceiroPersonagem.setText(personagem.get(2));
*/

    }
}
