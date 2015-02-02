package com.example.appagenda;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Activity3 extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity3);
		
		TextView text = (TextView) findViewById(R.id.textView1);
		Bundle reicieveParams = getIntent().getExtras();
		text.setText(reicieveParams.getString("titulo"));
		
		TextView text2 = (TextView) findViewById(R.id.textView2);
		Bundle reicieveParams2 = getIntent().getExtras();
		text2.setText(reicieveParams2.getString("descripcion"));
		
		TextView text3 = (TextView) findViewById(R.id.textView3);
		Bundle reicieveParams3 = getIntent().getExtras();
		text3.setText(reicieveParams3.getString("fecha"));
		
		TextView text4 = (TextView) findViewById(R.id.textView4);
		Bundle reicieveParams4 = getIntent().getExtras();
		text4.setText(reicieveParams4.getString("hora"));

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
