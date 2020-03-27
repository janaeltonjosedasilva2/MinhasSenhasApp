package br.usjt.devmobile.minhassenhasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import br.usjt.devmobile.minhassenhasapp.model.Usuario;

import static br.usjt.devmobile.minhassenhasapp.ContainsActivities.KEYUSUARIO;
import static br.usjt.devmobile.minhassenhasapp.ContainsActivities.TAGCADASTROALUNO;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private TextInputEditText login;
    private TextInputEditText senha;
    private TextInputEditText pergunta;
    private TextInputEditText resposta;

    Usuario usuario = new Usuario();
    private final static List<Usuario> usuarios = new ArrayList<Usuario>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        setTitle("Cadastro");
        configuraCampostoDeCadastro();

        Hawk.init(this).build();
    }

    private void configuraCampostoDeCadastro() {
        login = (TextInputEditText)findViewById(R.id.CadastroActivity_UsuarioEditTextInput);
        senha = (TextInputEditText)findViewById(R.id.CadastroActivity_SenhaEditTextInput);
        pergunta = (TextInputEditText)findViewById(R.id.CadastroActivity_PerguntaEditTextInput);
        resposta = (TextInputEditText)findViewById(R.id.CadastroActivity_RespostaEditTextInput);
    }

    public void cadastrar(View view) {
        Log.d(TAGCADASTROALUNO, "Clicou no fazer Cadastro!");

        configuraListaDeUsuario();

        adicionaUsuarioNoHawk();

        Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show();

        finish();
    }

    private void adicionaUsuarioNoHawk() {
        try {
            Hawk.put(KEYUSUARIO, usuarios);
        }catch (Exception e){
            Log.e("ErroHawk", "Clicou no fazer Cadastro!");
        }
    }

    private void configuraListaDeUsuario() {
        usuario.setLogin(login.getText().toString());
        usuario.setSenha(senha.getText().toString());
        usuario.setPergunta(pergunta.getText().toString());
        usuario.setResposta(resposta.getText().toString());

        usuarios.add(usuario);
    }
}
