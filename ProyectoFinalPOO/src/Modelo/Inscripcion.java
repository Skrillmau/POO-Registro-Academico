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
public class Inscripcion {
    private String CodigoAsignatura;
    private String CodigoProfesor;
    private String CodigoEstudiante;

    Inscripcion(String CodProf, String CodEstud, String CodAsig) {
        this.CodigoAsignatura=CodAsig;
        this.CodigoEstudiante=CodEstud;
        this.CodigoProfesor = CodProf;
    }

    Inscripcion() {
        
    }

    public String getCodigoAsignatura() {
        return CodigoAsignatura;
    }

    public void setCodigoAsignatura(String CodigoAsignatura) {
        this.CodigoAsignatura = CodigoAsignatura;
    }

    public String getCodigoProfesor() {
        return CodigoProfesor;
    }

    public void setCodigoProfesor(String CodigoProfesor) {
        this.CodigoProfesor = CodigoProfesor;
    }

    public String getCodigoEstudiante() {
        return CodigoEstudiante;
    }

    public void setCodigoEstudiante(String CodigoEstudiante) {
        this.CodigoEstudiante = CodigoEstudiante;
    }

    @Override
    public String toString() {
        return CodigoAsignatura+","+ CodigoProfesor+","+CodigoEstudiante;
    }
            
}
