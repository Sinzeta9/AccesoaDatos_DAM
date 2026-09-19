package dam.accesodatos.bloque3;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ConsultaHQLParametros {

    public static void main(String[] args) {

        SessionFactory sessionFactory =
                HibernateUtil.getSessionFactory();

        try (
                Session session =
                        sessionFactory.openSession()
        ) {

            String cicloBuscado = "DAM";

            List<AlumnoEntidad> alumnos =
                    session.createQuery(
                            "FROM AlumnoEntidad " +
                            "WHERE ciclo = :ciclo " +
                            "ORDER BY id",
                            AlumnoEntidad.class
                    )
                    .setParameter(
                            "ciclo",
                            cicloBuscado
                    )
                    .getResultList();

            System.out.println(
                    "=== ALUMNOS DE "
                            + cicloBuscado
                            + " ==="
            );

            for (AlumnoEntidad alumno : alumnos) {
                System.out.println(alumno);
            }

        } catch (Exception e) {

            System.out.println(
                    "Error en la consulta HQL."
            );

            System.out.println(
                    e.getMessage()
            );

        } finally {

            sessionFactory.close();
        }
    }
}