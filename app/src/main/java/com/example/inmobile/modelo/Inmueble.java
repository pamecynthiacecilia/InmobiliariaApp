package com.example.inmobile.modelo;

import java.io.Serializable;
import java.util.Objects;

public class Inmueble implements Serializable {

    private int id;
    private String direccion;
    private String uso;
    private String tipo;
    private int cantAmbientes;
    private double precio;
    private Propietario propietarioInmueble;

    public int getDisponible() {
        return disponible;
    }

    public void setDisponible(int disponible) {
        this.disponible = disponible;
    }

    private int disponible;
    private String imagen;

    public Inmueble(int id, String direccion, String uso, String tipo, int cantAmbientes, double precio, Propietario propietarioInmuebled, int disponible, String imagen) {
        this.id = id;
        this.direccion = direccion;
        this.uso = uso;
        this.tipo = tipo;
        this.cantAmbientes = cantAmbientes;
        this.precio = precio;
        this.propietarioInmueble = propietarioInmueble;
        this.disponible = disponible;
        this.imagen = imagen;
    }
    public Inmueble() {

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantAmbientes() {
        return cantAmbientes;
    }

    public void setAmbientes(int cantAmbientes) {
        this.cantAmbientes = cantAmbientes;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Propietario getpropietarioInmueble() {
        return propietarioInmueble;
    }

    public void setpropietarioInmuebled(Propietario propietarioInmueble) {
        this.propietarioInmueble = propietarioInmueble;
    }

   /* public boolean isEstado() {
        return disponible;
    }

    public void setEstado(boolean disponible) {
        this.disponible = disponible;
    }*/

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inmueble inmueble = (Inmueble) o;
        return id == inmueble.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
