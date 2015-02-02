package com.example.appagenda;
import java.util.Calendar;

import BD.AndroidBaseDatos;
import android.support.v7.app.ActionBarActivity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class Activity2 extends ActionBarActivity {
	private AndroidBaseDatos base;
	private DatePicker date_picker;
	private TimePicker time_picker; 
	private Calendar calendar;
	static final int DATE_DIALOG_ID = 100;
 
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity2);
		final EditText t = (EditText) findViewById(R.id.editText1);
		final EditText d = (EditText) findViewById(R.id.editText2);
		final EditText f = (EditText) findViewById(R.id.editText3);
		final EditText h = (EditText) findViewById(R.id.editText4);
		
		setCurrentDate();
		EditTextListener();
		EditTextListener2();

		 calendar = Calendar.getInstance();
		 hour = calendar.get(Calendar.HOUR_OF_DAY);
	     minute = calendar.get(Calendar.MINUTE);
	      showTime(hour, minute);
		
		base = new BD.AndroidBaseDatos(this);
		base.open();
		Button agendarcita = (Button)findViewById(R.id.button1);
		
	
			      
            
        		
                agendarcita.setOnClickListener(new View.OnClickListener() { 
                	
                    public void onClick(View view) {
                     
                    	base.insertEvento(t.getText().toString(),d.getText().toString(),f.getText().toString(),h.getText().toString());
                    	
            	
            	Toast toast = Toast.makeText(getApplicationContext(), "Se agendo su cita", Toast.LENGTH_SHORT);
    			toast.show();
            }});
			}
	
		
	public void setCurrentDate() {
		final EditText f = (EditText) findViewById(R.id.editText3);
		
		date_picker = (DatePicker) findViewById(R.id.datePicker1);
 
		final Calendar calendar = Calendar.getInstance();
		
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH);
		day = calendar.get(Calendar.DAY_OF_MONTH);

		f.setText(new StringBuilder()
			// Month is 0 based, so you have to add 1
			.append(month + 1).append("/")
			.append(day).append("/")
			.append(year).append(" "));
 
		// set current date into Date Picker
		date_picker.init(year, month, day, null);
		
 
	}
	
	
	  public void showTime(int ho, int m) {
		  final EditText h = (EditText) findViewById(R.id.editText4);
		  h.setText(new StringBuilder()
			.append(ho + 1).append(":")
			.append(m).append("")	
					);
	  }
	  
	  public void setTime(View view) {
	      int hour = time_picker.getCurrentHour();
	      int min = time_picker.getCurrentMinute();
	      showTime(hour, min);
	   }
	
	public void EditTextListener() {
		 
		final EditText f = (EditText) findViewById(R.id.editText3);
		
		
		f.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View v) {
 
				showDialog(DATE_DIALOG_ID);
 
			}
		
 
		});
	}
	public void EditTextListener2() {
		final EditText h = (EditText) findViewById(R.id.editText4);
		h.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View v) {
 
				showDialog(DATE_DIALOG_ID);
 
			}
		
 
		});
 
	}
	@Override
	protected Dialog onCreateDialog(int id) {
		
		switch (id) {
		case DATE_DIALOG_ID:
		   // set date picker as current date
		   return new DatePickerDialog(this, datePickerListener, year, month,day);
		}
		return null;
	}
 
	protected Dialog onCreateDialog2(int id) {
		
		switch (id) {
		case DATE_DIALOG_ID:
		   // set date picker as current date
		   return new TimePickerDialog( null, timePickerListener, hour, minute, true);
		}
		return null;
	}
	
	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
		 
		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;
			final EditText f = (EditText) findViewById(R.id.editText3);
 
			// set selected date into Text View
			f.setText(new StringBuilder().append(month + 1)
			   .append("/").append(day).append("/").append(year).append(" "));
 
			// set selected date into Date Picker
			date_picker.init(year, month, day, null);
 
		}
	};
	
	private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
		
		@Override
	
		public void onTimeSet(TimePicker view, int selectedHour,int selectedMinute) {
			hour = selectedHour;
			minute = selectedMinute;
			
			final EditText h = (EditText) findViewById(R.id.editText4);
 
			// set selected date into Text View
			h.setText(new StringBuilder()
			.append(hour + 1).append(":")
			.append(minute).append(""));
			showTime(hour, minute);
			// set selected date into Date Picker
			//time_picker.(hour, minute, null);
 
		}
	};
 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity2, menu);
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
