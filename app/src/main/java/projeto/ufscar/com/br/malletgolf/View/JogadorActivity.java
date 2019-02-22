package projeto.ufscar.com.br.malletgolf.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

import projeto.ufscar.com.br.malletgolf.DAO.ConfiguracaoFirebase;
import projeto.ufscar.com.br.malletgolf.R;
import projeto.ufscar.com.br.malletgolf.modelo.DadosJogador;
import projeto.ufscar.com.br.malletgolf.modelo.DadosOrganizador;

public class JogadorActivity extends AppCompatActivity {

    private EditText nome;
    private EditText categoria;
    private DatabaseReference databaseReference;
    private DadosJogador jogador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogador);

        nome = findViewById(R.id.editJogadorNome);
        categoria = findViewById(R.id.editJogadorCat);


        Button bntEntrar = findViewById(R.id.bntJogadorEntrar);
        bntEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                /*jogador = new DadosJogador();
                jogador.setNome(nome.getText().toString());
                jogador.setCategoria(categoria.getText().toString());*/
                /*fazer uma classe para inicializar*/
                inicializarFirebase();
                String reconheceNome = nome.getText().toString().trim();
                String reconheceCat = categoria.getText().toString().trim();

                pesquisarJogador(reconheceNome, reconheceCat);
            }
        });

    }

    private void pesquisarJogador(final String reconheceNome, final String reconheceCat) {
        //Query query;

        jogador = new DadosJogador();
        jogador.setNome(reconheceNome);
        jogador.setCategoria(reconheceCat);

        /*query = databaseReference.child("Jogador").child(jogador.getCategoria())
                .equalTo(reconheceCat,reconheceNome);*/

        databaseReference.child("Jogador").child(jogador.getCategoria()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    DadosJogador jogador =  dataSnapshot.getValue(DadosJogador.class);

                    /*verificação para ver se existe no banco*/
                    if(reconheceNome.equals(jogador.getNome()) && reconheceCat.equals(jogador.getCategoria())){
                        Toast.makeText(JogadorActivity.this, "Jogador encontrado ", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(JogadorActivity.this, "Jogador não encontrado ", Toast.LENGTH_SHORT).show();
                    }
                /*se nao existir algum no*/
                } else {
                    Toast.makeText(JogadorActivity.this, "Jogador não encontrado ", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(JogadorActivity.this);
        databaseReference = ConfiguracaoFirebase.getFirebase();
    }

}
