package dam.accesodatos.bloque3;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ConsultaJoinFetch {

    public static void main(String[] args) {

        SessionFactory sessionFactory =
                HibernateUtil.getSessionFactory();

        try (
                Session session =
                        sessionFactory.openSession()
        ) {

            CursoEntidad curso =
                    session.createQuery(
                            "SELECT DISTINCT c " +
                            "FROM CursoEntidad c " +
                            "LEFT JOIN FETCH c.alumnos " +
                            "WHERE c.id = :id",
                            CursoEntidad.class
                    )
                    .setParameter(
                            "id",
                            1
                    )
                    .getSingleResult();

            System.out.println(
                    "Curso: " + curso
            );

            System.out.println(
                    "=== ALUMNOS ==="
            );

            for (
                    AlumnoCursoEntidad alumno
                    : curso.getAlumnos()
            ) {
                System.out.println(alumno);
            }

        } catch (Exception e) {

            System.out.println(
                    "Error en JOIN FETCH."
            );

            System.out.println(
                    e.getMessage()
            );

        } finally {

            sessionFactory.close();
        }
    }
}