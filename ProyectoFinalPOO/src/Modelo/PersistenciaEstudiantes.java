
package Modelo;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author mate_
 */
public class PersistenciaEstudiantes {

    private String Modo;
    private String NombreArchivo = "Estudiantes";
    BufferedWriter Bw;
    BufferedReader Br;
    
    
    public ArrayList <Estudiante>  CargarTodo()
  {
     ArrayList <Estudiante> ArrayEstudiantes = new  ArrayList <>();
     Estudiante Est=null;
     if (abrirConexion("Lectura"))
     {
         while ( (Est=leerEstudiante() ) != null)
         {
             ArrayEstudiantes.add(Est);
         }
         cerrar();
     }
      return ArrayEstudiantes;  
  }
    public void GuardarTodo(ArrayList <Estudiante> ArrayE)
  {
      if ( abrirConexion("Escritura") )
      {
          for (int i=0; i < ArrayE.size(); i++)
          {
              GrabarEstudiante(ArrayE.get(i));
          }
          cerrar();
      }
  }

    public void GrabarEstudiante(Estudiante Est) {
        try {
            Bw.write(Est.toString());
            Bw.newLine();
        } catch (IOException Error) {
            System.out.println("Error Guardando la Carrera");
        }
    }
    
    public Estudiante leerEstudiante()
    {   String Texto="";
        Estudiante Est = new Estudiante();
        try {   Texto = Br.readLine();
            if (Texto != null)
            {
                String []Info = Texto.split(",");
                Est.setCodigo(Info[0]);
                Est.setNombre(Info[1]);
                Est.setApellido(Info[2]);
                Est.setTelefono(Info[3]);
                Est.setCarrera(Info[4]);
                Est.setDireccion(Info[5]);
                Est.setCorreo(Info[6]);
                Est.setContraseña(Info[7]);
                Est.setFoto(Info[8]);
            }
            else {  Est = null;  }
                
        } catch(IOException error)
        { 
          Est = null;  
          System.out.println("Error de lectura");
        }
        return Est;
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
