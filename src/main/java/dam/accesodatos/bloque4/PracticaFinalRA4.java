package dam.accesodatos.bloque4;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class PracticaFinalRA4 {

    public static void main(String[] args) {

        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {

            emf = Persistence.createEntityManagerFactory(
                    "data/objectdb/personas.odb"
            );

            em = emf.createEntityManager();

            // =========================
            // CREAR DATOS
            // =========================

            em.getTransaction().begin();

            DepartamentoObjeto sistemas =
                    new DepartamentoObjeto(
                            "Sistemas"
                    );

            EmpleadoObjeto maria =
                    new EmpleadoObjeto(
                            "Maria",
                            sistemas
                    );

            EmpleadoObjeto pedro =
                    new EmpleadoObjeto(
                            "Pedro",
                            sistemas
                    );

            em.persist(sistemas);
            em.persist(maria);
            em.persist(pedro);

            em.getTransaction().commit();

            System.out.println(
                    "Objetos guardados correctamente."
            );

            // =========================
            // CONSULTAR DATOS
            // =========================

            TypedQuery<EmpleadoObjeto> consulta =
                    em.createQuery(
                            "SELECT e FROM EmpleadoObjeto e " +
                            "ORDER BY e.nombre",
                            EmpleadoObjeto.class
                    );

            List<EmpleadoObjeto> empleados =
                    consulta.getResultList();

            System.out.println();
            System.out.println(
                    "Empleados almacenados:"
            );

            for (EmpleadoObjeto empleado : empleados) {

                System.out.println(
                        empleado.getNombre()
                                + " -> "
                                + empleado
                                    .getDepartamento()
                                    .getNombre()
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Error en la practica final RA4."
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