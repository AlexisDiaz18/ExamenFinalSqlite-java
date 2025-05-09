package com.example.examenfinalsqlite_java;

public class Alumnos {

    private int id;
    private String nombre;
    private String usuario;
    private String ip;
    private String img;

    public Alumnos(int id, String nombre, String usuario, String ip, String img){
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.ip = ip;
        this.img = img;
    }

    public int getId(){ return id;}
    public String getNombre(){ return  nombre;}
    public String getUsuario(){ return  usuario;}
    public String getIp(){ return  ip;}
    public String getImg(){ return img;}

}
