package com.example.appagenda;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import BD.JSONParser;
import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnClickListener{

	private ProgressDialog pDialog;

	JSONParser jsonParser = new JSONParser();
	EditText correo ;
	EditText contrasena;

private static String LOGIN_URL = "http://ylla.co/Autenticacion_user.php";

// JSON Node names
private static final String TAG_SUCCESS = "success";
private static final String TAG_MESSAGE = "message";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 correo = (EditText) findViewById(R.id.editText1);
		 contrasena = (EditText) findViewById(R.id.editText2);

		Button login = (Button) findViewById(R.id.button1);
		Button register = (Button) findViewById(R.id.button2);
		
		
	
		login.setOnClickListener(this);

		
		register.setOnClickListener(this);

			
		
	
	}
	  public void onClick(View v) {
	        switch (v.getId()) {
         case R.id.button1:
	                new AttemptLogin().execute();
	            break;
	        case R.id.button2:
	                Intent i = new Intent(this, Activity6.class);
	                startActivity(i);
	            break;
	        default:
               break;
	        }
	    }
	  
	  class AttemptLogin extends AsyncTask<String, String, String> {
	
		          boolean failure = false;
		
		          @Override

      protected void onPreExecute() {

          super.onPreExecute();

          pDialog = new ProgressDialog(MainActivity.this);

         pDialog.setMessage("Comprobando login...");

         pDialog.setIndeterminate(false);

          pDialog.setCancelable(true);
          pDialog.show();

      }



      @Override

      protected String doInBackground(String... args) {

          // TODO Auto-generated method stub

           // Check for success tag

          int success;

          String username = correo.getText().toString();

          String password = contrasena.getText().toString();

          try {

             // Building Parameters

              List<NameValuePair> params = new ArrayList<NameValuePair>();

              params.add(new BasicNameValuePair("correo", username));

              params.add(new BasicNameValuePair("contrasena", password));
		


             Log.d("request!", "starting");

              // getting product details by making HTTP request

             JSONObject json = jsonParser.makeHttpRequest(

                    LOGIN_URL, "POST", params);



              // check your log for json response

              Log.d("Login attempt", json.toString());


              // json success tag

             success = json.getInt(TAG_SUCCESS);

            if (success == 1) {

                  Log.d("Login Successful!", json.toString());

                  Intent i = new Intent(MainActivity.this, Activity5.class);

                  finish();

                  startActivity(i);

                  return json.getString(TAG_MESSAGE);

              }else{

                  Log.d("Login Failure!", json.getString(TAG_MESSAGE));

                  return json.getString(TAG_MESSAGE);



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

          // dismiss the dialog once product deleted

          pDialog.dismiss();

          if (file_url != null){

              Toast.makeText(MainActivity.this, file_url, Toast.LENGTH_LONG).show();

          }


      }



  }



}



