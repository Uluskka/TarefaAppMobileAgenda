package com.example.tarefaappmobileagenda.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tarefaappmobileagenda.R;
import com.example.tarefaappmobileagenda.dao.PersonagemDAO;
import com.example.tarefaappmobileagenda.model.Personagem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static com.example.tarefaappmobileagenda.ui.activities.ConstantesActivities.CHAVE_PERSONAGEM;

public class ListaPersonagemActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de Personagens";
    private final PersonagemDAO dao = new PersonagemDAO();

    //override para lista de personagens.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem);
        setTitle(TITULO_APPBAR); //seta o titulo.
        configuraFabNovoPersonagem();


    }

    private void configuraFabNovoPersonagem() {
        FloatingActionButton botaoNovoPersonagem = findViewById(R.id.fab_add);
        botaoNovoPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                abreFormulario();       //direciona para o formulario.
            }
        });
    }

    private void abreFormulario() {
        startActivity(new Intent(this, FormularioPersonagemActivity.class));
    }

    @Override
    protected void onResume() {  //salva os dados digitados no aplicativo para nao serem apagados.
        super.onResume();
        // declarando o index dao para utiliza-lo.
        //PersonagemDAO dao = new PersonagemDAO();

        ListView listaDePersonagens = findViewById(R.id.activity_main_list_personagem); //Lista de personagens.
        final List<Personagem> personagens = dao.todos(); // armazena a informaçao do personagens.
        listaDePersonagens(listaDePersonagens, personagens);
        configuraItemPorClique(listaDePersonagens);

    }

    private void configuraItemPorClique(ListView listaDePersonagens) {
        listaDePersonagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) { //Faz uma meio de entrada com os dados digitados
                Personagem personagemEscolhido = (Personagem) adapterView.getItemAtPosition(posicao);
                abreFormularioEditar(personagemEscolhido);



            }

            private void abreFormularioEditar(Personagem personagemEscolhido) {
                Intent vaiParaFormulario = new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class);
                vaiParaFormulario.putExtra(CHAVE_PERSONAGEM, personagemEscolhido);
                startActivity(vaiParaFormulario);
            }
        });
    }

    private void listaDePersonagens(ListView listaDePersonagens, List<Personagem> personagens) {
        listaDePersonagens.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personagens));
    }

}
