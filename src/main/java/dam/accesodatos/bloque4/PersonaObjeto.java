package dam.accesodatos.bloque4;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class PersonaObjeto {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private int edad;

    public PersonaObjeto() {
    }

    public PersonaObjeto(
            String nombre,
            int edad
    ) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "PersonaObjeto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                '}';
    }
}