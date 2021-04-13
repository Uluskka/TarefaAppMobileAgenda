package com.example.tarefaappmobileagenda.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tarefaappmobileagenda.R;
import com.example.tarefaappmobileagenda.dao.PersonagemDAO;
import com.example.tarefaappmobileagenda.model.Personagem;

public class FormularioPersonagemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);
        setTitle("Formulario de Personagens");

        PersonagemDAO dao = new PersonagemDAO();

        EditText campoNome = findViewById(R.id.edittext_nome);
        EditText campoAltura = findViewById(R.id.edittext_altura);          //Vincula a variavel campo com o objeto xml
        EditText campoNascimento = findViewById(R.id.edittext_nascimento);


        Button botaoSalvar = findViewById(R.id.button_salvar);      //config do botao para entender o comando disponivel para ele.
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {   //chama uma view.

                String nome = campoNome.getText().toString();
                String altura = campoAltura.getText().toString();               //armazena informaçao dentro da variavel string.
                String nascimento = campoNascimento.getText().toString();

                Personagem personagemSalvo = new Personagem(nome, altura, nascimento);

                dao.salva(personagemSalvo);

                    startActivity(new Intent(FormularioPersonagemActivity.this, ListaPersonagemActivity.class)); //Busca informaçao em cada um dos campos para passar os parametros para dentro dele
                //mando para o dao e armazena.


                /*Toast.makeText(FormularioPersonagemActivity.this,
                        personagemSalvo.getNome() + " - "+personagemSalvo.getAltura()+ " - "+personagemSalvo.getNascimento(), Toast.LENGTH_SHORT).show();*/
                //toast feito para aparecer a mensagem das informaçoes.

                new Personagem(nome, altura, nascimento);

                //Toast.makeText(FormularioPersonagemActivity.this,"Personagem Salvo!",Toast.LENGTH_SHORT).show(); //sistema de teste do botao para salvar.
            }
        });
    }


}
