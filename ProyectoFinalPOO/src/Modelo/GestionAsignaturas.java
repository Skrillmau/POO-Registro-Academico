/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.ControladorPrincipal;
import Vista.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author mate_
 */
public class GestionAsignaturas {

    boolean Existe = true;
    ArrayList<Asignatura> ArrayAsignaturas = new ArrayList<>();
    ArrayList<AsignaProf> ArrayAsignaProf = new ArrayList<>();
    Asignatura Asig;

    public void CapturarDatos(PantallaMaterias PM) {
        String Nombre = "";
        String Codigo = "";
        String Creditos = "";
        //Comprobar campos de ingreso de datos
        if (PM.getTxtCodigo().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese un Codigo valido", "ERROR", 0);
            PM.getTxtCodigo().requestFocus();
            //PP.getTbtnNuevo().setText("Guardar");
        }
        if (PM.getTxtNombre().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese un nombre en la casilla correspondiente", "ERROR", 0);
            PM.getTxtNombre().requestFocus();
            //PP.getTbtnNuevo().setText("Guardar");
        }
        if (PM.getTxtCreditos().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese numero de creditos en la casilla correspondiente", "ERROR", 0);
            PM.getTxtCreditos().requestFocus();
            //PP.getTbtnNuevo().setText("Guardar");
        } else {
            do {
                Codigo = PM.getTxtCodigo().getText();
                this.Existe = ComprobarCodigo(Codigo);

                if (this.Existe == true) {
                    //Verificar si el codigo ya esta en uso
                    JOptionPane.showMessageDialog(null, "Identificacion ya registrada, ingrese una nuevo", "ERROR", 0);
                    System.out.println(Codigo);
                    PM.getTxtCodigo().requestFocus();

                } else {
                    //Recuperar Datos de las casillas de texto
                    Nombre = PM.getTxtNombre().getText();
                    Creditos = PM.getTxtCreditos().getText();

                    this.Existe = true;
                    //Crear el objeto
                    Asig = new Asignatura(Codigo, Nombre, Creditos);
                    GuardarAsignaturas(Asig);
                    JOptionPane.showMessageDialog(null, "La operación se hizo exitosamente..!!");
                }
            } while (this.Existe == false);
        }
    }

    private boolean ComprobarCodigo(String Codigo) {
        Asignatura Asig;
        boolean Existe = false;
        for (int a = 0; a < ArrayAsignaturas.size(); a++) {
            Asig = ArrayAsignaturas.get(a);
            if (Asig.getCodigo().equals(Codigo)) {
                Existe = true;
            }
        }
        return Existe;
    }

    private void GuardarAsignaturas(Asignatura Asig) {
        ArrayAsignaturas.add(Asig);
    }

    public void BuscarAsignatura(PantallaMaterias PM, GestionProfesores GP) {
        String Codigo;
        Asignatura Asig;
        Boolean A = false;
        AsignaProf AP;

        Codigo = JOptionPane.showInputDialog("Digite el código: ");
        for (int a = 0; a < ArrayAsignaturas.size(); a++) {
            Asig = ArrayAsignaturas.get(a);
            if (Asig.getCodigo().equalsIgnoreCase(Codigo)) {
                A = true;
                PM.getTxtCodigo().setText(Asig.getCodigo());
                PM.getTxtNombre().setText(Asig.getNombre());
                PM.getTxtCreditos().setText(Asig.getCreditos());
            }
            int C = 0;
            for (int b = 0; b < ArrayAsignaProf.size(); b++) {
                AP = ArrayAsignaProf.get(b);

                if (AP.getCodAsignatura().equalsIgnoreCase(Codigo)) {

                    DefaultTableModel modelo = (DefaultTableModel) PM.getTblProf().getModel();
                    TableRowSorter modeloSorter = new TableRowSorter(modelo);
                    PM.getTblProf().setRowSorter(modeloSorter);
                    if (C >= PM.getTblProf().getRowCount()) {
                        modelo.addRow(new Object[]{"", "", "", ""});
                    }
                    PM.getTblProf().setValueAt(AP.getCodAsignatura(), C, 0);
                    PM.getTblProf().setValueAt((GetNombProf(AP.getCodProfesor(), GP)), C, 1);
                    C++;
                }

            }
        }
        if (A == false) {
            JOptionPane.showMessageDialog(null, "Ese Codigo no coincide con ninguna Asignatura actualmente registrada", "Error", 0);

        }

    }

    public ArrayList<Asignatura> getArrayAsignaturas() {
        return ArrayAsignaturas;
    }

    public void setArrayAsignaturas(ArrayList<Asignatura> ArrayAsignaturas) {
        this.ArrayAsignaturas = ArrayAsignaturas;
    }

    public void ModificarAsignatura(PantallaMaterias PM, ControladorPrincipal CP) {
        String ID;
        Asignatura Asig = null;
        Boolean A = false;
        int I = 0;

        ID = JOptionPane.showInputDialog("Digite el Codigo de la materia: ");
        for (int a = 0; a < ArrayAsignaturas.size(); a++) {
            Asig = ArrayAsignaturas.get(a);
            if (Asig.getCodigo().equalsIgnoreCase(ID)) {
                A = true;
                I = a;
                PM.getTxtCodigo().setText(Asig.getCodigo());
                PM.getTxtNombre().setText(Asig.getNombre());
                PM.getTxtCreditos().setText(Asig.getCreditos());
            }

        }
        if (A == true) {
            ArrayAsignaturas.remove(I);
        }
        if (A == false) {
            JOptionPane.showMessageDialog(null, "Ese codigo no coincide con ninguna asignatura actualmente registrado", "Error", 0);
            CP.Desactivador(5);
        }
    }

