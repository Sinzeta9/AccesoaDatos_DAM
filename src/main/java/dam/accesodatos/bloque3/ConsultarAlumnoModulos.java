package dam.accesodatos.bloque3;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ConsultarAlumnoModulos {

    public static void main(String[] args) {

        SessionFactory sessionFactory =
                HibernateUtil.getSessionFactory();

        try (
                Session session =
                        sessionFactory.openSession()
        ) {

            List<AlumnoCursoEntidad> alumnos =
                    session.createQuery(
                            "SELECT DISTINCT a " +
                            "FROM AlumnoCursoEntidad a " +
                            "LEFT JOIN FETCH a.modulos " +
                            "ORDER BY a.id",
                            AlumnoCursoEntidad.class
                    )
                    .getResultList();

            for (
                    AlumnoCursoEntidad alumno
                    : alumnos
            ) {

                System.out.println(
                        "Alumno: "
                                + alumno.getNombre()
                );

                for (
                        ModuloEntidad modulo
                        : alumno.getModulos()
                ) {

                    System.out.println(
                            " - "
                                    + modulo.getNombre()
                    );
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Error al consultar alumnos y modulos."
            );

            System.out.println(
                    e.getMessage()
            );

        } finally {

            sessionFactory.close();
        }
    }
}