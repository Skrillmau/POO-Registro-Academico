/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.*;
import Vista.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author mate_
 */
public class GestionCarrera {

    boolean Existe = true;
    ArrayList<Carrera> ArrayCarrera = new ArrayList<>();
    Carrera Car;

    public void CapturarDatos(AddCarrera PC) {
        String Nombre = "";
        String Codigo = "";
        boolean AC = false, P = false, O = false;
        //Comprobar campos de ingreso de datos
        if (PC.getTxtCodigo().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese un codigo valido", "ERROR", 0);
        }
        if (PC.getTxtPrograma().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el nombre del programa", "ERROR", 0);
        } else if (PC.getTxtCodigo().getText().equals("") && PC.getTxtPrograma().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese datos en las casillas primero", "ERROR", 0);
        } else {
            do {
                Codigo = PC.getTxtCodigo().getText();
                this.Existe = ComprobarCodigo(Codigo);

                if (this.Existe == true) {
                    //Verificar si el codigo ya esta en uso
                    JOptionPane.showMessageDialog(null, "Codigo en uso, ingrese uno nuevo", "ERROR", 0);
                    System.out.println(Codigo);
                    PC.getTxtCodigo().requestFocus();

                } else {
                    //Recuperar Datos de las casillas de texto
                    Nombre = PC.getTxtPrograma().getText();
                    if (PC.getChkAcreditado().isSelected() == true) {
                        AC = true;
                    }
                    if (PC.getChkPresencial().isSelected() == true) {
                        P = true;
                    }
                    if (PC.getChkOnline().isSelected() == true) {
                        O = true;
                    }
                    this.Existe = true;
                    //Crear el objeto
                    Car = new Carrera(Codigo, Nombre, AC, P, O);
                    GuardaRCarrera(Car);
                    JOptionPane.showMessageDialog(null, "La operación se hizo exitosamente..!!");
                }
            } while (this.Existe == false);
        }
    }

    public void ModificarCarrera(Programas Prog, AddCarrera ACar) {
        String Cod;
        Carrera Car = null;
        Boolean A = false;
        int I = 0;

        Cod = JOptionPane.showInputDialog("Digite el código: ");
        for (int a = 0; a < ArrayCarrera.size(); a++) {
            Car = ArrayCarrera.get(a);
            if (Car.getCodigo().equalsIgnoreCase(Cod)) {
                A = true;
                I = a;
                ACar.setVisible(true);
                if (Car.isAcreditada() == true) {
                    ACar.getChkAcreditado().setSelected(true);
                }
                if (Car.isOnline() == true) {
                    ACar.getChkOnline().setSelected(true);
                }
                if (Car.isPresencial() == true) {
                    ACar.getChkPresencial().setSelected(true);
                }
                ACar.getTxtCodigo().setText(Car.getCodigo());
                ACar.getTxtPrograma().setText(Car.getCarrera());
            }

        }
        if (A == true) {
            ArrayCarrera.remove(I);
        }
        if (A == false) {
            JOptionPane.showMessageDialog(null, "ESE CODIGO NO EXISTE..!", "Error", 0);
        }
    }

    public void EliminarCarrera() {
        String Cod1 = "";
        int I = 0;
        boolean Encontrado = false;
        Carrera Car;
        Cod1 = JOptionPane.showInputDialog("Digite el codigo de la carrera a eliminar: ");
        for (int a = 0; a < ArrayCarrera.size(); a++) {
            Car = ArrayCarrera.get(a);
            if (Car.getCodigo().equalsIgnoreCase(Cod1)) {
                I = a;
                Encontrado = true;
                break;
            } else {
                Encontrado = false;
            }
        }
        if (Encontrado == true) {
            ArrayCarrera.remove(I);
            JOptionPane.showMessageDialog(null, "Se ha eliminado correctamente");
        }
        if (Encontrado == false) {
            JOptionPane.showMessageDialog(null, "Ese Codigo no coincide con ninguna carrera actualmente guardada", "Error", 0);
        }

    }

    public void BuscarCarrera(Programas Prog, AddCarrera ACar) {
        String Cod;
        Carrera Car;
        Boolean A = false;

        Cod = JOptionPane.showInputDialog("Digite el código: ");
        for (int a = 0; a < ArrayCarrera.size(); a++) {
            Car = ArrayCarrera.get(a);
            if (Car.getCodigo().equalsIgnoreCase(Cod)) {
                A = true;
                ACar.setVisible(true);
                if (Car.isAcreditada() == true) {
                    ACar.getChkAcreditado().setSelected(true);
                }
                if (Car.isOnline() == true) {
                    ACar.getChkOnline().setSelected(true);
                }
                if (Car.isPresencial() == true) {
                    ACar.getChkPresencial().setSelected(true);
                }
                ACar.getTxtCodigo().setText(Car.getCodigo());
                ACar.getTxtPrograma().setText(Car.getCarrera());
            }

        }
        if (A == false) {
            JOptionPane.showMessageDialog(null, "Ese Codigo no coincide con ninguna carrera actualmente guardada", "Error", 0);
            Prog.setVisible(true);
        }
    }

    public boolean ComprobarCodigo(String Cod) {
        Carrera Car;
        boolean Existe = false;
        for (int a = 0; a < ArrayCarrera.size(); a++) {
            Car = ArrayCarrera.get(a);
            if (Car.getCodigo().equals(Cod)) {
                Existe = true;
            }
        }
        return Existe;
    }

    private void GuardaRCarrera(Carrera Car) {
        ArrayCarrera.add(Car);
    }

    public ArrayList<Carrera> getArrayCarrera() {
        return ArrayCarrera;
    }

    public void setArrayCarrera(ArrayList<Carrera> ArrayCarrera) {
        this.ArrayCarrera = ArrayCarrera;
    }

    public void VerTodos(VerTodos VTodos) {
        int FilaActual = 0;
        Carrera Car;
        
        DefaultTableModel modelo = (DefaultTableModel) VTodos.getTblVertodos().getModel();
        TableRowSorter modeloSorter = new TableRowSorter(modelo);
        VTodos.setRowSorter(modeloSorter);
        for (int a = 0; a < ArrayCarrera.size(); a++) {
            Car = ArrayCarrera.get(a);
            if (FilaActual >= VTodos.getTblVertodos().getRowCount()) {
                modelo.addRow(new Object[]{"", "", "", "", ""});
            }
            VTodos.getTblVertodos().setValueAt(Car.getCodigo(), FilaActual, 0);
            VTodos.getTblVertodos().setValueAt(Car.getCarrera(), FilaActual, 1);
            if (Car.isAcreditada() == true) {
                VTodos.getTblVertodos().setValueAt("Si", FilaActual, 2);
            } else if (Car.isAcreditada() == false) {
                VTodos.getTblVertodos().setValueAt("No", FilaActual, 2);
            }
            if (Car.isPresencial() == true) {
                VTodos.getTblVertodos().setValueAt("Si", FilaActual, 3);
            } else if (Car.isPresencial() == false) {
                VTodos.getTblVertodos().setValueAt("No", FilaActual, 3);
            }
            if (Car.isOnline() == true) {
                VTodos.getTblVertodos().setValueAt("Si", FilaActual, 4);
            } else if (Car.isOnline() == false) {
                VTodos.getTblVertodos().setValueAt("No", FilaActual, 4);
            }
            FilaActual++;
        }

    }

}
