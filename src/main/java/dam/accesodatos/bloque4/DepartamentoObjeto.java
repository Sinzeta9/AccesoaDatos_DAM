package dam.accesodatos.bloque4;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class DepartamentoObjeto {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    public DepartamentoObjeto() {
    }

    public DepartamentoObjeto(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "DepartamentoObjeto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}