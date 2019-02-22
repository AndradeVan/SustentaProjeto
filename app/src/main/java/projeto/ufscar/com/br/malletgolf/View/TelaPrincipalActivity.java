package projeto.ufscar.com.br.malletgolf.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import projeto.ufscar.com.br.malletgolf.R;

public class TelaPrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);


        Button entrarJogador = findViewById(R.id.bntPrincJog);
        Button entrarOrganizador = findViewById(R.id.bntPrincOrg);

        entrarJogador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irparajogador = new Intent(TelaPrincipalActivity.this,JogadorActivity.class);
                startActivity(irparajogador);
            }
        });
       entrarOrganizador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irparaOrganizador = new Intent(TelaPrincipalActivity.this,MainActivity.class);
                startActivity(irparaOrganizador);
            }
        });

    }
}
