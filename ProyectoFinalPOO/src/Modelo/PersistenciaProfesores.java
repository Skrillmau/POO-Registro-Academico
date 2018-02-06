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
public class PersistenciaProfesores {
    private String Modo;
    private String NombreArchivo = "Profesores";
    BufferedWriter Bw;
    BufferedReader Br;
    
    
    public ArrayList <Profesor>  CargarTodo()
  {
     ArrayList <Profesor> ArrayProfesores = new  ArrayList <>();
     Profesor Prof=null;
     if (abrirConexion("Lectura"))
     {
         while ( (Prof=LeerProfesor() ) != null)
         {
             ArrayProfesores.add(Prof);
         }
         cerrar();
     }
      return ArrayProfesores;  
  }
    public void GuardarTodo(ArrayList <Profesor> ArrayP)
  {
      if ( abrirConexion("Escritura") )
      {
          for (int i=0; i < ArrayP.size(); i++)
          {
              GrabarProfesor(ArrayP.get(i));
          }
          cerrar();
      }
  }

    public void GrabarProfesor(Profesor Prof) {
        try {
            Bw.write(Prof.toString());
            Bw.newLine();
        } catch (IOException Error) {
            System.out.println("Error Guardando la Carrera");
        }
    }
    
    public Profesor LeerProfesor()
    {   String Texto="";
        Profesor Prof = new Profesor();
        try {   Texto = Br.readLine();
            if (Texto != null)
            {
                String []Info = Texto.split(",");
                Prof.setIdentificacion(Info[0]);
                Prof.setNombre(Info[1]);
                Prof.setApellido(Info[2]);
                Prof.setPrograma(Info[3]);
                Prof.setFoto(Info[4]);

            }
            else {  Prof = null;  }
                
        } catch(IOException error)
        { 
          Prof = null;  
          System.out.println("Error de lectura");
        }
        return Prof;
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
