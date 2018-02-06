/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author mate_
 */
public class PersistenciaCarrera {

    private String Modo;
    private String NombreArchivo = "Carreras";
    BufferedWriter Bw;
    BufferedReader Br;
    
    
    public ArrayList <Carrera>  CargarTodo()
  {
     ArrayList <Carrera> ArrayCarrera = new  ArrayList <Carrera>();
     Carrera Car=null;
     if (abrirConexion("Lectura"))
     {
         while ( (Car=leerCarrera() ) != null)
         {
             ArrayCarrera.add(Car);
         }
         cerrar();
     }
      return ArrayCarrera;  
  }
    public void GuardarTodo(ArrayList <Carrera> ArrayC)
  {
      if ( abrirConexion("Escritura") )
      {
          for (int i=0; i < ArrayC.size(); i++)
          {
              GrabarCarrera(ArrayC.get(i));
          }
          cerrar();
      }
  }

    public void GrabarCarrera(Carrera Car) {
        try {
            Bw.write(Car.toString());
            Bw.newLine();
        } catch (IOException Error) {
            System.out.println("Error Guardando la Carrera");
        }
    }
    
    public Carrera leerCarrera()
    {   String Texto="";
        Carrera Car = new Carrera();
        try {   Texto = Br.readLine();
            if (Texto != null)
            {
                String []Info = Texto.split(",");
                Car.setCodigo(Info[0]);
                Car.setCarrera(Info[1]);
                Car.setAcreditada(Boolean.parseBoolean(Info[2]));
                Car.setPresencial(Boolean.parseBoolean(Info[3]));
                Car.setOnline(Boolean.parseBoolean(Info[4]));
                
            }
            else {  Car = null;  }
                
        } catch(IOException error)
        { 
          Car = null;  
          System.out.println("Error de lectura");
        }
        return Car;
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
