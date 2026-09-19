package dam.accesodatos.bloque3;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class PaginacionHibernate {

    public static void main(String[] args) {

        SessionFactory sessionFactory =
                HibernateUtil.getSessionFactory();

        try (
                Session session =
                        sessionFactory.openSession()
        ) {

            int pagina = 1;
            int tamanoPagina = 2;

            int primerResultado =
                    (pagina - 1) * tamanoPagina;

            List<AlumnoEntidad> alumnos =
                    session.createQuery(
                            "FROM AlumnoEntidad " +
                            "ORDER BY id",
                            AlumnoEntidad.class
                    )
                    .setFirstResult(
                            primerResultado
                    )
                    .setMaxResults(
                            tamanoPagina
                    )
                    .getResultList();

            System.out.println(
                    "=== PAGINA "
                            + pagina
                            + " ==="
            );

            for (AlumnoEntidad alumno : alumnos) {
                System.out.println(alumno);
            }

        } catch (Exception e) {

            System.out.println(
                    "Error en paginacion."
            );

            System.out.println(
                    e.getMessage()
            );

        } finally {

            sessionFactory.close();
        }
    }
}