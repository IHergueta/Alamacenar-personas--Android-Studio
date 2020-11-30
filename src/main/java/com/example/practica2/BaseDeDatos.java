 package com.example.practica2;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
//importo la clase
import com.example.practica2.Constantes.*;

import java.util.ArrayList;


public class BaseDeDatos extends SQLiteOpenHelper{
    public static final int VERSION = 2;
    //el metodo del constructor padre
    public BaseDeDatos(Context context) {

        super(context, Constantes.BASE_DATOS,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //con la base de datos, hago un consulta
        db.execSQL("CREATE TABLE " + Constantes.TABLA_FORMULARIO + " ("+Constantes._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Constantes.NOMBRE+ " TEXT, "+Constantes.DNI+ " TEXT,"+Constantes.CORREO+ " TEXT,"+Constantes.NACIONALIDAD+ " TEXT,"+Constantes.BOLETIN_NOTICIAS+ " TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //si existe la tabla, la borre y llame la metodo de crearla otra vez
        db.execSQL("drop table if exists " + Constantes.TABLA_FORMULARIO);
        onCreate(db);

    }

    public ArrayList<DatosRegistro> obtenerDatos(){

        //creo una base de datos para poder leerla
        SQLiteDatabase sqlb = getReadableDatabase();

        //creo un cursor para que me haga la consulta
        Cursor cursor = sqlb.query(Constantes.TABLA_FORMULARIO,new String[] {Constantes._ID,Constantes.NOMBRE,Constantes.DNI,Constantes.CORREO,Constantes.NACIONALIDAD,Constantes.BOLETIN_NOTICIAS},null,null,null,null,Constantes._ID);

        //creo la array para meter los registros
        ArrayList<DatosRegistro> registro = new ArrayList<DatosRegistro>();

        //recorro la tabla
        while(cursor.moveToNext()){

            //añado los registros
            registro.add(new DatosRegistro(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)));

        }

        cursor.close();
        sqlb.close();
        return registro;
    }

    public long registrarDatos(DatosRegistro datos){

        //creo una base de datos que puedo escribir
        SQLiteDatabase sqlb = getWritableDatabase();

        //hago un content value donde metos los datos para despues meterlos en el insert
        ContentValues cv = new ContentValues();
        //meto los datos
        //cv.put(Constantes._ID,datos.getId());
        cv.put(Constantes.NOMBRE,datos.getNombre());
        cv.put(Constantes.DNI,datos.getDni());
        cv.put(Constantes.CORREO,datos.getCorreo());
        cv.put(Constantes.NACIONALIDAD,datos.getNacionalidad());
        cv.put(Constantes.BOLETIN_NOTICIAS,datos.getBoletin());

        //consulta insert into
        long devolver = sqlb.insert(Constantes.TABLA_FORMULARIO,null,cv);

        return devolver;
    }

    public void eliminarDatos(String id){

        //hago la base de datos para poder escribirla
        SQLiteDatabase slqb = getWritableDatabase();

        //ejecuto la query de borrar datos
        slqb.delete(Constantes.TABLA_FORMULARIO,Constantes._ID+ " =?",new String[] {id});

    }
}
