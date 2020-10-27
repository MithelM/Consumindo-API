package com.projetosuniso.teste;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.projetosuniso.teste.Requisicoes.GetClientePorCPF;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView resposta = findViewById(R.id.etMain_resposta);

        Button btnBuscarCep = findViewById(R.id.btnMain_buscarCep);

        btnBuscarCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Contato contato = new GetClientePorCPF("44488855522").execute().get();


                        resposta.setText("\nID: " + contato.getId() + "\nNome: " + contato.getNome() + "\ncpf: " + contato.getCpf());


                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void onClickCadastro(View view) {
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);
    }
}