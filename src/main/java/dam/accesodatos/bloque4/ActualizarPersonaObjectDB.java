package dam.accesodatos.bloque4;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ActualizarPersonaObjectDB {

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
                        "Antes: " + persona
                );

                persona.setEdad(26);

                em.getTransaction().commit();

                System.out.println(
                        "Despues: " + persona
                );

            } else {

                System.out.println(
                        "Persona no encontrada."
                );

                em.getTransaction().rollback();
            }

        } catch (Exception e) {

            System.out.println(
                    "Error al actualizar ObjectDB."
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