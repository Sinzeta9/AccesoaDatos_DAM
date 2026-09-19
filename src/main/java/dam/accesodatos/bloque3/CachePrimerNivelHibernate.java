package dam.accesodatos.bloque3;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CachePrimerNivelHibernate {

    public static void main(String[] args) {

        SessionFactory sessionFactory =
                HibernateUtil.getSessionFactory();

        try (
                Session session =
                        sessionFactory.openSession()
        ) {

            System.out.println(
                    "=== PRIMER FIND ==="
            );

            AlumnoCursoEntidad alumno1 =
                    session.find(
                            AlumnoCursoEntidad.class,
                            1
                    );

            System.out.println(alumno1);

            System.out.println(
                    "\n=== SEGUNDO FIND ==="
            );

            AlumnoCursoEntidad alumno2 =
                    session.find(
                            AlumnoCursoEntidad.class,
                            1
                    );

            System.out.println(alumno2);

            System.out.println(
                    "\n¿Es el mismo objeto?"
            );

            System.out.println(
                    alumno1 == alumno2
            );

            /*
             * Eliminamos las entidades
             * gestionadas de la Session.
             */
            session.clear();

            System.out.println(
                    "\n=== DESPUES DE clear() ==="
            );

            AlumnoCursoEntidad alumno3 =
                    session.find(
                            AlumnoCursoEntidad.class,
                            1
                    );

            System.out.println(alumno3);

            System.out.println(
                    "\n¿Sigue siendo el mismo objeto?"
            );

            System.out.println(
                    alumno1 == alumno3
            );

        } catch (Exception e) {

            System.out.println(
                    "Error en cache de primer nivel."
            );

            System.out.println(
                    e.getMessage()
            );

        } finally {

            sessionFactory.close();
        }
    }
}