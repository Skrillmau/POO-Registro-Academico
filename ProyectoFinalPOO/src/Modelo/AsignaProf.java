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
public class AsignaProf {
    private String CodAsignatura;
    private String CodProfesor;

    AsignaProf(String CDProf, String CDAsing) {
        this.CodAsignatura=CDAsing;
        this.CodProfesor=CDProf;
    }

    AsignaProf() {
        
    }

    public String getCodAsignatura() {
        return CodAsignatura;
    }

    public void setCodAsignatura(String CodAsignatura) {
        this.CodAsignatura = CodAsignatura;
    }

    public String getCodProfesor() {
        return CodProfesor;
    }

    public void setCodProfesor(String CodProfesor) {
        this.CodProfesor = CodProfesor;
    }

    @Override
    public String toString() {
        return CodAsignatura +","+ CodProfesor ;
    }
    
}
