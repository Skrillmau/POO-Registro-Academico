/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.ControladorPrincipal;
import Vista.PantallaEstudiante;
import Vista.PantallaProfesores;
import Vista.VerTodosEst;
import Vista.VerTodosProf;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author mate_
 */
public class GestionProfesores {

    boolean Existe = true;
    ArrayList<Profesor> ArrayProfesores = new ArrayList<>();
    Profesor Prof;

    public void CapturarDatos(PantallaProfesores PP) {
        String Nombre = "";
        String ID = "";
        String Apellido = "";
        String Carrera = "";
        String Foto = "";

        //Comprobar campos de ingreso de datos
        if (PP.getTxtID().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese una identificacion valido", "ERROR", 0);
            PP.getTxtID().requestFocus();
            //PP.getTbtnNuevo().setText("Guardar");
        }
        if (PP.getTxtNombres().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese un nombre en la casilla correspondiente", "ERROR", 0);
            PP.getTxtNombres().requestFocus();
            //PP.getTbtnNuevo().setText("Guardar");
        }
        if (PP.getTxtApellidos().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese un nombre en la casilla correspondiente", "ERROR", 0);
            PP.getTxtApellidos().requestFocus();
            //PP.getTbtnNuevo().setText("Guardar");
        } else {
            do {
                ID = PP.getTxtID().getText();
                this.Existe = ComprobarIdentificacion(ID);

                if (this.Existe == true) {
                    //Verificar si el codigo ya esta en uso
                    JOptionPane.showMessageDialog(null, "Identificacion ya registrada, ingrese una nuevo", "ERROR", 0);
                    System.out.println(ID);
                    PP.getTxtID().requestFocus();

                } else {
                    //Recuperar Datos de las casillas de texto
                    Nombre = PP.getTxtNombres().getText();
                    Apellido = PP.getTxtApellidos().getText();
                    Carrera = (String) PP.getCboCarreras().getSelectedItem();
                    Foto = PP.foto;

                    this.Existe = true;
                    //Crear el objeto
                    Prof = new Profesor(ID, Nombre, Apellido, Carrera, Foto);
                    GuardarProfesor(Prof);
                    JOptionPane.showMessageDialog(null, "La operación se hizo exitosamente..!!");
                }
            } while (this.Existe == false);
        }
    }

    public ArrayList<Profesor> getArrayProfesores() {
        return ArrayProfesores;
    }

    public void setArrayProfesores(ArrayList<Profesor> ArrayProfesores) {
        this.ArrayProfesores = ArrayProfesores;
    }

    private boolean ComprobarIdentificacion(String ID) {
        Profesor Prof;
        boolean Existe = false;
        for (int a = 0; a < ArrayProfesores.size(); a++) {
            Prof = ArrayProfesores.get(a);
            if (Prof.getIdentificacion().equals(ID)) {
                Existe = true;
            }
        }
        return Existe;
    }

    private void GuardarProfesor(Profesor Prof) {
        ArrayProfesores.add(Prof);
    }

    public void BuscarProfesor(PantallaProfesores PPro) {
        String ID;
        Profesor Prof;
        Boolean A = false;

        ID = JOptionPane.showInputDialog("Digite el código: ");
        for (int a = 0; a < ArrayProfesores.size(); a++) {
            Prof = ArrayProfesores.get(a);
            if (Prof.getIdentificacion().equalsIgnoreCase(ID)) {
                A = true;
                PPro.getTxtID().setText(Prof.getIdentificacion());
                PPro.getTxtNombres().setText(Prof.getNombre());
                PPro.getTxtApellidos().setText(Prof.getApellido());
                PPro.getCboCarreras().setSelectedItem(Prof.getPrograma());
                PPro.PoneFoto(Prof.getFoto());
            }

        }
        if (A == false) {
            JOptionPane.showMessageDialog(null, "Esa identificacion no coincide con ningun Profesor actualmente registrado", "Error", 0);

        }
    }

    public void ModificarProfesor(PantallaProfesores PPro, ControladorPrincipal CP) {
        String ID;
        Profesor Prof = null;
        Boolean A = false;
        int I = 0;

        ID = JOptionPane.showInputDialog("Digite la identificacion: ");
        for (int a = 0; a < ArrayProfesores.size(); a++) {
            Prof = ArrayProfesores.get(a);
            if (Prof.getIdentificacion().equalsIgnoreCase(ID)) {
                A = true;
                I = a;
                PPro.getTxtID().setText(Prof.getIdentificacion());
                PPro.getTxtNombres().setText(Prof.getNombre());
                PPro.getTxtApellidos().setText(Prof.getApellido());
                PPro.getCboCarreras().setSelectedItem(Prof.getPrograma());
                PPro.PoneFoto(Prof.getFoto());
            }

        }
        if (A == true) {
            ArrayProfesores.remove(I);
        }
        if (A == false) {
            JOptionPane.showMessageDialog(null, "Esa identificacion no coincide con ningun Profesor actualmente registrado", "Error", 0);
            CP.Desactivador(5);
        }
    }

    public void EliminarProfesor() {
        String ID1 = "";
        int I = 0;
        boolean Encontrado = false;
        Profesor Prof;
        ID1 = JOptionPane.showInputDialog("Digite la identificacion del profesor a eliminar: ");
        for (int a = 0; a < ArrayProfesores.size(); a++) {
            Prof = ArrayProfesores.get(a);
            if (Prof.getIdentificacion().equalsIgnoreCase(ID1)) {
                I = a;
                Encontrado = true;
                break;
            } else {
                Encontrado = false;
            }
        }
        if (Encontrado == true) {
            ArrayProfesores.remove(I);
            JOptionPane.showMessageDialog(null, "Se ha eliminado correctamente");
        }
        if (Encontrado == false) {
            JOptionPane.showMessageDialog(null, "Esa identificacion no coincide con ningun Profesor actualmente registrado", "Error", 0);
        }

    }
    public void VerTodosEst(VerTodosProf VTodosP) {
        int FilaActual = 0;
        Profesor Pro;
        
        DefaultTableModel modelo = (DefaultTableModel) VTodosP.getTblVTodos().getModel();
        TableRowSorter modeloSorter = new TableRowSorter(modelo);
        VTodosP.setRowSorter(modeloSorter);
        for (int a = 0; a < ArrayProfesores.size(); a++) {
            Pro = ArrayProfesores.get(a);
            if (FilaActual >= VTodosP.getTblVTodos().getRowCount()) {
                modelo.addRow(new Object[]{"", "", "", ""});
            }
            VTodosP.getTblVTodos().setValueAt(Pro.getIdentificacion(), FilaActual, 0);
            VTodosP.getTblVTodos().setValueAt(Pro.getNombre(), FilaActual, 1);
            VTodosP.getTblVTodos().setValueAt(Pro.getApellido(), FilaActual, 2);
            VTodosP.getTblVTodos().setValueAt(Pro.getPrograma(), FilaActual, 3);
            
            FilaActual++;
        }

    }

}
