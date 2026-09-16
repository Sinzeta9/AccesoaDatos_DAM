package dam.accesodatos.bloque1;

public class AlumnoJson {

    private String nombre;
    private int edad;
    private String ciclo;

    public AlumnoJson() {
    }

    public AlumnoJson(String nombre, int edad, String ciclo) {
        this.nombre = nombre;
        this.edad = edad;
        this.ciclo = ciclo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    @Override
    public String toString() {
        return "AlumnoJson{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", ciclo='" + ciclo + '\'' +
                '}';
    }
}