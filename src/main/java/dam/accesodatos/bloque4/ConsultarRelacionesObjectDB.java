package dam.accesodatos.bloque4;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class ConsultarRelacionesObjectDB {

    public static void main(String[] args) {

        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {

            emf = Persistence.createEntityManagerFactory(
                    "data/objectdb/personas.odb"
            );

            em = emf.createEntityManager();

            TypedQuery<EmpleadoObjeto> consulta =
                    em.createQuery(
                            "SELECT e FROM EmpleadoObjeto e",
                            EmpleadoObjeto.class
                    );

            List<EmpleadoObjeto> empleados =
                    consulta.getResultList();

            System.out.println(
                    "Empleados almacenados:"
            );

            for (EmpleadoObjeto empleado : empleados) {

                System.out.println(
                        empleado.getNombre()
                                + " -> "
                                + empleado.getDepartamento().getNombre()
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Error al consultar relaciones."
            );

            System.out.println(
                    e.getMessage()
            );

        } finally {

            if (em != null && em.isOpen()) {
                em.close();
            }

            if (emf != null && emf.isOpen()) {
                emf.close();
            }
        }
    }
}