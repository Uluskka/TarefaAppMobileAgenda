package com.example.tarefaappmobileagenda.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tarefaappmobileagenda.R;
import com.example.tarefaappmobileagenda.dao.PersonagemDAO;
import com.example.tarefaappmobileagenda.model.Personagem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaPersonagemActivity extends AppCompatActivity { // Codigo para selecionar o personagem desejado.

    private final PersonagemDAO dao = new PersonagemDAO();

    //override para lista de personagens.
    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem);
        setTitle("Lista de Personagens");
        dao.salva(new Personagem("ken", "1,80","02041979"));
        dao.salva(new Personagem("ryu", "1,80","02041979"));


        //array
        //List<String> personagem = new ArrayList<>(Arrays.asList("Alex", "Ken", "Ryu", "Guile"));

     FloatingActionButton botaoNovoPersonagem = findViewById(R.id.fab_add);
     botaoNovoPersonagem.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             startActivity(new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class)); //direciona para o formulario.
         }
     });


/*
        TextView primeiroPersonagem = findViewById(R.id.textView);
        TextView segundoPersonagem = findViewById(R.id.textView2);
        TextView terceiroPersonagem = findViewById(R.id.textView3);
        primeiroPersonagem.setText(personagem.get(0));
        segundoPersonagem.setText(personagem.get(1));
        terceiroPersonagem.setText(personagem.get(2));
*/

    }

    @Override
    protected void onResume() {             //salva os dados digitados no aplicativo para nao serem apagados.
        super.onResume();
        // declarando o index dao para utiliza-lo.
        //PersonagemDAO dao = new PersonagemDAO();

        ListView listaDePersonagens = findViewById(R.id.activity_main_list_personagem); //Lista de personagens.
        List<Personagem> personagens = dao.todos(); // armazena a informaçao do personagens.
        listaDePersonagens.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personagens));

        listaDePersonagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) { //Faz uma meio de entrada com os dados digitados
                Personagem personagemEscolhido = personagens.get(posicao);
                //Log.i("personagem","" + personagemEscolhido);
                Intent vaiParaFormulario = new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class);
                vaiParaFormulario.putExtra("personagem", personagemEscolhido);
                startActivity(vaiParaFormulario);

            }
        });

    }

}
