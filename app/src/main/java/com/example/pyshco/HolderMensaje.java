package com.example.pyshco;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;

public class HolderMensaje extends RecyclerView.ViewHolder {
    private TextView nombre;
    private TextView mensaje;
    private TextView hora;
    private CircleImageView fotomensajePerfil;
    private ImageView fotoMensaje;
    public HolderMensaje( View itemView) {
        super(itemView);
        nombre= (TextView) itemView.findViewById(R.id.nombreMensaje);
        mensaje= (TextView) itemView.findViewById(R.id.mensajeMensaje);
        hora= (TextView) itemView.findViewById(R.id.horaMensaje);
        fotomensajePerfil = (CircleImageView) itemView.findViewById(R.id.fotoPerfilMensaje);
        fotoMensaje =(ImageView) itemView.findViewById(R.id.mensajeFoto);


    }

    public CircleImageView getFotomensajePerfil() {
        return fotomensajePerfil;
    }

    public void setFotomensajePerfil(CircleImageView fotomensajePerfil) {
        this.fotomensajePerfil = fotomensajePerfil;
    }

    public TextView getHora() {
        return hora;
    }

    public void setHora(TextView hora) {
        this.hora = hora;
    }

    public TextView getMensaje() {
        return mensaje;
    }

    public void setMensaje(TextView mensaje) {
        this.mensaje = mensaje;
    }

    public TextView getNombre() {
        return nombre;
    }

    public void setNombre(TextView nombre) {
        this.nombre = nombre;
    }

    public ImageView getFotoMensaje() {
        return fotoMensaje;
    }

    public void setFotoMensaje(ImageView fotoMensaje) {
        this.fotoMensaje = fotoMensaje;
    }
}
