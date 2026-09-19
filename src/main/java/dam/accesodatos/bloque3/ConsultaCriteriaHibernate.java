package dam.accesodatos.bloque3;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class ConsultaCriteriaHibernate {

    public static void main(String[] args) {

        SessionFactory sessionFactory =
                HibernateUtil.getSessionFactory();

        try (
                Session session =
                        sessionFactory.openSession()
        ) {

            /*
             * Constructor de consultas Criteria.
             */
            CriteriaBuilder cb =
                    session.getCriteriaBuilder();

            /*
             * Indicamos qué tipo de resultado
             * queremos obtener.
             */
            CriteriaQuery<AlumnoCursoEntidad> consulta =
                    cb.createQuery(
                            AlumnoCursoEntidad.class
                    );

            /*
             * Equivale al FROM de HQL/SQL.
             */
            Root<AlumnoCursoEntidad> alumno =
                    consulta.from(
                            AlumnoCursoEntidad.class
                    );

            /*
             * SELECT alumno
             * WHERE edad >= 23
             * ORDER BY edad
             */
            consulta.select(alumno)
                    .where(
                            cb.greaterThanOrEqualTo(
                                    alumno.get("edad"),
                                    23
                            )
                    )
                    .orderBy(
                            cb.asc(
                                    alumno.get("edad")
                            )
                    );

            List<AlumnoCursoEntidad> alumnos =
                    session.createQuery(
                            consulta
                    )
                    .getResultList();

            System.out.println(
                    "=== ALUMNOS EDAD >= 23 ==="
            );

            for (
                    AlumnoCursoEntidad a
                    : alumnos
            ) {

                System.out.println(a);
            }

        } catch (Exception e) {

            System.out.println(
                    "Error en consulta Criteria."
            );

            System.out.println(
                    e.getMessage()
            );

        } finally {

            sessionFactory.close();
        }
    }
}