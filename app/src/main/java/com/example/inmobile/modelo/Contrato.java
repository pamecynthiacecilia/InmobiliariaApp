package com.example.inmobile.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Contrato implements Serializable {

    private int id;
    private String fechaInicio;
    private String fechaFin;
    private double importe;
    private Inquilino inquilinoContrato;
    private Inmueble inmuebleContrato;

    public Contrato() {}
    public Contrato(int id, String fechaInicio, String fechaFin, double importe, Inquilino inquilinoContrato, Inmueble inmuebleContrato) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.importe = importe;
        this.inquilinoContrato = inquilinoContrato;
        this.inmuebleContrato = inmuebleContrato;
    }


    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getimporte() {
        return importe;
    }

    public void setimporte(float importe) {
        this.importe = importe;
    }


    public Inquilino getinquilinoContrato() {
        return inquilinoContrato;
    }

    public void setinquilinoContrato(Inquilino inquilinoContrato) {
        this.inquilinoContrato = inquilinoContrato;
    }

    public Inmueble getinmuebleContrato() {
        return inmuebleContrato;
    }

    public void setinmuebleContrato(Inmueble inmuebleContrato) {
        this.inmuebleContrato = inmuebleContrato;
    }


}
