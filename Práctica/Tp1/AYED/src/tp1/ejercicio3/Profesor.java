package tp1.ejercicio3;

public class Profesor {

    private String nombre;
    private String apellido;
    private String email;
    private String catedra;
    private String facultad;

    // TODO: getters y setters
    public String getNombre() { 
        return this.nombre;
    }

     public String getApellido() {
        return this.apellido ;
    }

     public String getEmail() {
        return this.email;
    }

     public String getCatedra() {
        return this.catedra ;
    }

     public String getFacultad() {
        return this.facultad ;
    }

     public void setNombre(String nombre) {
        this.nombre = nombre;
    }

     public void setApellido(String apellido) {
        this.apellido = apellido;
    }

     public void setEmail(String email) {
        this.email = email;
    }

     public void setCatedra(String catedra) {
        this.catedra = catedra;
    }

     public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String tusDatos() {
        //  retornar string con todos los datos usando los getters
        return "Nombre: "+ getNombre() + "Apellido: " + getApellido() + "Email: " + getEmail() + "Catedra: " + getCatedra() + "Facultad: " + getFacultad();
    }
}