    public void EliminarAsignatura() {
        String ID1 = "";
        int I = 0;
        boolean Encontrado = false;
        Asignatura Asig;
        ID1 = JOptionPane.showInputDialog("Digite el Codigo de la materia a eliminar: ");
        for (int a = 0; a < ArrayAsignaturas.size(); a++) {
            Asig = ArrayAsignaturas.get(a);
            if (Asig.getCodigo().equalsIgnoreCase(ID1)) {
                I = a;
                Encontrado = true;
                break;
            } else {
                Encontrado = false;
            }
        }
        if (Encontrado == true) {
            ArrayAsignaturas.remove(I);
            JOptionPane.showMessageDialog(null, "Se ha eliminado correctamente");
        }
        if (Encontrado == false) {
            JOptionPane.showMessageDialog(null, "Ese codigo no coincide con ninguna asignatura actualmente registrado", "Error", 0);
        }

    }

    public void AsignarMateria(PantallaMaterias PM, GestionProfesores GP) {
        String CDProf;
        String Codigo;
        String CDAsing;
        AsignaProf AsigP;
        Asignatura Asig1;
        boolean Existe = false;

        Codigo = JOptionPane.showInputDialog("Digite el código: ");
        Existe = ComprobarCodigo(Codigo);
        for (int a = 0; a < ArrayAsignaturas.size(); a++) {
            Asig1 = ArrayAsignaturas.get(a);
            if (Asig1.getCodigo().equalsIgnoreCase(Codigo)) {
                PM.getTxtCodigo().setText(Asig1.getCodigo());
                PM.getTxtNombre().setText(Asig1.getNombre());
                PM.getTxtCreditos().setText(Asig1.getCreditos());
                CDProf = GetProf(PM, GP);
                CDAsing = Codigo;
                Existe = true;
                if (CDProf != null) {
                    AsigP = new AsignaProf(CDProf, CDAsing);
                    GuardarAsignaProf(AsigP);
                    JOptionPane.showMessageDialog(null, "La operación se hizo exitosamente..!!");
                    break;
                }
            }

        }
        if (Existe == false) {
            JOptionPane.showMessageDialog(null, "Ese codigo no coincide con ninguna asignatura actualmente registrado");
        }
    }

    private String GetProf(PantallaMaterias PM, GestionProfesores GP) {
        Profesor Prof;
        String Nomb = "";
        String Cod = "";
        if (PM.getCboProfes().getSelectedItem().equals("---Seleccione un Profesor---")) {
            JOptionPane.showMessageDialog(null, "Seleccione un profesor de la lista", "ERROR", 0);
            Cod = null;
        } else {
            Nomb = (String) PM.getCboProfes().getSelectedItem();
            for (int a = 0; a < GP.getArrayProfesores().size(); a++) {
                Prof = GP.getArrayProfesores().get(a);
                if (Nomb.equalsIgnoreCase(Prof.getNombre())) {
                    Cod = Prof.getIdentificacion();
                    break;
                }
            }
        }
        return Cod;
    }

    private void GuardarAsignaProf(AsignaProf Asig) {
        ArrayAsignaProf.add(Asig);
        System.out.println(ArrayAsignaProf.get(0));
    }

    public ArrayList<AsignaProf> getArrayAsignaProf() {
        return ArrayAsignaProf;
    }

    public void setArrayAsignaProf(ArrayList<AsignaProf> ArrayAsignaProf) {
        this.ArrayAsignaProf = ArrayAsignaProf;
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

    public void EliminaAsignacion(PantallaMaterias PM, GestionProfesores GP) {
        String CDProf;
        String Codigo;
        String CDAsing;
        AsignaProf AsigP;
        Asignatura Asig1;
        int Elim = 0;
        boolean Existe = false;

        Codigo = JOptionPane.showInputDialog("Digite el código: ");
        Existe = ComprobarCodigo(Codigo);
        for (int a = 0; a < ArrayAsignaturas.size(); a++) {
            Asig1 = ArrayAsignaturas.get(a);
            if (Asig1.getCodigo().equalsIgnoreCase(Codigo)) {
                CDProf = GetProf(PM, GP);
                CDAsing = Codigo;
                Existe = true;
                if (CDProf != null) {
                    for (int b = 0; b < ArrayAsignaProf.size(); b++) {
                        AsigP = ArrayAsignaProf.get(b);
                        if (AsigP.getCodProfesor().equalsIgnoreCase(CDProf)) {
                            Elim = b;
                        }
                    }
                    ArrayAsignaProf.remove(Elim);
                    JOptionPane.showMessageDialog(null, "Se ha eliminado correctamenre");
                    break;
                }
            }

        }
        if (Existe == false) {
            JOptionPane.showMessageDialog(null, "Ese codigo no coincide con ninguna asignatura actualmente registrado");
        }

    }

    public void VTodos(VerTodosAsig VTodosA) {
        int FilaActual = 0;
        Asignatura Asig;

        DefaultTableModel modelo = (DefaultTableModel) VTodosA.getTblVertodos().getModel();
        TableRowSorter modeloSorter = new TableRowSorter(modelo);
        VTodosA.setRowSorter(modeloSorter);
        for (int a = 0; a < ArrayAsignaturas.size(); a++) {
            Asig = ArrayAsignaturas.get(a);
            if (FilaActual >= VTodosA.getTblVertodos().getRowCount()) {
                modelo.addRow(new Object[]{"", "", ""});
            }
            VTodosA.getTblVertodos().setValueAt(Asig.getCodigo(), FilaActual, 0);
            VTodosA.getTblVertodos().setValueAt(Asig.getNombre(), FilaActual, 1);
            VTodosA.getTblVertodos().setValueAt(Asig.getCreditos(), FilaActual, 2);

            FilaActual++;
        }

    }

}
