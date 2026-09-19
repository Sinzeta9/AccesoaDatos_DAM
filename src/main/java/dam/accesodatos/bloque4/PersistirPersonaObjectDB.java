package dam.accesodatos.bloque4;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PersistirPersonaObjectDB {

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
                    new PersonaObjeto(
                            "Laura",
                            25
                    );

            em.persist(persona);

            em.getTransaction().commit();

            System.out.println(
                    "Objeto almacenado correctamente."
            );

            System.out.println(
                    persona
            );

        } catch (Exception e) {

            System.out.println(
                    "Error al almacenar el objeto."
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