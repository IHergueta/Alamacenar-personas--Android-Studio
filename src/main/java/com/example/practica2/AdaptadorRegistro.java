package com.example.practica2;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorRegistro extends BaseAdapter {

    private Context context; // Contexto de la aplicacion
    private ArrayList<DatosRegistro> listaRegistros; // Lista de objetos DatosRegistro
    private LayoutInflater inflater; // Inflador del contexto de la aplicacion

    // Constructor

    public AdaptadorRegistro(Activity context, ArrayList<DatosRegistro> listaRegistros){
        this.context = context;
        this.listaRegistros = listaRegistros;
        this.inflater = LayoutInflater.from(context);
    }

    // Se le llama cada en la construcción del ListView
    // Va elemento a elemento hasta rellenar el ListView con el ArrayList

    // Buscar método en la API correspondiente: parámetros de entrada y de salida

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

    // Rellenar
        if (convertView == null) {

            //mete el elemento creado en listview_layout en todos los elementeos dde la listvew
            convertView = inflater.inflate(R.layout.listview_layout,null);

        }

            //busco el convertview de los textos de la listview
            TextView txt1 = convertView.findViewById(R.id.listview_txt_1);
            TextView txt2 = convertView.findViewById(R.id.listview_txt_2);
            TextView txt3 = convertView.findViewById(R.id.listview_txt_3);
            TextView txt4 = convertView.findViewById(R.id.listview_txt_4);
            TextView txt5 = convertView.findViewById(R.id.listview_txt_5);
            TextView txt6 = convertView.findViewById(R.id.listview_txt_6);

            //inicializo el datosregistro con el elemetno selecionado de la listaregistros
            DatosRegistro datosRegistro = listaRegistros.get(position);

            //pongo en el convertview de cada elemento sus datos
            txt1.setText(String.valueOf(datosRegistro.getId()));
            txt2.setText(datosRegistro.getNombre());
            txt3.setText(datosRegistro.getDni());
            txt4.setText(datosRegistro.getCorreo());
            txt5.setText(datosRegistro.getNacionalidad());
            txt6.setText(datosRegistro.getBoletin());



        return convertView;
    }



    @Override
    public int getCount() {
        return listaRegistros.size();
    }

    @Override
    public Object getItem(int position) {
        return listaRegistros.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



}
