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
public class Profesor {
    private String Nombre;
    private String Identificacion;
    private String Apellido;
    private String Programa;
    private String Foto;

    Profesor(String ID, String Nombre, String Apellido, String Carrera, String Foto) {
        this.Identificacion=ID;
        this.Nombre=Nombre;
        this.Apellido=Apellido;
        this.Foto=Foto;
        this.Programa=Carrera;
    }

    Profesor() {
        
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getIdentificacion() {
        return Identificacion;
    }

    public void setIdentificacion(String Identificacion) {
        this.Identificacion = Identificacion;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getPrograma() {
        return Programa;
    }

    public void setPrograma(String Programa) {
        this.Programa = Programa;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String Foto) {
        this.Foto = Foto;
    }

    @Override
    public String toString() {
        return Identificacion+","+Nombre + "," + Apellido + "," + Programa + "," + Foto;
    }
    
}
