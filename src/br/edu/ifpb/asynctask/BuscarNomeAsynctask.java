package br.edu.ifpb.asynctask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.edu.ifpb.activity.BuscarNomeActivity;
import br.edu.ifpb.util.HttpService;
import br.edu.ifpb.util.Response;
import android.os.AsyncTask;
import android.util.Log;

public class BuscarNomeAsynctask extends AsyncTask <JSONObject, Void, Response> {
	
	@Override
    protected Response doInBackground(JSONObject... jsons) {

        Response response = null;

        JSONObject json = jsons[0];
        Log.i("EditTextListener", "doInBackground (JSON): " + json);

        try {

            response = HttpService.sendJSONPostResquest("get-byname", json);

        } catch (IOException e) {

            Log.e("EditTextListener", e.getMessage());
        }

        return response;
    }

    @Override
    protected void onPostExecute(Response response) {
    	
    	String nome = null;
        List<String> nomes = new ArrayList<String>();
        int n = 0;

        try {
            JSONArray jsonArray = new JSONArray(response.getContentValue());

            while(jsonArray.getString(n)!= null) {
                JSONObject jo = new JSONObject(jsonArray.getString(n));
                nome = jo.getString("fullName");
                nomes.add(nome);
                n++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        BuscarNomeActivity.atualizarLista(nomes);

    }

}
