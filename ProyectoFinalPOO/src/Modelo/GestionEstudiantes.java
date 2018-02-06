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
public class GestionEstudiantes {

    boolean Existe = true;
    ArrayList<Estudiante> ArrayEstudiantes = new ArrayList<>();
    Estudiante Est;

    public void CapturarDatos(PantallaEstudiante PE) {
        String Nombre = "";
        String Codigo = "";
        String Apellido = "";
        String Telefono = "";
        String Carrera = "";
        String Direccion = "";
        String Correo = "";
        String Contraseña = "";
        String Foto="";

        //Comprobar campos de ingreso de datos
        if (PE.getTxtCodigo().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese un codigo valido", "ERROR", 0);
            PE.getTxtCodigo().requestFocus();
            PE.getTbtnNuevo().setText("Guardar");
        }
        if (PE.getTxtNombre().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese un nombre en la casilla correspondiente", "ERROR", 0);
            PE.getTxtNombre().requestFocus();
            PE.getTbtnNuevo().setText("Guardar");
        }
        if (PE.getTxtApellido().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese un apellido en la casilla correspondiente", "ERROR", 0);
            PE.getTxtApellido().requestFocus();
            PE.getTbtnNuevo().setText("Guardar");
        }
        if (PE.getTxtTelefono().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese un telefono en la casilla correspondiente", "ERROR", 0);
            PE.getTxtTelefono().requestFocus();
            PE.getTbtnNuevo().setText("Guardar");
        }
        if (PE.getTxtDireccion().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese una direccion en la casilla correspondiente", "ERROR", 0);
            PE.getTxtDireccion().requestFocus();
            PE.getTbtnNuevo().setText("Guardar");
        }
        if (PE.getTxtCorreo().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese un correo en la casilla correspondiente", "ERROR", 0);
            PE.getTxtCorreo().requestFocus();
            PE.getTbtnNuevo().setText("Guardar");
        }
        if (PE.getTxtContraseña().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese una contraseña en la casilla correspondiente", "ERROR", 0);
            PE.getTxtContraseña().requestFocus();
            PE.getTbtnNuevo().setText("Guardar");
        } //else if (PC.getTxtCodigo().getText().equals("") && PC.getTxtPrograma().getText().equals("")) {
        // JOptionPane.showMessageDialog(null, "Ingrese datos en las casillas primero", "ERROR", 0);
        //} 
        else {
            do {
                Codigo = PE.getTxtCodigo().getText();
                this.Existe = ComprobarCodigo(Codigo);

                if (this.Existe == true) {
                    //Verificar si el codigo ya esta en uso
                    JOptionPane.showMessageDialog(null, "Codigo en uso, ingrese uno nuevo", "ERROR", 0);
                    System.out.println(Codigo);
                    PE.getTxtCodigo().requestFocus();

                } else {
                    //Recuperar Datos de las casillas de texto
                    Nombre = PE.getTxtNombre().getText();
                    Apellido = PE.getTxtApellido().getText();
                    Telefono = PE.getTxtTelefono().getText();
                    Carrera = (String) PE.getCbCarrera().getSelectedItem();
                    Direccion = PE.getTxtDireccion().getText();
                    Correo = PE.getTxtCorreo().getText();
                    Contraseña=Contraseña(PE);
                    Foto = PE.foto;
               
                    this.Existe = true;
                    //Crear el objeto
                    Est = new Estudiante(Codigo, Nombre, Apellido, Telefono, Carrera, Direccion, Correo, Contraseña,Foto);
                    GuardarEstudiante(Est);
                    JOptionPane.showMessageDialog(null, "La operación se hizo exitosamente..!!");
                }
            } while (this.Existe == false);
        }
    }

    public boolean ComprobarCodigo(String Cod) {
        Estudiante Est;
        boolean Existe = false;
        for (int a = 0; a < ArrayEstudiantes.size(); a++) {
            Est = ArrayEstudiantes.get(a);
            if (Est.getCodigo().equals(Cod)) {
                Existe = true;
            }
        }
        return Existe;
    }

    private void GuardarEstudiante(Estudiante Est) {
        ArrayEstudiantes.add(Est);
    }

    public void BuscarEstudiante(PantallaEstudiante PEst) {
        String Cod;
        Estudiante Est;
        Boolean A = false;

        Cod = JOptionPane.showInputDialog("Digite el código: ");
        for (int a = 0; a < ArrayEstudiantes.size(); a++) {
            Est = ArrayEstudiantes.get(a);
            if (Est.getCodigo().equalsIgnoreCase(Cod)) {
                A = true;
                PEst.getTxtCodigo().setText(Est.getCodigo());
                PEst.getTxtNombre().setText(Est.getNombre());
                PEst.getTxtApellido().setText(Est.getApellido());
                PEst.getTxtTelefono().setText(Est.getTelefono());
                PEst.getTxtDireccion().setText(Est.getDireccion());
                PEst.getTxtCorreo().setText(Est.getCorreo());
                PEst.getTxtContraseña().setText(Est.getContraseña());
                PEst.getCbCarrera().setSelectedItem(Est.getCarrera());
                PEst.PoneFoto(Est.getFoto());
            }

        }
        if (A == false) {
            JOptionPane.showMessageDialog(null, "Ese Codigo no coincide con ningun Estudiante actualmente registrado", "Error", 0);

        }
    }

