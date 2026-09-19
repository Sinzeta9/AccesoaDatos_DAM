package dam.accesodatos.bloque3;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AgregacionesHQL {

    public static void main(String[] args) {

        SessionFactory sessionFactory =
                HibernateUtil.getSessionFactory();

        try (
                Session session =
                        sessionFactory.openSession()
        ) {

            Long totalAlumnos =
                    session.createQuery(
                            "SELECT COUNT(a) " +
                            "FROM AlumnoEntidad a",
                            Long.class
                    )
                    .getSingleResult();

            Double edadMedia =
                    session.createQuery(
                            "SELECT AVG(a.edad) " +
                            "FROM AlumnoEntidad a",
                            Double.class
                    )
                    .getSingleResult();

            Integer edadMaxima =
                    session.createQuery(
                            "SELECT MAX(a.edad) " +
                            "FROM AlumnoEntidad a",
                            Integer.class
                    )
                    .getSingleResult();

            Integer edadMinima =
                    session.createQuery(
                            "SELECT MIN(a.edad) " +
                            "FROM AlumnoEntidad a",
                            Integer.class
                    )
                    .getSingleResult();

            System.out.println(
                    "Total alumnos: "
                            + totalAlumnos
            );

            System.out.println(
                    "Edad media: "
                            + edadMedia
            );

            System.out.println(
                    "Edad maxima: "
                            + edadMaxima
            );

            System.out.println(
                    "Edad minima: "
                            + edadMinima
            );

        } catch (Exception e) {

            System.out.println(
                    "Error en agregaciones HQL."
            );

            System.out.println(
                    e.getMessage()
            );

        } finally {

            sessionFactory.close();
        }
    }
}