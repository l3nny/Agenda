package com.example.appagenda;

import BD.AndroidBaseDatos;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class Activity3 extends ActionBarActivity {
	
	private AndroidBaseDatos base;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity3);

		final TextView text = (TextView) findViewById(R.id.textView1);
		Bundle reicieveParams = getIntent().getExtras();
		text.setText(reicieveParams.getString("titulo"));

		final TextView text2 = (TextView) findViewById(R.id.textView2);
		Bundle reicieveParams2 = getIntent().getExtras();
		text2.setText(reicieveParams2.getString("descripcion"));

		final TextView text3 = (TextView) findViewById(R.id.textView3);
		Bundle reicieveParams3 = getIntent().getExtras();
		text3.setText(reicieveParams3.getString("fecha"));

		final TextView text4 = (TextView) findViewById(R.id.textView4);
		Bundle reicieveParams4 = getIntent().getExtras();
		text4.setText(reicieveParams4.getString("hora"));
	

		
		
		ImageButton imageButton1= (ImageButton) findViewById(R.id.button1);
		ImageButton imageButton2= (ImageButton) findViewById(R.id.button2);
		
		imageButton1.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Intent intent = new Intent(view.getContext(), Activity4.class);
				intent.putExtra("titulo",text.getText().toString());
				intent.putExtra("descripcion", text2.getText().toString());
				intent.putExtra("fecha", text3.getText().toString());
				intent.putExtra("hora", text4.getText().toString());
				
				startActivity(intent);
			}
		});
		
		imageButton2.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				
				
				base.EliminarEvento(text.getText().toString(),text2.getText().toString(),text3.getText().toString(), text4.getText().toString());
				Toast toast = Toast.makeText(getApplicationContext(),
						"Se elimino su cita", Toast.LENGTH_SHORT);
				toast.show();
				
				Intent intent = new Intent(view.getContext(), Activity1.class);
				startActivity(intent);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity3, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
