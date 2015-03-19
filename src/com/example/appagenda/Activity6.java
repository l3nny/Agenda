package com.example.appagenda;




import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import BD.JSONParser;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



import android.support.v7.app.ActionBarActivity;

public class Activity6 extends ActionBarActivity {
	
	// Progress Dialog
		private ProgressDialog pDialog;

		JSONParser jsonParser = new JSONParser();
		EditText correo ;
		EditText nombre;
		EditText contrasena;

	private static String url_create_user = "http://ylla.co/create_user.php";


	// JSON Node names
		private static final String TAG_SUCCESS = "success";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity6);
		  correo = (EditText) findViewById(R.id.editText3);
		  nombre = (EditText) findViewById(R.id.editText1);
		 contrasena = (EditText) findViewById(R.id.editText2);

		// Create button
		Button btnCreateUser = (Button) findViewById(R.id.button1);

		// button click event
		btnCreateUser.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// creating new product in background thread
				new CreateNewUser().execute();
			}
		});}
	

class CreateNewUser extends AsyncTask<String, String, String> {
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		pDialog = new ProgressDialog(Activity6.this);
		pDialog.setMessage("Creando registro....");
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(true);
		pDialog.show();
	
}
	/**
	 * Before starting background thread Show Progress Dialog
	 * */
	protected String doInBackground(String... args) {
	    
		String correo1 = correo.getText().toString();
		String nombre1 = nombre.getText().toString();
		String contrasena1 = contrasena.getText().toString();

		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("correo", correo1));
		params.add(new BasicNameValuePair("nombre", nombre1));
		params.add(new BasicNameValuePair("contrasena", contrasena1));

		// getting JSON Object
		// Note that create product url accepts POST method
		JSONObject json = jsonParser.makeHttpRequest(url_create_user,
				"POST", params);
		
		// check log cat fro response
		Log.d("Create Response", json.toString());

		// check for success tag
		try {
			int success = json.getInt(TAG_SUCCESS);

			if (success == 1) {
				// successfully created product
				Intent i = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(i);
				
				// closing this screen
				finish();
			} else {
				// failed to create product
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}
/**
 * After completing background task Dismiss the progress dialog
 * **/
protected void onPostExecute(String file_url) {
	// dismiss the dialog once done
	pDialog.dismiss();
}
}
}
