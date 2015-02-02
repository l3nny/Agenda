package com.example.appagenda;
import BD.AndroidBaseDatos;
import BD.Evento;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class Activity1 extends ActionBarActivity {
	 ListView lista;
	 ArrayAdapter<Evento> adaptador;
	 private AndroidBaseDatos base;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity1);
		base = new BD.AndroidBaseDatos(this);
		base.open();
		Evento[] event=(Evento[]) base.ListaEventos();
		
		 
		lista = (ListView) findViewById(R.id.listView1);
		adaptador = new ArrayAdapter<Evento>(this,android.R.layout.simple_list_item_1,event);
		lista.setAdapter(adaptador);
		
	
		lista.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				  Intent intent = new Intent(view.getContext(), Activity3.class);
				  Evento atributo = (Evento) lista.getItemAtPosition(position);
				    intent.putExtra("titulo", atributo.getTitulo());
				    intent.putExtra("descripcion", atributo.getDescripcion());
				    intent.putExtra("fecha", atributo.getFecha());
				    intent.putExtra("hora", atributo.getHora());
				     startActivity(intent);
				
			}
			});

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.main, menu);
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

