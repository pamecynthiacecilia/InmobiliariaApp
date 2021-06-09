package com.example.inmobile.modelo;

import java.io.Serializable;

public class Inquilino implements Serializable {

    private int id;
    private String dni;
    private String nombre;
    private String apellido;
    private String lugarDeTrabajo;
    private String email;
    private String tel;
    private String nombreGarante;
    private String telGarante;

    public Inquilino() {}

    public Inquilino(int id, String dni, String nombre, String apellido, String lugarDeTrabajo, String email, String tel, String nombreGarante, String telGarante) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.lugarDeTrabajo = lugarDeTrabajo;
        this.email = email;
        this.tel = tel;
        this.nombreGarante = nombreGarante;
        this.telGarante = telGarante;
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public String getdni() {
        return dni;
    }

    public void setdni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getLugarDeTrabajo() {
        return lugarDeTrabajo;
    }

    public void setLugarDeTrabajo(String lugarDeTrabajo) {
        this.lugarDeTrabajo = lugarDeTrabajo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String gettel() {
        return tel;
    }

    public void settel(String tel) {
        this.tel = tel;
    }

    public String getNombreGarante() {
        return nombreGarante;
    }

    public void setNombreGarante(String nombreGarante) {
        this.nombreGarante = nombreGarante;
    }

    public String gettelGarante() {
        return telGarante;
    }

    public void settelGarante(String telGarante) {
        this.telGarante = telGarante;
    }
}
