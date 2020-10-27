package com.projetosuniso.teste.Requisicoes;

import android.os.AsyncTask;
import com.projetosuniso.teste.Contato;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

public class GetClientePorCPF extends AsyncTask<Void,Void, Contato> {

    private final String cpf;

    public GetClientePorCPF(String cpf) {
        this.cpf = cpf;
    }



    @Override
    protected Contato doInBackground(Void... voids) {
        Contato contato = new Contato();

        try {
            URL url = new URL("https://minebank-api.herokuapp.com/clientes/cpf/"+ cpf);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);
            connection.connect();

            Scanner scanner = new Scanner(url.openStream());

            JSONObject obj = new JSONObject(scanner.next());

            String id = obj.getString("id");
            String nome = obj.getString("nome");
            String cpf = obj.getString("cpf");

            contato.setId(id);
            contato.setNome(nome);
            contato.setCpf(cpf);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return contato;
    }
}
