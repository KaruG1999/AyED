package tp1.ejercicio3;

public class Estudiante {

    private String nombre;
    private String apellido;
    private String comision;
    private String email;
    private String direccion;

    public Estudiante (){
        
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

     public void setApellido(String apellido) {
        this.apellido = apellido;
    }

     public void setComision(String comision) {
        this.comision = comision;
    }

     public void setEmail(String email) {
        this.email = email;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return this.nombre;
    }

     public String getApellido() {
        return this.apellido ;
    }

     public String getComision() {
        return this.comision ;
    }

     public String getEmail() {
        return this.email;
    }

    public String getDireccion() {
        return this.direccion ;
    }

    public String tusDatos() {
        // retornar string con todos los datos usando los getters
        return "Nombre: "+ getNombre() + "Apellido: " + getApellido() + "Comision: " + getComision()  + "Email: " + getEmail() + "Direccion: " + getDireccion();
    }
}
