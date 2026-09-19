package dam.accesodatos.bloque3;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ConsultaHQLMultiple {

    public static void main(String[] args) {

        SessionFactory sessionFactory =
                HibernateUtil.getSessionFactory();

        try (
                Session session =
                        sessionFactory.openSession()
        ) {

            String cicloBuscado = "DAM";
            int edadMinima = 24;

            List<AlumnoEntidad> alumnos =
                    session.createQuery(
                            "FROM AlumnoEntidad " +
                            "WHERE ciclo = :ciclo " +
                            "AND edad >= :edad " +
                            "ORDER BY edad",
                            AlumnoEntidad.class
                    )
                    .setParameter(
                            "ciclo",
                            cicloBuscado
                    )
                    .setParameter(
                            "edad",
                            edadMinima
                    )
                    .getResultList();

            System.out.println(
                    "=== DAM CON EDAD >= "
                            + edadMinima
                            + " ==="
            );

            for (AlumnoEntidad alumno : alumnos) {
                System.out.println(alumno);
            }

        } catch (Exception e) {

            System.out.println(
                    "Error en consulta HQL."
            );

            System.out.println(
                    e.getMessage()
            );

        } finally {

            sessionFactory.close();
        }
    }
}