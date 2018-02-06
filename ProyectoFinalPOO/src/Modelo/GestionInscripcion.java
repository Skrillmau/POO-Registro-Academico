/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.ControladorPrincipal;
import Vista.InscripcionMaterias;
import Vista.PantallaEstudiante;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author mate_
 */
public class GestionInscripcion {

    boolean Existe = true;
    ArrayList<Inscripcion> ArrayInscripciones = new ArrayList<>();
    Inscripcion Ins;

    public void AgregarInscripcion(InscripcionMaterias IM, ControladorPrincipal CP, GestionProfesores GP, GestionAsignaturas GA) {
        String CodProf;
        String CodEstud = "";
        String CodAsig;
        if (IM.getCboAsignaturas().getSelectedItem().equals("---Seleccione una Asignatura---")) {
            JOptionPane.showMessageDialog(null, "Seleccione una Asignatura", "Error", 0);
        }
        if (IM.getCboProfes().getSelectedItem().equals("---Seleccione un Profesor---")) {
            JOptionPane.showMessageDialog(null, "Seleccione un Profesor", "Error", 0);
        } else {
            //Recuperar Datos de las casillas de texto
            CodEstud = IM.getTxtCodigo().getText();
            CodProf = CP.GetCodProfe((String) IM.getCboProfes().getSelectedItem());
            CodAsig = CP.GetCodMate((String) IM.getCboAsignaturas().getSelectedItem());

            this.Existe = true;
            //Crear el objeto
            Ins = new Inscripcion(CodProf, CodEstud, CodAsig);
            GuardarInscripcion(Ins);
            JOptionPane.showMessageDialog(null, "La operación se hizo exitosamente..!!");
        }
        Tabla(IM, CodEstud, GP, GA);

    }

    public void EliminarInscripcion(InscripcionMaterias IM, ControladorPrincipal CP, GestionProfesores GP, GestionAsignaturas GA) {
        Inscripcion Ins;
        String CodProf;
        String CodEstud = "";
        String CodAsig;
        boolean A = false;
        int Elim = 0;
        if (IM.getCboAsignaturas().getSelectedItem().equals("---Seleccione una Asignatura---")) {
            JOptionPane.showMessageDialog(null, "Seleccione una Asignatura", "Error", 0);
        }
        if (IM.getCboProfes().getSelectedItem().equals("---Seleccione un Profesor---")) {
            JOptionPane.showMessageDialog(null, "Seleccione un Profesor", "Error", 0);
        } else {
            //Recuperar Datos de las casillas de texto
            CodEstud = IM.getTxtCodigo().getText();
            CodProf = CP.GetCodProfe((String) IM.getCboProfes().getSelectedItem());
            CodAsig = CP.GetCodMate((String) IM.getCboAsignaturas().getSelectedItem());
            for (int a = 0; a < ArrayInscripciones.size(); a++) {
                Ins = ArrayInscripciones.get(a);
                if (Ins.getCodigoAsignatura().equalsIgnoreCase(CodAsig)
                        && Ins.getCodigoEstudiante().equalsIgnoreCase(CodEstud)
                        && Ins.getCodigoProfesor().equalsIgnoreCase(CodProf)) {
                    Elim = a;
                    A = true;
                }
            }
            if (A == true) {
                ArrayInscripciones.remove(Elim);
                JOptionPane.showMessageDialog(null, "Se ha eliminado correctamente");
                Tabla(IM, CodEstud, GP, GA);
            }
            if (A == false) {
                JOptionPane.showMessageDialog(null, "Usted no tiene esa materia registrada", "Error", 0);

            }

        }
    }

    private String GetNombProf(String Cod, GestionProfesores GP) {
        String Nom = "";
        Profesor Prof;
        for (int a = 0; a < GP.getArrayProfesores().size(); a++) {
            Prof = GP.getArrayProfesores().get(a);
            if (Cod.equalsIgnoreCase(Prof.getIdentificacion())) {
                Nom = Prof.getNombre();
                break;
            }

        }
        return Nom;
    }

    private String GetNombAsig(String Cod, GestionAsignaturas GA) {
        String Nom = "";
        Asignatura Asig;
        for (int a = 0; a < GA.getArrayAsignaturas().size(); a++) {
            Asig = GA.getArrayAsignaturas().get(a);
            if (Cod.equalsIgnoreCase(Asig.getCodigo())) {
                Nom = Asig.getNombre();
                break;
            }

        }
        return Nom;
    }

    public ArrayList<Inscripcion> getArrayInscripciones() {
        return ArrayInscripciones;
    }

    public void setArrayInscripciones(ArrayList<Inscripcion> ArrayInscripciones) {
        this.ArrayInscripciones = ArrayInscripciones;
    }

    public boolean Login(InscripcionMaterias IM, GestionEstudiantes GE) {
        String Codigo = IM.getTxtCodigo().getText();
        String Contraseña = Contraseña(IM);
        Estudiante Stud;
        boolean Ok = false;

        for (int a = 0; a < GE.getArrayEstudiantes().size(); a++) {
            Stud = GE.getArrayEstudiantes().get(a);
            if (Stud.getCodigo().equalsIgnoreCase(Codigo)) {
                if (Stud.getContraseña().equalsIgnoreCase(Contraseña)) {
                    Ok = true;
                }
            }
        }
        return Ok;
    }

    private String Contraseña(InscripcionMaterias IM) {
        char[] Pass = IM.getTxtPass().getPassword();
        String Password = new String(Pass);
        return Password;
    }

    private void GuardarInscripcion(Inscripcion Ins) {
        ArrayInscripciones.add(Ins);
    }

    public void Tabla(InscripcionMaterias IM, String CodEstud, GestionProfesores GP, GestionAsignaturas GA) {
        int FilaActual = 0;
        Inscripcion Ins;

        DefaultTableModel modelo = (DefaultTableModel) IM.getTblInsc().getModel();
        TableRowSorter modeloSorter = new TableRowSorter(modelo);
        IM.setRowSorter(modeloSorter);
        for (int a = 0; a < ArrayInscripciones.size(); a++) {
            Ins = ArrayInscripciones.get(a);
            if (Ins.getCodigoEstudiante().equalsIgnoreCase(CodEstud)) {
                if (FilaActual >= IM.getTblInsc().getModel().getRowCount()) {
                    modelo.addRow(new Object[]{"", ""});
                }
                IM.getTblInsc().setValueAt(GetNombProf(Ins.getCodigoProfesor(), GP), FilaActual, 0);
                IM.getTblInsc().setValueAt(GetNombAsig(Ins.getCodigoAsignatura(), GA), FilaActual, 1);
                FilaActual++;
            }
        }
    }
}