    public ArrayList<Estudiante> getArrayEstudiantes() {
        return ArrayEstudiantes;
    }

    public void setArrayEstudiantes(ArrayList<Estudiante> ArrayEstudiantes) {
        this.ArrayEstudiantes = ArrayEstudiantes;
    }

    public void ModificarEstudiante(PantallaEstudiante PEst, ControladorPrincipal CP) {
        String Cod;
        Estudiante Est = null;
        Boolean A = false;
        int I = 0;

        Cod = JOptionPane.showInputDialog("Digite el código: ");
        for (int a = 0; a < ArrayEstudiantes.size(); a++) {
            Est = ArrayEstudiantes.get(a);
            if (Est.getCodigo().equalsIgnoreCase(Cod)) {
                A = true;
                I = a;
                PEst.getTxtCodigo().setText(Est.getCodigo());
                PEst.getTxtNombre().setText(Est.getNombre());
                PEst.getTxtApellido().setText(Est.getApellido());
                PEst.getTxtTelefono().setText(Est.getTelefono());
                PEst.getTxtDireccion().setText(Est.getDireccion());
                PEst.getTxtCorreo().setText(Est.getCorreo());
                PEst.getTxtContraseña().setText(Est.getContraseña());
                PEst.getCbCarrera().setSelectedItem(Est.getCarrera());
                PEst.PoneFoto(Est.getFoto());
            }

        }
        if (A == true) {
            ArrayEstudiantes.remove(I);
        }
        if (A == false) {
            JOptionPane.showMessageDialog(null, "Ese Codigo no coincide con ningun Estudiante actualmente registrado", "Error", 0);
            CP.Desactivador(5);
        }
    }
    public void EliminarEstudiante() {
        String Cod1 = "";
        int I = 0;
        boolean Encontrado = false;
        Estudiante Est;
        Cod1 = JOptionPane.showInputDialog("Digite el codigo de la carrera a eliminar: ");
        for (int a = 0; a < ArrayEstudiantes.size(); a++) {
            Est = ArrayEstudiantes.get(a);
            if (Est.getCodigo().equalsIgnoreCase(Cod1)) {
                I = a;
                Encontrado = true;
                break;
            } else {
                Encontrado = false;
            }
        }
        if (Encontrado == true) {
            ArrayEstudiantes.remove(I);
            JOptionPane.showMessageDialog(null, "Se ha eliminado correctamente");
        }
        if (Encontrado == false) {
            JOptionPane.showMessageDialog(null, "Ese Codigo no coincide con ningun Estudiante acrtualmente registrado", "Error", 0);
        }
        

    }
    public void VerTodosEst(VerTodosEst VTodosE) {
        int FilaActual = 0;
        Estudiante Est;
        
        DefaultTableModel modelo = (DefaultTableModel) VTodosE.getTblVertodos().getModel();
        TableRowSorter modeloSorter = new TableRowSorter(modelo);
        VTodosE.setRowSorter(modeloSorter);
        for (int a = 0; a < ArrayEstudiantes.size(); a++) {
            Est = ArrayEstudiantes.get(a);
            if (FilaActual >= VTodosE.getTblVertodos().getRowCount()) {
                modelo.addRow(new Object[]{"", "", "", "", "","",""});
            }
            VTodosE.getTblVertodos().setValueAt(Est.getCodigo(), FilaActual, 0);
            VTodosE.getTblVertodos().setValueAt(Est.getNombre(), FilaActual, 1);
            VTodosE.getTblVertodos().setValueAt(Est.getApellido(), FilaActual, 2);
            VTodosE.getTblVertodos().setValueAt(Est.getTelefono(), FilaActual, 3);
            VTodosE.getTblVertodos().setValueAt(Est.getCarrera(), FilaActual, 4);
            VTodosE.getTblVertodos().setValueAt(Est.getDireccion(), FilaActual, 5);
            VTodosE.getTblVertodos().setValueAt(Est.getCorreo(), FilaActual, 6);
            
            FilaActual++;
        }

    }

    private String Contraseña(PantallaEstudiante PE) {
        char [] Pass = PE.getTxtContraseña().getPassword();
        String Password = new String(Pass);
        return Password;
    }
}
