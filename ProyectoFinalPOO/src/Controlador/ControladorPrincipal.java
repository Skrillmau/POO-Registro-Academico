package Controlador;

import Modelo.*;
import Vista.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorPrincipal {

    private static ControladorPrincipal ContPrinc;
    private static MenuInicial MenuP;
    private static Programas Programas;
    private static AddCarrera Carrera;
    private static GestionCarrera GCarrera;
    private static PersistenciaCarrera PCarrera;
    private static PantallaEstudiante PEstudiante;
    private static VerTodos VTodos;
    private static GestionEstudiantes GEstudiantes;
    private static PersistenciaEstudiantes PEstudiantes;
    private static VerTodosEst VTodosE;
    private static PantallaProfesores PProfesor;
    private static GestionProfesores GProfesor;
    private static PersistenciaProfesores PProfesores;
    private static VerTodosProf VTodosP;
    private static PantallaMaterias PMaterias;
    private static GestionAsignaturas GAsignaturas;
    private static PersistenciaAsignaturas PAsignatura;
    private static PersistenciaAsignaProf PAsigna;
    private static InscripcionMaterias IMaterias;
    private static GestionInscripcion GInscripcion;
    private static PersistenciaInscripciones PInscripcion;
    private static VerTodosAsig VTAsig;

    public static void main(String[] args) {
        ContPrinc = new ControladorPrincipal();
        MenuP = new MenuInicial(ContPrinc);
        MenuP.setVisible(true);
        Programas = new Programas(ContPrinc);
        Carrera = new AddCarrera(ContPrinc);
        GCarrera = new GestionCarrera();
        PCarrera = new PersistenciaCarrera();
        PEstudiante = new PantallaEstudiante(ContPrinc);
        GCarrera.setArrayCarrera(PCarrera.CargarTodo());
        GEstudiantes = new GestionEstudiantes();
        PEstudiantes = new PersistenciaEstudiantes();
        GEstudiantes.setArrayEstudiantes(PEstudiantes.CargarTodo());
        PProfesor = new PantallaProfesores(ContPrinc);
        GProfesor = new GestionProfesores();
        PProfesores = new PersistenciaProfesores();
        GProfesor.setArrayProfesores(PProfesores.CargarTodo());
        PMaterias = new PantallaMaterias(ContPrinc);
        GAsignaturas = new GestionAsignaturas();
        PAsignatura = new PersistenciaAsignaturas();
        GAsignaturas.setArrayAsignaturas(PAsignatura.CargarTodo());
        PAsigna = new PersistenciaAsignaProf();
        GAsignaturas.setArrayAsignaProf(PAsigna.CargarTodo());
        IMaterias = new InscripcionMaterias(ContPrinc);
        GInscripcion = new GestionInscripcion();
        PInscripcion = new PersistenciaInscripciones();
        GInscripcion.setArrayInscripciones(PInscripcion.CargarTodo());
        VTAsig= new VerTodosAsig(ContPrinc);

    }

    public void ManejarEvento(String Evento) {
        if (Evento.equalsIgnoreCase("Salir")) {
            System.exit(0);
        }
        if (Evento.equalsIgnoreCase("Programas")) {
            GCarrera.setArrayCarrera(PCarrera.CargarTodo());
            Programas.setVisible(true);
            MenuP.setVisible(false);
        }
        if (Evento.equalsIgnoreCase("RegresarMenu")) {
            PCarrera.GuardarTodo(GCarrera.getArrayCarrera());
            Programas.setVisible(false);
            MenuP.setVisible(true);
        }
        if (Evento.equalsIgnoreCase("AgregarCarrera")) {
            Limpiar(1);
            Desactivador(3);
            Programas.setVisible(false);
            Carrera.setVisible(true);
        }
        if (Evento.equalsIgnoreCase("RegresarProgramas")) {
            Programas.setVisible(true);
            Carrera.setVisible(false);
        }
        if (Evento.equalsIgnoreCase("Guardar")) {
            PCarrera.GuardarTodo(GCarrera.getArrayCarrera());
            Carrera.getBtnRegresar().setEnabled(true);
            GCarrera.CapturarDatos(Carrera);
        }
        if (Evento.equalsIgnoreCase("Estudiante")) {
            GEstudiantes.setArrayEstudiantes(PEstudiantes.CargarTodo());
            CargarCombobox();
            PEstudiante.setVisible(true);
            MenuP.setVisible(false);
        }
        if (Evento.equalsIgnoreCase("RegresarMenuP")) {
            PEstudiantes.GuardarTodo((GEstudiantes.getArrayEstudiantes()));
            PEstudiante.setVisible(false);
            MenuP.setVisible(true);
        }
        if (Evento.equalsIgnoreCase("VerTodos")) {
            VTodos = new VerTodos(ContPrinc);
            VTodos.setVisible(true);
            Programas.setVisible(false);
            GCarrera.VerTodos(VTodos);
        }
        if (Evento.equalsIgnoreCase("RegresarMP")) {
            VTodos.setVisible(false);
            Programas.setVisible(true);
        }
        if (Evento.equalsIgnoreCase("Limpiar")) {
            Limpiar(1);
        }
        if (Evento.equalsIgnoreCase("BuscarP")) {
            Desactivador(2);
            Programas.setVisible(false);
            GCarrera.BuscarCarrera(Programas, Carrera);
        }
        if (Evento.equalsIgnoreCase("EliminarPrograma")) {
            GCarrera.EliminarCarrera();
            PCarrera.GuardarTodo(GCarrera.getArrayCarrera());
        }
        if (Evento.equalsIgnoreCase("ModificarCarrera")) {
            Desactivador(1);
            if (Evento.equalsIgnoreCase("Guardar")) {
                Carrera.getBtnRegresar().setEnabled(true);
            }
            GCarrera.ModificarCarrera(Programas, Carrera);
            PCarrera.GuardarTodo(GCarrera.getArrayCarrera());
        }
        if (Evento.equalsIgnoreCase("N/G")) {
            if (PEstudiante.getTbtnNuevo().getText().equalsIgnoreCase("Nuevo")) {
                Desactivador(4);
                Limpiar(2);
                PEstudiante.getTbtnNuevo().setText("Guardar");
            } else if (PEstudiante.getTbtnNuevo().getText().equalsIgnoreCase("Guardar")) {
                GEstudiantes.CapturarDatos(PEstudiante);
                Desactivador(5);
                PEstudiantes.GuardarTodo(GEstudiantes.getArrayEstudiantes());
                PEstudiante.getTbtnNuevo().setText("Nuevo");

            }
        }
        if (Evento.equalsIgnoreCase("B/R")) {
            if (PEstudiante.getTbtnBuscar().getText().equalsIgnoreCase("Buscar")) {
                Desactivador(6);
                GEstudiantes.BuscarEstudiante(PEstudiante);
                PEstudiante.getTbtnBuscar().setText("Regresar");
            } else if (PEstudiante.getTbtnBuscar().getText().equalsIgnoreCase("Regresar")) {
                Limpiar(2);
                Desactivador(5);
                PEstudiante.getTbtnBuscar().setText("Buscar");
            }
        }
        if (Evento.equalsIgnoreCase("ModificarES")) {
            Desactivador(7);
            PEstudiante.getTbtnNuevo().setText("Guardar");
            if (Evento.equalsIgnoreCase("N/G")) {
                PEstudiante.getBtnregresar().setEnabled(true);
            }
            GEstudiantes.ModificarEstudiante(PEstudiante, ContPrinc);
        }
        if (Evento.equalsIgnoreCase("EliminarE")) {
            GEstudiantes.EliminarEstudiante();
            PEstudiantes.GuardarTodo(GEstudiantes.getArrayEstudiantes());
        }
        if (Evento.equalsIgnoreCase("VerTodosES")) {
            VTodosE = new VerTodosEst(ContPrinc);
            PEstudiante.setVisible(false);
            VTodosE.setVisible(true);
            GEstudiantes.VerTodosEst(VTodosE);
        }
        if (Evento.equalsIgnoreCase("RegresarMPE")) {
            VTodosE.setVisible(false);
            PEstudiante.setVisible(true);
        }
        if (Evento.equalsIgnoreCase("Profesores")) {
            CargarCombobox();
            GProfesor.setArrayProfesores(PProfesores.CargarTodo());
            MenuP.setVisible(false);
            PProfesor.setVisible(true);
        }
        if (Evento.equalsIgnoreCase("RegresarMPP")) {
            MenuP.setVisible(true);
            PProfesor.setVisible(false);
        }
        if (Evento.equalsIgnoreCase("PN/G")) {
            if (PProfesor.getTbtnNuevo().getText().equalsIgnoreCase("Nuevo")) {
                Desactivador(8);
                Limpiar(3);
                PProfesor.getTbtnNuevo().setText("Guardar");
            } else if (PProfesor.getTbtnNuevo().getText().equalsIgnoreCase("Guardar")) {
                GProfesor.CapturarDatos(PProfesor);
                Desactivador(9);
                PProfesores.GuardarTodo(GProfesor.getArrayProfesores());
                PProfesor.getTbtnNuevo().setText("Nuevo");
            }
        }
        if (Evento.equalsIgnoreCase("ModificarPR")) {
            Desactivador(11);
            PProfesor.getTbtnNuevo().setText("Guardar");
            if (Evento.equalsIgnoreCase("PN/G")) {
                PProfesor.getBtnRegresar().setEnabled(true);
            }
            GProfesor.ModificarProfesor(PProfesor, ContPrinc);
        }
        if (Evento.equalsIgnoreCase("EliminarP")) {
            GProfesor.EliminarProfesor();
            PProfesores.GuardarTodo(GProfesor.getArrayProfesores());
        }
        if (Evento.equalsIgnoreCase("PB/R")) {
            if (PProfesor.getTbtnBuscar().getText().equalsIgnoreCase("Buscar")) {
                Desactivador(10);
                GProfesor.BuscarProfesor(PProfesor);
                PProfesor.getTbtnBuscar().setText("Regresar");
            } else if (PProfesor.getTbtnBuscar().getText().equalsIgnoreCase("Regresar")) {
                Limpiar(3);
                Desactivador(9);
                PProfesor.getTbtnBuscar().setText("Buscar");
            }
        }
        if (Evento.equalsIgnoreCase("VTodosP")) {
            VTodosP = new VerTodosProf(ContPrinc);
            VTodosP.setVisible(true);
            PProfesor.setVisible(false);
            GProfesor.VerTodosEst(VTodosP);
        }
        if (Evento.equalsIgnoreCase("RegresarPPR")) {
            VTodosP.setVisible(false);
            PProfesor.setVisible(true);
        }
        if (Evento.equalsIgnoreCase("RegresarPM")) {
            MenuP.setVisible(true);
            PMaterias.setVisible(false);
            PProfesores.GuardarTodo(GProfesor.getArrayProfesores());
            PAsigna.GuardarTodo(GAsignaturas.getArrayAsignaProf());
        }
        if (Evento.equalsIgnoreCase("Asignaturas")) {
            GAsignaturas.setArrayAsignaProf(PAsigna.CargarTodo());
            GAsignaturas.setArrayAsignaturas(PAsignatura.CargarTodo());
            CargarComboboxP();
            MenuP.setVisible(false);
            PMaterias.setVisible(true);
        }
        if (Evento.equalsIgnoreCase("GuardarM")) {
            Desactivador(13);
            GAsignaturas.CapturarDatos(PMaterias);
            PAsignatura.GuardarTodo(GAsignaturas.getArrayAsignaturas());
        }
        if (Evento.equalsIgnoreCase("MB/R")) {
            GAsignaturas.setArrayAsignaProf(PAsigna.CargarTodo());
            if (PMaterias.getTbtnBuscar().getText().equalsIgnoreCase("Buscar")) {
                Desactivador(12);
                GAsignaturas.BuscarAsignatura(PMaterias, GProfesor);
                PMaterias.getTbtnBuscar().setText("Regresar");
            } else if (PMaterias.getTbtnBuscar().getText().equalsIgnoreCase("Regresar")) {
                Limpiar(4);
                Desactivador(13);
                PMaterias.getTbtnBuscar().setText("Buscar");
            }
        }
        if (Evento.equalsIgnoreCase("ModificarM")) {
            Desactivador(14);
            GAsignaturas.ModificarAsignatura(PMaterias, ContPrinc);
        }
        if (Evento.equalsIgnoreCase("EliminarM")) {
            GAsignaturas.EliminarAsignatura();
            PAsignatura.GuardarTodo(GAsignaturas.getArrayAsignaturas());
        }
        if (Evento.equalsIgnoreCase("LimpiarM")) {
            Limpiar(4);
        }
        if (Evento.equalsIgnoreCase("AgregarPr")) {
            GAsignaturas.AsignarMateria(PMaterias, GProfesor);
            PAsigna.GuardarTodo(GAsignaturas.getArrayAsignaProf());
        }
        if (Evento.equalsIgnoreCase("EliminarPr")) {
            GAsignaturas.EliminaAsignacion(PMaterias, GProfesor);
            PAsigna.GuardarTodo(GAsignaturas.getArrayAsignaProf());
        }
        if (Evento.equalsIgnoreCase("Inscribir")) {
            MenuP.setVisible(false);
            IMaterias.setVisible(true);
            Desactivador(15);
        }
        if (Evento.equalsIgnoreCase("RegresarIM")) {
            MenuP.setVisible(true);
            IMaterias.setVisible(false);
            Limpiar(5);
            PInscripcion.GuardarTodo(GInscripcion.getArrayInscripciones());
        }
        if (Evento.equalsIgnoreCase("Login")) {
            boolean Ok = GInscripcion.Login(IMaterias, GEstudiantes);
            if (Ok == true) {
                Desactivador(16);
                CargarComboboxA();
                Desactivador(18);
                GInscripcion.setArrayInscripciones(PInscripcion.CargarTodo());
            }
            if (Ok == false) {
                Limpiar(5);
                JOptionPane.showMessageDialog(null, "Datos incorrectos intente nuevamente", "Error", 0);
            }
        }
        if (Evento.equalsIgnoreCase("AñadirIns")) {
            Desactivador(17);
            GInscripcion.AgregarInscripcion(IMaterias, this, GProfesor, GAsignaturas);
            PInscripcion.GuardarTodo(GInscripcion.getArrayInscripciones());

        }
        if (Evento.equalsIgnoreCase("EliminarIns")) {
            Desactivador(17);
            GInscripcion.EliminarInscripcion(IMaterias, this, GProfesor, GAsignaturas);
            PInscripcion.GuardarTodo(GInscripcion.getArrayInscripciones());
        }
        if (Evento.equalsIgnoreCase("VIncripcion")) {
            Desactivador(18);
            GInscripcion.Tabla(IMaterias, IMaterias.getTxtCodigo().getText(), GProfesor, GAsignaturas);
        }
        if(Evento.equalsIgnoreCase("HInscripcion")){
            Desactivador(19);
        }
        if(Evento.equalsIgnoreCase("VTodosAS")){
            VTAsig.setVisible(true);
            PMaterias.setVisible(false);
            GAsignaturas.VTodos(VTAsig);
        }
        if(Evento.equalsIgnoreCase("RegresarMPAS")){
            VTAsig.setVisible(false);
            PMaterias.setVisible(true);
        }

    }

    public void Limpiar(int A) {
        if (A == 1) {
            Carrera.getChkAcreditado().setSelected(false);
            Carrera.getChkPresencial().setSelected(false);
            Carrera.getChkOnline().setSelected(false);
            Carrera.getTxtCodigo().setText("");
            Carrera.getTxtPrograma().setText("");
        }
        if (A == 2) {
            PEstudiante.getTxtApellido().setText("");
            PEstudiante.getTxtCodigo().setText("");
            PEstudiante.getTxtNombre().setText("");
            PEstudiante.getTxtDireccion().setText("");
            PEstudiante.getTxtTelefono().setText("");
            PEstudiante.getTxtCorreo().setText("");
            PEstudiante.getTxtContraseña().setText("");
            PEstudiante.getCbCarrera().setSelectedItem("");

        }
        if (A == 3) {
            PProfesor.getTxtID().setText("");
            PProfesor.getTxtNombres().setText("");
            PProfesor.getTxtApellidos().setText("");
            PProfesor.getCboCarreras().setSelectedItem("");
        }
        if (A == 4) {
            PMaterias.getTxtCodigo().setText("");
            PMaterias.getTxtNombre().setText("");
            PMaterias.getTxtCreditos().setText("");
            PMaterias.getCboProfes().setSelectedIndex(0);
        }
        if (A == 5) {
            IMaterias.getCboAsignaturas().removeAllItems();
            IMaterias.getCboProfes().removeAllItems();
            IMaterias.getTxtPass().setText("");
            IMaterias.getTxtCodigo().setText("");
        }
    }

    public void Desactivador(int A) {
        if (A == 1) {
            Carrera.getChkAcreditado().setEnabled(true);
            Carrera.getChkOnline().setEnabled(true);
            Carrera.getChkPresencial().setEnabled(true);
            Carrera.getBtnGuardar().setEnabled(true);
            Carrera.getBtnLimpiar().setEnabled(false);
            Carrera.getBtnRegresar().setEnabled(false);
        }
        if (A == 2) {
            Carrera.getChkAcreditado().setEnabled(false);
            Carrera.getChkOnline().setEnabled(false);
            Carrera.getChkPresencial().setEnabled(false);
            Carrera.getBtnGuardar().setEnabled(false);
            Carrera.getBtnLimpiar().setEnabled(false);
            Carrera.getBtnRegresar().setEnabled(true);
        }
        if (A == 3) {
            Carrera.getChkAcreditado().setEnabled(true);
            Carrera.getChkOnline().setEnabled(true);
            Carrera.getChkPresencial().setEnabled(true);
            Carrera.getBtnGuardar().setEnabled(true);
            Carrera.getBtnLimpiar().setEnabled(true);
            Carrera.getBtnRegresar().setEnabled(true);
        }
        if (A == 4) {
            PEstudiante.getTbtnBuscar().setEnabled(false);
            PEstudiante.getBtnModificar().setEnabled(false);
            PEstudiante.getBtnVertodos().setEnabled(false);
            PEstudiante.getBtnEliminar().setEnabled(false);
            PEstudiante.PoneFoto("sinfoto.png");
        }
        if (A == 5) {
            PEstudiante.getTbtnNuevo().setEnabled(true);
            PEstudiante.getTbtnBuscar().setEnabled(true);
            PEstudiante.getBtnModificar().setEnabled(true);
            PEstudiante.getBtnVertodos().setEnabled(true);
            PEstudiante.getBtnEliminar().setEnabled(true);
            PEstudiante.getTxtCodigo().setEditable(true);
            PEstudiante.getTxtNombre().setEditable(true);
            PEstudiante.getTxtApellido().setEditable(true);
            PEstudiante.getTxtTelefono().setEditable(true);
            PEstudiante.getTxtDireccion().setEditable(true);
            PEstudiante.getTxtCorreo().setEditable(true);
            PEstudiante.getTxtContraseña().setEditable(true);
            PEstudiante.getCbCarrera().setEnabled(true);
            PEstudiante.getBtnregresar().setEnabled(true);
            PEstudiante.PoneFoto("sinfoto.png");
        }
        if (A == 6) {
            PEstudiante.getTbtnNuevo().setEnabled(false);
            PEstudiante.getBtnModificar().setEnabled(false);
            PEstudiante.getBtnVertodos().setEnabled(false);
            PEstudiante.getBtnEliminar().setEnabled(false);
            PEstudiante.getTxtCodigo().setEditable(false);
            PEstudiante.getTxtNombre().setEditable(false);
            PEstudiante.getTxtApellido().setEditable(false);
            PEstudiante.getTxtTelefono().setEditable(false);
            PEstudiante.getTxtDireccion().setEditable(false);
            PEstudiante.getTxtCorreo().setEditable(false);
            PEstudiante.getTxtContraseña().setEditable(false);
            PEstudiante.getCbCarrera().setEnabled(false);
        }
        if (A == 7) {
            PEstudiante.getTbtnNuevo().setEnabled(true);
            PEstudiante.getTbtnBuscar().setEnabled(false);
            PEstudiante.getBtnModificar().setEnabled(false);
            PEstudiante.getBtnVertodos().setEnabled(false);
            PEstudiante.getBtnEliminar().setEnabled(false);
            PEstudiante.getTxtCodigo().setEditable(false);
            PEstudiante.getTxtNombre().setEditable(true);
            PEstudiante.getTxtApellido().setEditable(true);
            PEstudiante.getTxtTelefono().setEditable(true);
            PEstudiante.getTxtDireccion().setEditable(true);
            PEstudiante.getTxtCorreo().setEditable(true);
            PEstudiante.getTxtContraseña().setEditable(true);
            PEstudiante.getCbCarrera().setEnabled(true);
            PEstudiante.getBtnregresar().setEnabled(false);
        }
        if (A == 8) {
            PProfesor.getBtnEliminar().setEnabled(false);
            PProfesor.getBtnVTodos().setEnabled(false);
            PProfesor.getBtnModificar().setEnabled(false);
            PProfesor.getTbtnBuscar().setEnabled(false);
            PProfesor.getTxtID().setEditable(true);
            PProfesor.getTbtnNuevo().setEnabled(true);
            PProfesor.getTxtNombres().setEditable(true);
            PProfesor.getTxtApellidos().setEditable(true);
            PProfesor.getCboCarreras().setEnabled(true);
            PProfesor.PoneFoto("sinfoto.png");
        }
        if (A == 9) {
            PProfesor.getBtnEliminar().setEnabled(true);
            PProfesor.getBtnVTodos().setEnabled(true);
            PProfesor.getBtnModificar().setEnabled(true);
            PProfesor.getTbtnBuscar().setEnabled(true);
            PProfesor.getTxtID().setEnabled(true);
            PProfesor.getTbtnNuevo().setEnabled(true);
            PProfesor.getTxtNombres().setEditable(true);
            PProfesor.getTxtApellidos().setEditable(true);
            PProfesor.getCboCarreras().setEditable(true);
            PProfesor.getBtnRegresar().setEnabled(true);
            PProfesor.PoneFoto("sinfoto.png");
        }
        if (A == 10) {
            PProfesor.getBtnEliminar().setEnabled(false);
            PProfesor.getBtnVTodos().setEnabled(false);
            PProfesor.getBtnModificar().setEnabled(false);
            PProfesor.getTbtnBuscar().setEnabled(true);
            PProfesor.getTbtnNuevo().setEnabled(true);
            PProfesor.getBtnRegresar().setEnabled(false);
            PProfesor.getTxtID().setEditable(false);
            PProfesor.getTxtNombres().setEditable(false);
            PProfesor.getTxtApellidos().setEditable(false);
            PProfesor.getCboCarreras().setEnabled(false);
        }
        if (A == 11) {
            PProfesor.getBtnEliminar().setEnabled(false);
            PProfesor.getBtnVTodos().setEnabled(false);
            PProfesor.getBtnModificar().setEnabled(false);
            PProfesor.getTbtnBuscar().setEnabled(false);
            PProfesor.getTbtnNuevo().setEnabled(true);
            PProfesor.getBtnRegresar().setEnabled(false);
            PProfesor.getTxtID().setEditable(true);
            PProfesor.getTxtNombres().setEditable(true);
            PProfesor.getTxtApellidos().setEditable(true);
            PProfesor.getCboCarreras().setEnabled(true);
        }
        if (A == 12) {
            PMaterias.getBtnEliminar().setEnabled(false);
            PMaterias.getBtnVTodos().setEnabled(false);
            PMaterias.getBtnModificar().setEnabled(false);
            PMaterias.getTbtnBuscar().setEnabled(true);
            PMaterias.getBtnNuevo().setEnabled(false);
            PMaterias.getBtnRegresar().setEnabled(false);
            PMaterias.getTxtCodigo().setEditable(false);
            PMaterias.getTxtNombre().setEditable(false);
            PMaterias.getTxtCreditos().setEditable(false);
            PMaterias.getBtnGuardar().setEnabled(false);
            PMaterias.getBtnAgregarP().setEnabled(false);
            PMaterias.getBtnEliminarP().setEnabled(false);
            PMaterias.getCboProfes().setEnabled(false);
        }
        if (A == 13) {
            PMaterias.getBtnEliminar().setEnabled(true);
            PMaterias.getBtnVTodos().setEnabled(true);
            PMaterias.getBtnModificar().setEnabled(true);
            PMaterias.getTbtnBuscar().setEnabled(true);
            PMaterias.getBtnNuevo().setEnabled(true);
            PMaterias.getBtnRegresar().setEnabled(true);
            PMaterias.getTxtCodigo().setEditable(true);
            PMaterias.getTxtNombre().setEditable(true);
            PMaterias.getTxtCreditos().setEditable(true);
            PMaterias.getBtnGuardar().setEnabled(true);
            PMaterias.getBtnAgregarP().setEnabled(true);
            PMaterias.getBtnEliminarP().setEnabled(true);
            PMaterias.getCboProfes().setEnabled(true);
            int filas = PMaterias.getTblProf().getRowCount();
            DefaultTableModel modelo = (DefaultTableModel) PMaterias.getTblProf().getModel();
            if (filas > 0) {
                for (int a = 0; a < filas; a++) {
                    modelo.removeRow(0);
                }
            }

        }
        if (A == 14) {
            PMaterias.getBtnEliminar().setEnabled(false);
            PMaterias.getBtnVTodos().setEnabled(false);
            PMaterias.getBtnModificar().setEnabled(false);
            PMaterias.getTbtnBuscar().setEnabled(true);
            PMaterias.getBtnNuevo().setEnabled(true);
            PMaterias.getBtnRegresar().setEnabled(false);
            PMaterias.getTxtCodigo().setEditable(false);
            PMaterias.getTxtNombre().setEditable(true);
            PMaterias.getTxtCreditos().setEditable(true);
            PMaterias.getBtnGuardar().setEnabled(true);
        }
        if (A == 15) {
            IMaterias.getBtnHIscripcion().setVisible(false);
            IMaterias.getBtnVInscripcion().setVisible(false);
            IMaterias.getBtnAñadir().setVisible(false);
            IMaterias.getBtnBorrar().setVisible(false);
            IMaterias.getCboAsignaturas().setVisible(false);
            IMaterias.getCboProfes().setVisible(false);
            IMaterias.getTblInsc().setVisible(false);
            IMaterias.getTblInsc().setVisible(false);
            IMaterias.getLblAsig().setVisible(false);
            IMaterias.getLblProf().setVisible(false);
            IMaterias.getTxtCodigo().setEditable(true);
            IMaterias.getTxtPass().setEditable(true);
            IMaterias.getjScrollPane1().setVisible(false);
            IMaterias.getBtnListo().setEnabled(true);

        }
        if (A == 16) {
            IMaterias.getBtnHIscripcion().setVisible(true);
            IMaterias.getBtnVInscripcion().setVisible(true);
            IMaterias.getBtnAñadir().setVisible(true);
            IMaterias.getBtnBorrar().setVisible(true);
            IMaterias.getCboAsignaturas().setVisible(true);
            IMaterias.getCboProfes().setVisible(true);
            IMaterias.getTblInsc().setVisible(true);
            IMaterias.getTblInsc().setVisible(true);
            IMaterias.getLblAsig().setVisible(true);
            IMaterias.getLblProf().setVisible(true);
            IMaterias.getTxtCodigo().setEditable(false);
            IMaterias.getTxtPass().setEditable(false);
            IMaterias.getjScrollPane1().setVisible(true);
            IMaterias.getBtnListo().setEnabled(false);
        }
        if (A == 17) {
            int filas = IMaterias.getTblInsc().getRowCount();
            DefaultTableModel modelo = (DefaultTableModel) IMaterias.getTblInsc().getModel();
            if (filas > 0) {
                for (int a = 0; a < filas; a++) {
                    modelo.removeRow(0);
                }
            }
        }
        if (A == 18) {
            IMaterias.getBtnAñadir().setEnabled(false);
            IMaterias.getBtnBorrar().setEnabled(false);
            IMaterias.getCboAsignaturas().setEnabled(false);
            IMaterias.getCboProfes().setEnabled(false);
        }
        if (A == 19) {
            IMaterias.getBtnAñadir().setEnabled(true);
            IMaterias.getBtnBorrar().setEnabled(true);
            IMaterias.getCboAsignaturas().setEnabled(true);
            IMaterias.getCboProfes().setEnabled(true);
        }
    }

    private void CargarCombobox() {
        Carrera Car;
        PEstudiante.getCbCarrera().removeAllItems();
        PEstudiante.getCbCarrera().addItem("---Seleccione una Carrera---");
        for (int a = 0; a < GCarrera.getArrayCarrera().size(); a++) {
            Car = GCarrera.getArrayCarrera().get(a);
            PEstudiante.getCbCarrera().addItem(Car.getCarrera());
        }
        PProfesor.getCboCarreras().removeAllItems();
        PProfesor.getCboCarreras().addItem("---Seleccione una Carrera---");
        for (int a = 0; a < GCarrera.getArrayCarrera().size(); a++) {
            Car = GCarrera.getArrayCarrera().get(a);
            PProfesor.getCboCarreras().addItem(Car.getCarrera());
        }
    }

    private void CargarComboboxP() {
        Profesor prof;
        PMaterias.getCboProfes().removeAllItems();
        PMaterias.getCboProfes().addItem("---Seleccione un Profesor---");
        for (int a = 0; a < GProfesor.getArrayProfesores().size(); a++) {
            prof = GProfesor.getArrayProfesores().get(a);
            PMaterias.getCboProfes().addItem(prof.getNombre());
        }
    }

    private void CargarComboboxA() {
        String Nombre = "";
        AsignaProf AsigP;
        Asignatura Asig;
        IMaterias.getCboAsignaturas().removeAllItems();
        IMaterias.getCboAsignaturas().addItem("---Seleccione una Asignatura---");
        for (int a = 0; a < GAsignaturas.getArrayAsignaProf().size(); a++) {
            AsigP = GAsignaturas.getArrayAsignaProf().get(a);
            for (int b = 0; b < GAsignaturas.getArrayAsignaturas().size(); b++) {
                Asig = GAsignaturas.getArrayAsignaturas().get(b);
                if (Nombre != Asig.getNombre() && AsigP.getCodAsignatura().equalsIgnoreCase(Asig.getCodigo())) {
                    IMaterias.getCboAsignaturas().addItem(Asig.getNombre());
                    Nombre = Asig.getNombre();
                }
            }
        }
    }

    public void CargarComboboxPA(String Materia) {
        String Cod = this.GetCodMate(Materia);
        String IDProf = "";
        AsignaProf AsigP;
        Profesor Prof;
        IMaterias.getCboProfes().removeAllItems();
        IMaterias.getCboProfes().addItem("---Seleccione un Profesor---");
        for (int b = 0; b < GAsignaturas.getArrayAsignaProf().size(); b++) {
            AsigP = GAsignaturas.getArrayAsignaProf().get(b);
            if (AsigP.getCodAsignatura().equalsIgnoreCase(Cod)) {
                IDProf = AsigP.getCodProfesor();
                for (int C = 0; C < GProfesor.getArrayProfesores().size(); C++) {
                    Prof = GProfesor.getArrayProfesores().get(C);
                    if (Prof.getIdentificacion().equalsIgnoreCase(IDProf)) {
                        IMaterias.getCboProfes().addItem(Prof.getNombre());
                    }
                }
            }
        }
    }

    public String GetCodMate(String Materia) {
        Asignatura Asig;
        String Cod = null;
        for (int a = 0; a < GAsignaturas.getArrayAsignaturas().size(); a++) {
            Asig = GAsignaturas.getArrayAsignaturas().get(a);
            if (Asig.getNombre().equalsIgnoreCase(Materia)) {
                Cod = Asig.getCodigo();
                break;
            }
        }
        return Cod;
    }

    public String GetCodProfe(String Profesor) {
        Profesor Prof;
        String Cod = null;
        for (int a = 0; a < GProfesor.getArrayProfesores().size(); a++) {
            Prof = GProfesor.getArrayProfesores().get(a);
            if (Prof.getNombre().equalsIgnoreCase(Profesor)) {
                Cod = Prof.getIdentificacion();
                break;
            }
        }
        return Cod;
    }
}
