package br.edu.ifpb.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import br.edu.ifpb.asynctask.BuscarNomeAsynctask;

import com.example.atividadetextwatcher.R;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class BuscarNomeActivity extends Activity implements TextWatcher{

	private static int TAMANHO_MINIMO_TEXTO = 4;

    private EditText nomeEditText;
    static List<String> nomes;
    static ArrayAdapter<String> adapter;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buscar_nome);
	
		nomeEditText = (EditText) findViewById(R.id.nomeEditText);
        nomeEditText.addTextChangedListener(this);

        ListView nomesListView = (ListView) findViewById(R.id.nomesListView);
        nomes = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                nomes);

        nomesListView.setAdapter(adapter);
	
	
	}

	@Override
	public void beforeTextChanged(CharSequence charSequence, int start, int count,
			int after) {
		Log.i("EditTextListener","beforeTextChanged: " + charSequence);
		
	}

	@Override
	public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
		  Log.i("EditTextListener", "onTextChanged: " + charSequence);
	        
		  String nome = charSequence.toString();

	        // Consultar o servidor. Criar o JSONObject e uma AsyncTask<JSONObject, Void, Response>
	        try {

	            if (nome.length() >= TAMANHO_MINIMO_TEXTO) {
	                // JSON
	                JSONObject json = new JSONObject();
	                json.put("fullName", nome);

	                BuscarNomeAsynctask buscarNomeAsyncTask = new BuscarNomeAsynctask();
	                buscarNomeAsyncTask.execute(json);	            
	            }else{
	            	
	            	adapter.clear();
	            }

	        } catch (JSONException e) {

	            Log.e("EditTextListener", e.getMessage());
	        }
	}

	@Override
	public void afterTextChanged(Editable editable) {
		Log.i("EditTextListener","afterTextChanged: " + editable);
		
	}
	 public static void atualizarLista(List<String> nomes1){
	        adapter.clear();
	        nomes.addAll(nomes1);
	        adapter.notifyDataSetChanged();
	    }
	

	
}
