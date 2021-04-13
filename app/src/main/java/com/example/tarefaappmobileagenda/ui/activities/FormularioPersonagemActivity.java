package com.example.tarefaappmobileagenda.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tarefaappmobileagenda.R;
import com.example.tarefaappmobileagenda.dao.PersonagemDAO;
import com.example.tarefaappmobileagenda.model.Personagem;

public class FormularioPersonagemActivity extends AppCompatActivity {

    private EditText campoNome;
    private EditText campoAltura;
    private EditText campoNascimento;
    private  final PersonagemDAO dao = new PersonagemDAO();
    private Personagem Personagem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);
        //indica o titulo.
        setTitle("Formulario de Personagens");
        inicializacaoCampos();


        configBotao();


        //puxa as informacoes dentro do dao.
            Intent dados = getIntent();
            if (dados.hasExtra("personagem")) {
                Personagem personagem = (Personagem) dados.getSerializableExtra("personagem");
                campoNome.setText(personagem.getNome());
                campoAltura.setText(personagem.getAltura());
                campoNascimento.setText(personagem.getNascimento());
            } else{

                Personagem = new  Personagem();
            }
    }

    private void configBotao() {
        Button botaoSalvar = findViewById(R.id.button_salvar);      //config do botao para entender o comando disponivel para ele.
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {   //chama uma view.

                String nome = campoNome.getText().toString();
                String altura = campoAltura.getText().toString();               //armazena informaçao dentro da variavel string.
                String nascimento = campoNascimento.getText().toString();

                Personagem personagemSalvo = new Personagem(nome,altura,nascimento);

                //Usa o metodo salvar para armazenar o personagem nesse banco
                dao.salva(personagemSalvo);
                finish();


                //seta as informaçoes.
                personagemSalvo.setNome(nome);
                personagemSalvo.setAltura(altura);
                personagemSalvo.setNascimento(nascimento);
                dao.editar(personagemSalvo);

            }


        });
    }

    private void inicializacaoCampos() {
        //Recebe os ID dos campos.
        campoNome = findViewById(R.id.edittext_nome);
        campoAltura = findViewById(R.id.edittext_altura);
        campoNascimento = findViewById(R.id.edittext_nascimento);
    }


}
