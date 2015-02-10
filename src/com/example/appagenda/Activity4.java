package com.example.appagenda;

import java.util.Calendar;

import BD.AndroidBaseDatos;
import android.support.v7.app.ActionBarActivity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class Activity4 extends ActionBarActivity {

	private AndroidBaseDatos base;
	private DatePicker date_picker;
	static final int DATE_DIALOG_ID = 0;
	static final int TIME_DIALOG_ID = 1;

	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity4);
		
		base = new BD.AndroidBaseDatos(this);
		base.open();
		
		final EditText t = (EditText) findViewById(R.id.editText1);
		final EditText d = (EditText) findViewById(R.id.editText2);
		final EditText f = (EditText) findViewById(R.id.editText3);
		final EditText h = (EditText) findViewById(R.id.editText4);
		
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
		
		setCurrentDate();
		EditTextListener();
		EditTextListener2();

		final Calendar cal = Calendar.getInstance();
		hour = cal.get(Calendar.HOUR_OF_DAY);
		minute = cal.get(Calendar.MINUTE);

		/** Display the current time in the TextView */
		updateDisplay();

		base = new BD.AndroidBaseDatos(this);
		base.open();
		Button agendarcita = (Button) findViewById(R.id.button1);

		agendarcita.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {

				base.EditarEvento(t.getText().toString(), d.getText()
						.toString(), f.getText().toString(), h.getText()
						.toString(), text.getText().toString(), 
						text2.getText().toString(),text3.getText().toString(),text4.getText().toString());

				Toast toast = Toast.makeText(getApplicationContext(),
						"Se edito su cita", Toast.LENGTH_SHORT);
				toast.show();

				Intent intent = new Intent(view.getContext(),
						MainActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	protected Dialog onCreateDialog(int id) {

		switch (id) {
		case DATE_DIALOG_ID:

			return new DatePickerDialog(this, datePickerListener, year, month,
					day);

		case TIME_DIALOG_ID:

			return new TimePickerDialog(this, mTimeSetListener, hour, minute,
					false);

		}
		return null;
	}

	/*-------------------------DATE PICKER----------------------------------------*/
	public void setCurrentDate() {
		final EditText f = (EditText) findViewById(R.id.editText3);

		date_picker = (DatePicker) findViewById(R.id.datePicker1);

		final Calendar calendar = Calendar.getInstance();

		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH);
		day = calendar.get(Calendar.DAY_OF_MONTH);

		f.setText(new StringBuilder()
				// Month is 0 based, so you have to add 1
				.append(month + 1).append("/").append(day).append("/")
				.append(year).append(" "));

		// set current date into Date Picker
		date_picker.init(year, month, day, null);

	}

	public void EditTextListener() {

		final EditText f = (EditText) findViewById(R.id.editText3);

		f.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {

				showDialog(DATE_DIALOG_ID);
			}
		});
	}

	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;
			final EditText f = (EditText) findViewById(R.id.editText3);

			// set selected date into Text View
			f.setText(new StringBuilder().append(month + 1).append("/")
					.append(day).append("/").append(year).append(" "));

			// set selected date into Date Picker
			date_picker.init(year, month, day, null);

		}
	};
	/*-------------------------TIME	 PICKER----------------------------------------*/
	
	/** Callback received when the user "picks" a time in the dialog */
	private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hourOfDay, int minute1) {
			hour = hourOfDay;
			minute = minute1;
			updateDisplay();

		}
	};

	/** Updates the time in the TextView */
	private void updateDisplay() {
		final EditText h = (EditText) findViewById(R.id.editText4);
		h.setText(new StringBuilder().append(pad(hour)).append(":")
				.append(pad(minute)));
	}

	/** Add padding to numbers less than ten */
	private static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}

	public void EditTextListener2() {

		final EditText h = (EditText) findViewById(R.id.editText4);

		h.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {

				showDialog(TIME_DIALOG_ID);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity4, menu);
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
