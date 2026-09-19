package dam.accesodatos.bloque3;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "alumnos_curso")
public class AlumnoCursoEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    private Integer edad;

    @ManyToOne
    @JoinColumn(
            name = "curso_id",
            nullable = false
    )
    private CursoEntidad curso;

public List<ModuloEntidad> getModulos() {
    return modulos;
}

public void agregarModulo(
        ModuloEntidad modulo
) {

    modulos.add(modulo);
    modulo.getAlumnos().add(this);
}

@ManyToMany
@JoinTable(
        name = "alumno_modulo",

        joinColumns = @JoinColumn(
                name = "alumno_id"
        ),

        inverseJoinColumns = @JoinColumn(
                name = "modulo_id"
        )
)
private List<ModuloEntidad> modulos =
        new ArrayList<>();

    public AlumnoCursoEntidad() {
    }

    public AlumnoCursoEntidad(
            String nombre,
            Integer edad
    ) {

        this.nombre = nombre;
        this.edad = edad;
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

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public CursoEntidad getCurso() {
        return curso;
    }

    public void setCurso(
            CursoEntidad curso
    ) {

        this.curso = curso;
    }

    @Override
    public String toString() {

        return id
                + " - "
                + nombre
                + " - "
                + edad;
    }
}