package BD;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
	

		
	public void EliminarEvento(String t, String d, String f, String h){
		 DB.execSQL("DELETE FROM agenda WHERE titulo='"+t+"' AND  descripcion='"+d+"' AND  fecha='"+f+"' AND  hora='"+h+"' ");
	}

 //*********************************************

	public void EditarEvento(String t, String d, String f, String h,String t1, String d1, String f1, String h1){
		 DB.execSQL("UPDATE agenda  SET titulo='"+t+"',descripcion='"+d+"',fecha='"+f+"',hora='"+h+"' WHERE titulo='"+t1+"' AND descripcion='"+d1+"' AND fecha='"+f1+"' AND hora='"+h1+"'");

    		
	}
	


	
	
	@SuppressWarnings("unchecked")
	public Evento[] ListaEventos2(){
    	
		Cursor cursor = DB.rawQuery("SELECT titulo, descripcion, fecha, hora FROM agenda ORDER BY fecha, hora ", null);
	    
		List<HashMap<Evento, String>> lista=new ArrayList<HashMap<Evento,String>>();

		HashMap<String ,String>  temp = new HashMap<String,String>();
		if(cursor.moveToFirst())
		{
			do{

          temp.put( cursor.getString(0), "titulo");
          temp.put(cursor.getString(2), "fecha");
          temp.put(cursor.getString(3), "hora");
          lista.addAll((Collection<? extends HashMap<Evento, String>>) temp);
		
	
	
	}while(cursor.moveToNext());
}
		Evento evento[]=new Evento[lista.size()];
		for(int i=0;i<lista.size();i++)
		{
			
			evento[i]=(Evento) lista.listIterator(i);
			
		}
		return evento;
    	
    }

	
	public Evento[] ListaEventos(){
    	
		Cursor cursor = DB.rawQuery("SELECT titulo, descripcion, fecha, hora FROM agenda ORDER BY fecha, hora ", null);
	    
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
    



