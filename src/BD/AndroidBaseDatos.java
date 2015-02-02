package BD;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

 
public class AndroidBaseDatos 
{
	
	
	private SQLiteDatabase DB;
	private SQLiteHelper dbHelper;
	
	
public AndroidBaseDatos(Context context) {
		dbHelper = SQLiteHelper.getInstance(context);
	}

	public void open() {
		DB = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}
	
	public void insertEvento(String t, String d, String f, String h)
	{
	    DB.execSQL("INSERT INTO \"agenda\" (\"titulo\", \"descripcion\", \"fecha\",\"hora\") VALUES ('"+t+"', '"+d+"','"+f+"','"+h+"')");
	    
	}
		
	public void eliminarTodo(){

		DB.execSQL("DELETE FROM \"agenda\"");
	}

 //*********************************************

	
	
	public Evento[] ListaEventos(){
    	
		Cursor cursor = DB.rawQuery("SELECT titulo, descripcion, fecha, hora FROM agenda", null);
	    
		List<Evento> lista=new ArrayList<Evento>();
		if(cursor.moveToFirst())
		{
			do{
		lista.add(new Evento(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
	}while(cursor.moveToNext());
}
		Evento evento[]=new Evento[lista.size()];
		for(int i=0;i<lista.size();i++)
		{
			
			evento[i]=(( Evento) lista.get(i));
			
		}
		return evento;
    	
    }



}
    



