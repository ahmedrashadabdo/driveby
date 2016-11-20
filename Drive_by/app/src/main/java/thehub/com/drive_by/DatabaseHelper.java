package thehub.com.drive_by;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Emalindah on 17/08/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "user.db";
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_MOBILE= "mobile";
    private static final String COLUMN_PASSWORD = "password";
    SQLiteDatabase db;
    private static final String TABLE_CREATE = "create table users " +
            "(id integer primary key not null ," +
            "username text not null, " +
            "email text not null," +
            "mobile integer not null," +
            "password text not null)";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;

    }
     public void insertUser( User u)
     {
         //make database writable(
         db = this.getWritableDatabase();
         ContentValues values = new ContentValues();

         String query = "select * from users";
         Cursor cursor = db.rawQuery(query , null);
         int count = cursor.getCount();

         values.put(COLUMN_ID, count);
         values.put(COLUMN_USERNAME,u.getUsername());
         values.put(COLUMN_EMAIL,u.getEmail() );
         values.put(COLUMN_MOBILE , u.getMobile());
         values.put(COLUMN_PASSWORD, u.getPassword());
         //insert content into the database
         db.insert(TABLE_NAME , null , values);
         db.close();

     }
    public String searchPass(String username)
    {
       db = this.getReadableDatabase();
        String query = "select username, password from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query , null);
        String uname, pass;
        pass = "Not found";
        if (cursor.moveToFirst())
        {
            do{
                uname = cursor.getString(0);

                if(uname.equals(username))
                    pass = cursor.getString(1);
                    break;

            }
            while(cursor.moveToNext());
        }

        return pass;

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS" + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
