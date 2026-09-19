package dam.accesodatos.bloque3;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ConsultarAlumnosHibernate {

    public static void main(String[] args) {

        SessionFactory sessionFactory =
                HibernateUtil.getSessionFactory();

        try (
                Session session =
                        sessionFactory.openSession()
        ) {

            List<AlumnoEntidad> alumnos =
                    session
                            .createQuery(
                                    "FROM AlumnoEntidad",
                                    AlumnoEntidad.class
                            )
                            .getResultList();

            System.out.println(
                    "=== ALUMNOS ==="
            );

            for (AlumnoEntidad alumno : alumnos) {
                System.out.println(alumno);
            }

        } catch (Exception e) {

            System.out.println(
                    "Error al consultar alumnos."
            );

            System.out.println(
                    e.getMessage()
            );

        } finally {

            sessionFactory.close();
        }
    }
}