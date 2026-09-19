package dam.accesodatos.bloque4;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PersistirRelacionObjectDB {

    public static void main(String[] args) {

        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {

            emf = Persistence.createEntityManagerFactory(
                    "data/objectdb/personas.odb"
            );

            em = emf.createEntityManager();

            em.getTransaction().begin();

            DepartamentoObjeto desarrollo =
                    new DepartamentoObjeto(
                            "Desarrollo"
                    );

            EmpleadoObjeto ana =
                    new EmpleadoObjeto(
                            "Ana",
                            desarrollo
                    );

            EmpleadoObjeto carlos =
                    new EmpleadoObjeto(
                            "Carlos",
                            desarrollo
                    );

            em.persist(desarrollo);
            em.persist(ana);
            em.persist(carlos);

            em.getTransaction().commit();

            System.out.println(
                    "Departamento: " + desarrollo
            );

            System.out.println(
                    "Empleado: " + ana
            );

            System.out.println(
                    "Empleado: " + carlos
            );

        } catch (Exception e) {

            System.out.println(
                    "Error al persistir relaciones."
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