package dam.accesodatos.bloque4;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EliminarPersonaObjectDB {

    public static void main(String[] args) {

        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {

            emf = Persistence.createEntityManagerFactory(
                    "data/objectdb/personas.odb"
            );

            em = emf.createEntityManager();

            em.getTransaction().begin();

            PersonaObjeto persona =
                    em.find(
                            PersonaObjeto.class,
                            1L
                    );

            if (persona != null) {

                System.out.println(
                        "Objeto encontrado: " + persona
                );

                em.remove(persona);

                em.getTransaction().commit();

                System.out.println(
                        "Objeto eliminado correctamente."
                );

            } else {

                System.out.println(
                        "Persona no encontrada."
                );

                em.getTransaction().rollback();
            }

        } catch (Exception e) {

            System.out.println(
                    "Error al eliminar en ObjectDB."
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