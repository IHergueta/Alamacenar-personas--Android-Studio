package com.example.practica2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class VisualizarDatos extends AppCompatActivity {

    private ListView list;
    private AdaptadorRegistro adaptador;
    private BaseDeDatos datos;
    private SQLiteDatabase db;
    private ArrayList<DatosRegistro> registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_datos);

        //inicializo la base de datos
        datos=new BaseDeDatos(this);

        //busco por id la listview
        list = findViewById(R.id.listview);

        //incializo la array list de registro
        registro = new ArrayList<>();

        //meto la array de los datos de la base de datos  en una variable
        registro= datos.obtenerDatos();

        //inicializo el adaptador
        adaptador = new AdaptadorRegistro(this,registro);

        // para añadir la vista en el caso de que la ListView esté vacía
        list.setEmptyView(findViewById(R.id.txtvacio));
        list.setAdapter(adaptador);

        //creo el objeto setOnItemLongClickListener
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                //busco  el elemto de la list view
                TextView t1 = findViewById(R.id.listview_txt_1);

                //llamo al metodo de eliminiar datos y le meto la id para borrar el elemento en la base de datos
                datos.eliminarDatos(t1.getText().toString());

                //borro de la listview metiendo la posicion
                registro.remove(position);

                //notifico al adaptador de que algo ha sido modificado y lo borre
                adaptador.notifyDataSetChanged();


                Toast.makeText(VisualizarDatos.this, "Eliminado elemento con id: " + registro.get(position).getId(), Toast.LENGTH_LONG).show();

                return false;
            }

        });
        }




        }




