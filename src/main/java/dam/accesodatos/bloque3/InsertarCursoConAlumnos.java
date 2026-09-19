package dam.accesodatos.bloque3;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class InsertarCursoConAlumnos {

    public static void main(String[] args) {

        SessionFactory sessionFactory =
                HibernateUtil.getSessionFactory();

        Transaction transaccion = null;

        try (
                Session session =
                        sessionFactory.openSession()
        ) {

            transaccion =
                    session.beginTransaction();

            CursoEntidad curso =
                    new CursoEntidad("DAM");

            AlumnoCursoEntidad alumno1 =
                    new AlumnoCursoEntidad(
                            "Lucia",
                            22
                    );

            AlumnoCursoEntidad alumno2 =
                    new AlumnoCursoEntidad(
                            "Mario",
                            26
                    );

            curso.agregarAlumno(alumno1);
            curso.agregarAlumno(alumno2);

            session.persist(curso);

            transaccion.commit();

            System.out.println(
                    "Curso guardado correctamente."
            );

            System.out.println(
                    "Curso: "
                            + curso
            );

            for (
                    AlumnoCursoEntidad alumno
                    : curso.getAlumnos()
            ) {

                System.out.println(
                        "Alumno: "
                                + alumno
                );
            }

        } catch (Exception e) {

            if (transaccion != null) {
                transaccion.rollback();
            }

            System.out.println(
                    "Error al guardar curso."
            );

            System.out.println(
                    e.getMessage()
            );

        } finally {

            sessionFactory.close();
        }
    }
}