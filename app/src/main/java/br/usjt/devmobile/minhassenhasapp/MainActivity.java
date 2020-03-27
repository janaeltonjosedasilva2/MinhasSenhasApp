package br.usjt.devmobile.minhassenhasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.orhanobut.hawk.Hawk;

import java.util.List;

import br.usjt.devmobile.minhassenhasapp.model.Usuario;

import static br.usjt.devmobile.minhassenhasapp.ContainsActivities.KEYUSUARIO;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText usuarioDigitado;
    private TextInputEditText senhaDigitada;
    Usuario usuario = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Login");
        setContentView(R.layout.activity_main);
        Hawk.init(this).build();

        configuraCamposDigitados();
    }

    private void configuraCamposDigitados() {
        usuarioDigitado = (TextInputEditText) findViewById(R.id.MainActivity_EmailEditTextInput);
        senhaDigitada = (TextInputEditText) findViewById(R.id.MainActivity_PasswordEditTextInput);
    }

    public void fazerLogin(View view) {

        if (Hawk.contains(KEYUSUARIO)) {
            buscaUsuarioESenha();
        }

    }

    private void buscaUsuarioESenha() {
        List<Usuario> usuarios = Hawk.get(KEYUSUARIO);
        boolean possuiLogin = false;
        for (Usuario a :
                usuarios) {
            if (a.getLogin().equals(usuarioDigitado.getText().toString())) {
                if (a.getSenha().equals(senhaDigitada.getText().toString())) {
                    possuiLogin = true;
                    Intent intent = new Intent(this, ListaSenhasActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Senha incorreta", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        }
        if (!possuiLogin){
            Toast.makeText(this, "Login não existe", Toast.LENGTH_SHORT).show();
        }
    }

    public void novoCadastro(View view) {
        Intent intent = new Intent(this, CadastroUsuarioActivity.class);
        startActivity(intent);
    }
}
