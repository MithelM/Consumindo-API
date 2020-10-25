package com.projetosuniso.teste;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;

public class CadastroActivity extends AppCompatActivity {

    private Button btnSalvar;
    private EditText edtNome;
    private EditText edtCPF;
    private Contato contato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        this.btnSalvar = (Button) findViewById(R.id.btnSalvar);
        this.edtNome = (EditText) findViewById(R.id.edtNome);
        this.edtCPF = (EditText) findViewById(R.id.edtCPF);

            this.btnSalvar.setOnClickListener(new View.OnClickListener() {
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

        HttpPost httpPost = new HttpPost(CadastroActivity.this, nome, cpf);
        httpPost.execute();

    }
}