package dam.accesodatos.bloque2;

public class Alumno {

    private int id;
    private String nombre;
    private int edad;
    private String ciclo;

    public Alumno() {
    }

    public Alumno(
            int id,
            String nombre,
            int edad,
            String ciclo
    ) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.ciclo = ciclo;
    }

    public Alumno(
            String nombre,
            int edad,
            String ciclo
    ) {
        this.nombre = nombre;
        this.edad = edad;
        this.ciclo = ciclo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return id + " - "
                + nombre + " - "
                + edad + " - "
                + ciclo;
    }
}