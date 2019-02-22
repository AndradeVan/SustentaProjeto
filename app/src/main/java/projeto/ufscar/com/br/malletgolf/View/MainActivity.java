package projeto.ufscar.com.br.malletgolf.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import projeto.ufscar.com.br.malletgolf.DAO.ConfiguracaoFirebase;
import projeto.ufscar.com.br.malletgolf.R;
import projeto.ufscar.com.br.malletgolf.modelo.DadosOrganizador;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText senha;
    private FirebaseAuth autenticacao;
    private DadosOrganizador organizador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.editemail);
        senha = findViewById(R.id.editsenha);

        Button bntEntrar = findViewById(R.id.bntOrganizaEntrar);
        bntEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().equals("") && senha.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this,"Email ou Senha não foram preenchidos",
                            Toast.LENGTH_SHORT).show();
                }else{
                    organizador = new DadosOrganizador();
                    organizador.setEmail(email.getText().toString());
                    organizador.setSenha(senha.getText().toString());
                    validarLogin();
                }
            }
        });
    }
    private void validarLogin(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(organizador.getEmail(), organizador.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Toast.makeText(MainActivity.this,"Login efetuado com sucesso", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, OrganizadorActivity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(MainActivity.this,"Senha ou Email invalido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
