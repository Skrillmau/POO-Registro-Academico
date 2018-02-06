package Modelo;

/**
 *
 * @author mate_
 */
public class Estudiante {

    private String Nombre;
    private String Codigo;
    private String Apellido;
    private String Telefono;
    private String Carrera;
    private String Direccion;
    private String Correo;
    private String Contraseña;
    private String Foto;

    Estudiante(String Codigo, String Nombre, String Apellido, String Telefono, String Carrera,
            String Direccion, String Correo, String Contraseña,String Foto) {
        this.Nombre = Nombre;
        this.Codigo = Codigo;
        this.Apellido = Apellido;
        this.Telefono = Telefono;
        this.Carrera = Carrera;
        this.Direccion = Direccion;
        this.Correo = Correo;
        this.Contraseña = Contraseña;
        this.Foto = Foto;
    }
    Estudiante(){
        
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String Foto) {
        this.Foto = Foto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getCarrera() {
        return Carrera;
    }

    public void setCarrera(String Carrera) {
        this.Carrera = Carrera;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }

    @Override
    public String toString() {
        return  Codigo +","+Nombre + "," + Apellido + "," + Telefono + "," + Carrera + ","
                + Direccion + "," + Correo + "," + Contraseña+","+Foto ;
    }

}
