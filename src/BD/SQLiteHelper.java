package BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
 
public class SQLiteHelper extends SQLiteOpenHelper {
 
	
	private static SQLiteHelper mOpenHelper = null;
	private static final String DATABASE_NAME = "BASE";
	private static final int DATABASE_VERSION = 1;
    private final String sqlCreate1 = "CREATE TABLE agenda (titulo TEXT,descripcion TEXT, fecha TEXT ,hora TEXT)";
    private final String sqlCreate2 = "CREATE TABLE usuario (nombre TEXT,correo TEXT, contrasena TEXT )";

    	
    	public static SQLiteHelper getInstance(Context context){
    		if (mOpenHelper == null){
    			mOpenHelper = new SQLiteHelper(context.getApplicationContext());
    		}
    		
    		return mOpenHelper;
    	}
    	
    	private SQLiteHelper(Context context) {
    		super(context, DATABASE_NAME, null, DATABASE_VERSION);
    		// TODO Auto-generated constructor stub
    	}

    	@Override
    	public void onCreate(SQLiteDatabase db) {
    		// TODO Auto-generated method stub
    		
    		db.execSQL(sqlCreate1);
    	
    		db.execSQL(sqlCreate2);
    	
    	}

    	@Override
    	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    		// TODO Auto-generated method stub
    		db.execSQL("delete table if exists agenda");
    		
    	
    		onCreate(db);
    	}

    }

