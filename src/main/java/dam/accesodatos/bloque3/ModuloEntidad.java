package dam.accesodatos.bloque3;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "modulos")
public class ModuloEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    @ManyToMany(mappedBy = "modulos")
    private List<AlumnoCursoEntidad> alumnos =
            new ArrayList<>();

    public ModuloEntidad() {
    }

    public ModuloEntidad(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<AlumnoCursoEntidad> getAlumnos() {
        return alumnos;
    }

    @Override
    public String toString() {
        return id + " - " + nombre;
    }
}