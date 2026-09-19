package dam.accesodatos.bloque4;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class ConsultarPersonasObjectDB {

    public static void main(String[] args) {

        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {

            emf = Persistence.createEntityManagerFactory(
                    "data/objectdb/personas.odb"
            );

            em = emf.createEntityManager();

            TypedQuery<PersonaObjeto> consulta =
                    em.createQuery(
                            "SELECT p FROM PersonaObjeto p",
                            PersonaObjeto.class
                    );

            List<PersonaObjeto> personas =
                    consulta.getResultList();

            System.out.println(
                    "Personas almacenadas:"
            );

            for (PersonaObjeto persona : personas) {

                System.out.println(
                        persona
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Error al consultar ObjectDB."
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