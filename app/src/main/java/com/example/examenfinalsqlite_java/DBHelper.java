package com.example.examenfinalsqlite_java;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME ="alumnos.db";
    private static  final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "alumnos";
    private static final String COLUMN_ID= "id";
    private static final String COLUMN_NOMBRE = "nombre";
    private static final String COLUMN_USUARIO = "usuario";
    private static final String COLUMN_IP = "ip";
    private static final String COLUMN_IMG = "img";

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NOMBRE + " TEXT," +
                COLUMN_USUARIO + " TEXT," +
                COLUMN_IP + " TEXT," +
                COLUMN_IMG + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Para actualizaciones de versión
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertarAlumno(String nombre,String usuario, String ip, String img){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE, nombre);
        values.put(COLUMN_USUARIO, usuario);
        values.put(COLUMN_IP, ip);
        values.put(COLUMN_IMG, img);

        long resultado = db.insert(TABLE_NAME, null, values);
        db.close();
        return resultado != -1;
    }

    public List<Alumnos> getAlumnos(){
        List<Alumnos> listaAlumnos = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if(cursor.moveToFirst()){
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String nombre = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRE));
                String usuario = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USUARIO));
                String ip = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IP));
                String img = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMG));
                Alumnos alumno = new Alumnos(id, nombre, usuario, ip,img);
                listaAlumnos.add(alumno);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listaAlumnos;
    }
}
