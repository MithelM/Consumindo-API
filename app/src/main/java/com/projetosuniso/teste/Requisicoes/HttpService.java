package com.projetosuniso.teste.Requisicoes;

import android.os.AsyncTask;

import com.projetosuniso.teste.Contato;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class HttpService extends AsyncTask<Void, Void, ArrayList<Contato>> {


    @Override
    protected ArrayList<Contato> doInBackground(Void... voids) {
        ArrayList<Contato> listContrato = new ArrayList<Contato>();

        try {
            URL url = new URL("https://minebank-api.herokuapp.com/clientes");


            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);
            connection.connect();

            /*
            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
                resposta.append(scanner.next());
            }
             */
            Scanner scanner = new Scanner(url.openStream());

            JSONArray obj = new JSONArray(scanner.next());

            for (int i = 0; i < obj.length(); i++) {

                JSONObject contatoJson = obj.getJSONObject(i);
                // get employee name and salary
                String id = contatoJson.getString("id");
                String nome = contatoJson.getString("nome");
                String cpf = contatoJson.getString("cpf");

                Contato contato = new Contato();
                contato.setId(id);
                contato.setNome(nome);
                contato.setCpf(cpf);

                listContrato.add(contato);
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return listContrato;
    }



}
