package com.example.examenfinalsqlite_java;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AlumnoAdapter extends RecyclerView.Adapter<AlumnoAdapter.AlumnoViewHolder>{

    private List<Alumnos> listaAlumnos;
    public AlumnoAdapter(List<Alumnos> listaAlumnos){
        this.listaAlumnos = listaAlumnos;
    }

    public static class AlumnoViewHolder extends RecyclerView.ViewHolder{
        TextView textNombre;
        TextView textUsuario;
        TextView textIp;
        ImageView imgView;

        public AlumnoViewHolder(View itemView){
            super(itemView);
            imgView = itemView.findViewById(R.id.imageView);
            textNombre = itemView.findViewById(R.id.txtNombre);
            textUsuario = itemView.findViewById(R.id.txtUsuario);
            textIp = itemView.findViewById(R.id.txtIp);
        }
    }

    @Override
    public AlumnoViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
        return new AlumnoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AlumnoViewHolder holder, int position){
        Alumnos alumno = listaAlumnos.get(position);

        Context context = holder.itemView.getContext();
        String nombreImg = alumno.getImg();
        int resId = context.getResources().getIdentifier(nombreImg, "drawable", context.getPackageName());

        if (resId != 0) {
            holder.imgView.setImageResource(resId);
        } else {
            holder.imgView.setImageResource(R.drawable.img_default);
        }

        holder.textNombre.setText(alumno.getNombre());
        holder.textUsuario.setText(String.format("Maquina: %s",alumno.getUsuario()));
        holder.textIp.setText(String.format("IP: %s", alumno.getIp()));
    }

    @Override
    public int getItemCount() {
        return listaAlumnos.size();
    }

}
