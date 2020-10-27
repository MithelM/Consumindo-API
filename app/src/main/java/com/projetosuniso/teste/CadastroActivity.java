package com.projetosuniso.teste;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.projetosuniso.teste.Requisicoes.PostInsertCliente;

public class CadastroActivity extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtCPF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        Button btnSalvar = findViewById(R.id.btnSalvar);
        this.edtNome = findViewById(R.id.edtNome);
        this.edtCPF = findViewById(R.id.edtCPF);

            btnSalvar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                consumirServce();

                }
            });



    }

    public void onClickVoltar(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    public void consumirServce(){

        String nome = edtNome.getText().toString();
        String cpf = edtCPF.getText().toString();

        PostInsertCliente httpPost = new PostInsertCliente(CadastroActivity.this, nome, cpf);
        httpPost.execute();

    }
}