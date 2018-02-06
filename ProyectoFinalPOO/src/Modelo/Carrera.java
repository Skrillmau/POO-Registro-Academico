/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author mate_
 */
public class Carrera {

    private String Codigo = "";
    private String Carrera = "";
    private boolean Acreditada = false;
    private boolean Presencial = false;
    private boolean Online = false;

    Carrera(String Codigo, String Nombre, boolean AC, boolean P, boolean O) {
        this.Codigo = Codigo;
        this.Carrera = Nombre;
        this.Acreditada = AC;
        this.Presencial = P;
        this.Online = O;
    }

    Carrera() {
        
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getCarrera() {
        return Carrera;
    }

    public void setCarrera(String Carrera) {
        this.Carrera = Carrera;
    }

    public boolean isAcreditada() {
        return Acreditada;
    }

    public void setAcreditada(boolean Acreditada) {
        this.Acreditada = Acreditada;
    }

    public boolean isPresencial() {
        return Presencial;
    }

    public void setPresencial(boolean Presencial) {
        this.Presencial = Presencial;
    }

    public boolean isOnline() {
        return Online;
    }

    public void setOnline(boolean Online) {
        this.Online = Online;
    }

    @Override
    public String toString() {
        return Codigo + "," + Carrera + "," + Acreditada + "," + Presencial + "," + Online;
    }

}
