package dam.accesodatos.bloque3;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cursos")
public class CursoEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    @OneToMany(
            mappedBy = "curso",
            cascade = CascadeType.ALL
    )
    private List<AlumnoCursoEntidad> alumnos =
            new ArrayList<>();

    public CursoEntidad() {
    }

    public CursoEntidad(String nombre) {
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

    public void agregarAlumno(
            AlumnoCursoEntidad alumno
    ) {

        alumnos.add(alumno);

        alumno.setCurso(this);
    }

    @Override
    public String toString() {
        return id + " - " + nombre;
    }
}