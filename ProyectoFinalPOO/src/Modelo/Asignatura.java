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
public class Asignatura {
    private String Codigo;
    private String Nombre;
    private String Creditos;

    Asignatura(String Codigo, String Nombre, String Creditos) {
        this.Codigo=Codigo;
        this.Nombre=Nombre;
        this.Creditos=Creditos;
    }

    Asignatura() {
        
    }


    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getCreditos() {
        return Creditos;
    }

    public void setCreditos(String Creditos) {
        this.Creditos = Creditos;
    }

    @Override
    public String toString() {
        return Codigo +","+Nombre+","+ Creditos;
    }
    
}
