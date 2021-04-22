package com.example.tarefaappmobileagenda.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.ims.ImsMmTelManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tarefaappmobileagenda.R;
import com.example.tarefaappmobileagenda.dao.PersonagemDAO;
import com.example.tarefaappmobileagenda.model.Personagem;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import static com.example.tarefaappmobileagenda.ui.activities.ConstantesActivities.CHAVE_PERSONAGEM;

public class FormularioPersonagemActivity extends AppCompatActivity {


    public static final String TITULO_APPBAR_EDITAR_PERSONAGEM = "Editar Personagens";
    public static final String TITULO_APPBAR_NOVO_PERSONAGEM = "Novo Personagens";
    private EditText campoNome;
    private EditText campoAltura;
    private EditText campoNascimento;
    private final PersonagemDAO dao = new PersonagemDAO();
    private Personagem personagem;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_personagem_menu_salvar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_formulario_personagem_menu_salvar) {
            finalizarFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);
        //indica o titulo.
        inicializacaoCampos(); // possibilita editar textos.
        configBotao(); //Configura o botao .
        carregaPersonagem(); // carrega o personagem digitado.

    }

    private void carregaPersonagem() {

        //puxa as informacoes dentro do dao.
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_PERSONAGEM)) {
            setTitle(TITULO_APPBAR_EDITAR_PERSONAGEM);    //edita informacoes.
            personagem = (Personagem) dados.getSerializableExtra(CHAVE_PERSONAGEM);
            preencheCampos(personagem);
        } else {
            setTitle(TITULO_APPBAR_NOVO_PERSONAGEM);

            personagem = new Personagem();
        }
    }

    private void preencheCampos(Personagem personagem) {
        campoNome.setText(personagem.getNome());
        campoAltura.setText(personagem.getAltura());
        campoNascimento.setText(personagem.getNascimento());
    }

    private void configBotao() {
        Button botaoSalvar = findViewById(R.id.button_salvar);      //config do botao para entender o comando disponivel para ele.
        botaoSalvar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {   //chama uma view.
                finalizarFormulario();


            }


        });
    }

    private void finalizarFormulario() {
        preencherPersonagem();
        if (personagem.IdValido()){
            dao.editar(personagem);
            finish();

        } else {
            dao.salva(personagem);
        }
        finish();
    }

    private void inicializacaoCampos() {
        //Recebe os ID dos campos.
        campoNome = findViewById(R.id.edittext_nome);
        campoAltura = findViewById(R.id.edittext_altura);
        campoNascimento = findViewById(R.id.edittext_nascimento);
        //identifca o campo para pode alterar.
        SimpleMaskFormatter smfAltura = new SimpleMaskFormatter("N,NN");
        MaskTextWatcher mtwAltura = new MaskTextWatcher(campoAltura, smfAltura);
        campoAltura.addTextChangedListener(mtwAltura);

        SimpleMaskFormatter smfNascimento = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher mtwNascimento = new MaskTextWatcher(campoNascimento, smfNascimento);
        campoNascimento.addTextChangedListener(mtwNascimento);
    }

    private void preencherPersonagem() { //passa as informacoes digitas que voce deseja.

        String nome = campoNome.getText().toString();
        String nascimento = campoNascimento.getText().toString();
        String altura = campoAltura.getText().toString();


        personagem.setNome(nome);
        personagem.setNascimento(nascimento);
        personagem.setAltura(altura);


    }


}
