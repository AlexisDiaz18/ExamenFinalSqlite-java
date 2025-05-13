package com.example.examenfinalsqlite_java;

public class Alumnos {

    private int id;
    private String nombre;
    private String usuarioMaquina;
    private String ip;
    private String img;

    public Alumnos(int id, String nombre, String usuarioMaquina, String ip, String img){
        this.id = id;
        this.nombre = nombre;
        this.usuarioMaquina = usuarioMaquina;
        this.ip = ip;
        this.img = img;
    }

    public int getId(){ return id;}
    public String getNombre(){ return  nombre;}
    public String getUsuario(){ return  usuarioMaquina;}
    public String getIp(){ return  ip;}
    public String getImg(){ return img;}

}
