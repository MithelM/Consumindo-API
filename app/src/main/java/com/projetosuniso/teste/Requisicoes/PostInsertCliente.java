package com.projetosuniso.teste.Requisicoes;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import com.google.gson.Gson;
import com.projetosuniso.teste.Contato;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.*;
import java.util.Iterator;

public class PostInsertCliente extends AsyncTask<Void, Void, Contato> {

    private final Context context;
    ProgressDialog dialog;
    String status;
    Contato contato;

    private final String nome, cpf;

    public PostInsertCliente(Context context, String nome, String cpf) {
        this.context = context;
        this.nome = nome;
        this.cpf = cpf;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = ProgressDialog.show(context, "Processando Solicitação", "Porfavor aguarde");
    }


    @Override
    protected Contato doInBackground(Void... voids) {

        contato = new Contato();
        JSONObject object = new JSONObject();

        URL url ;
        try {
            object.put("nome", nome);
            object.put("cpf", cpf);

            url = new URL("https://minebank-api.herokuapp.com/clientes");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            OutputStream os = connection.getOutputStream();
            /*BufferedWriter writer = new BufferedWriter( new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostString(object));
            writer.flush();
            writer.close();
            os.close();
            */
            os.write(object.toString().getBytes());
            os.flush();

            int responseCode = connection.getResponseCode();
            System.out.println("STATUS: " + responseCode + "   json:"+ object.toString());

            if (responseCode == HttpURLConnection.HTTP_OK) {

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                StringBuffer sb = new StringBuffer();
                String linea ;

                while ((linea = in.readLine()) != null) {

                    sb.append(linea);
                    break;
                }
                in.close();

                status = ("Cliente inserido com Sucesso: " + responseCode);

            }else{
                status = ( "Error "+ responseCode);
            }


        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Gson().fromJson( object.toString(), Contato.class );
    }

    @Override
    protected void onPostExecute(Contato contato) {
        super.onPostExecute(contato);
        dialog.dismiss();
        Toast.makeText(context, status, Toast.LENGTH_LONG).show();
    }

    public String getPostString(JSONObject object) throws Exception {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        Iterator<String> itr = object.keys();

        while (itr.hasNext()){

            String key = itr.next();
            Object value = object.get(key);

            if (first){
                first = false;
            }else {
                result.append("&");
            }

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        System.out.println(result.toString() + object);
        return result.toString();
    }


}
