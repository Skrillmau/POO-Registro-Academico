/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mate_
 */
public class PersistenciaInscripciones {
    private String Modo;
    private String NombreArchivo = "Inscripciones";
    BufferedWriter Bw;
    BufferedReader Br;
   
    
    public ArrayList <Inscripcion>  CargarTodo()
  {
     ArrayList <Inscripcion> ArrayInscripciones = new  ArrayList <>();
     Inscripcion Ins=null;
     if (abrirConexion("Lectura"))
     {
         while ( (Ins=LeerInscripcion() ) != null)
         {
             ArrayInscripciones.add(Ins);
         }
         cerrar();
     }
      return ArrayInscripciones;  
  }
    public void GuardarTodo(ArrayList <Inscripcion> ArrayI)
  {
      if ( abrirConexion("Escritura") )
      {
          for (int i=0; i < ArrayI.size(); i++)
          {
              GrabarInscripcion(ArrayI.get(i));
          }
          cerrar();
      }
  }

    public void GrabarInscripcion(Inscripcion Ins) {
        try {
            Bw.write(Ins.toString());
            Bw.newLine();
        } catch (IOException Error) {
            System.out.println("Error Guardando la Asignacion");
        }
    }
    
    public Inscripcion LeerInscripcion()
    {   String Texto="";
        Inscripcion Ins = new Inscripcion();
        try {   Texto = Br.readLine();
            if (Texto != null)
            {
                String []Info = Texto.split(",");
                Ins.setCodigoAsignatura(Info[0]);
                Ins.setCodigoProfesor(Info[1]);
                Ins.setCodigoEstudiante(Info[2]);

            }
            else {  Ins = null;  }
                
        } catch(IOException error)
        { 
          Ins = null;  
          System.out.println("Error de lectura");
        }
        return Ins;
    }

    public boolean abrirConexion(String Forma) {
        this.Modo = Forma;
        boolean Ok = false;
        if (Forma.equalsIgnoreCase("Escritura")) {
            try {
                FileWriter Fw
                        = new FileWriter(NombreArchivo, false);
                Bw = new BufferedWriter(Fw);
                Ok = true;

            } catch (IOException ex) {
                Ok = false;
                ex.printStackTrace();
                System.out.println("Error abriendo conexion en modo Escritura");
            }

        }
        if (Forma.equalsIgnoreCase("Lectura")) {
            try {
                FileReader fr = new FileReader(NombreArchivo);
                Br = new BufferedReader(fr);
                Ok = true;
            } catch (IOException ex) {
                Ok = false;
                ex.printStackTrace();
                System.out.println("Error abriendo conexion en modo Lectura");
            }

        }
        return Ok;
    }

    public void cerrar() {
        try {
            if (this.Modo.equalsIgnoreCase("Escritura")) {
                Bw.close();
            }
            if (this.Modo.equalsIgnoreCase("Lectura")) {
                Br.close();
            }
        } catch (IOException variable) {
            System.out.println("Error cerrando archivo");
        }
    }
}

