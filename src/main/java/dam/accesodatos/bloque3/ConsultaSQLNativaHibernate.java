package dam.accesodatos.bloque3;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ConsultaSQLNativaHibernate {

    public static void main(String[] args) {

        SessionFactory sessionFactory =
                HibernateUtil.getSessionFactory();

        try (
                Session session =
                        sessionFactory.openSession()
        ) {

            String sql =
                    """
                    SELECT *
                    FROM alumnos_curso
                    WHERE edad >= :edad
                    ORDER BY edad
                    """;

            List<AlumnoCursoEntidad> alumnos =
                    session.createNativeQuery(
                            sql,
                            AlumnoCursoEntidad.class
                    )
                    .setParameter(
                            "edad",
                            23
                    )
                    .getResultList();

            System.out.println(
                    "=== SQL NATIVO ==="
            );

            for (
                    AlumnoCursoEntidad alumno
                    : alumnos
            ) {

                System.out.println(alumno);
            }

        } catch (Exception e) {

            System.out.println(
                    "Error en consulta SQL nativa."
            );

            System.out.println(
                    e.getMessage()
            );

        } finally {

            sessionFactory.close();
        }
    }
}