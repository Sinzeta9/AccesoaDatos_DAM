package dam.accesodatos.bloque4;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class EmpleadoObjeto {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    @ManyToOne
    private DepartamentoObjeto departamento;

    public EmpleadoObjeto() {
    }

    public EmpleadoObjeto(
            String nombre,
            DepartamentoObjeto departamento
    ) {
        this.nombre = nombre;
        this.departamento = departamento;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public DepartamentoObjeto getDepartamento() {
        return departamento;
    }

    @Override
    public String toString() {
        return "EmpleadoObjeto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", departamento=" + departamento.getNombre() +
                '}';
    }
}