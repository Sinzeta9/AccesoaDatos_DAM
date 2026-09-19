package dam.accesodatos.bloque3;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class InsertarAlumnoModulo {

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

            AlumnoCursoEntidad lucia =
                    session.find(
                            AlumnoCursoEntidad.class,
                            1
                    );

            AlumnoCursoEntidad mario =
                    session.find(
                            AlumnoCursoEntidad.class,
                            2
                    );

            ModuloEntidad accesoDatos =
                    new ModuloEntidad(
                            "Acceso a Datos"
                    );

            ModuloEntidad psp =
                    new ModuloEntidad(
                            "PSP"
                    );

            session.persist(accesoDatos);
            session.persist(psp);

            lucia.agregarModulo(
                    accesoDatos
            );

            lucia.agregarModulo(
                    psp
            );

            mario.agregarModulo(
                    accesoDatos
            );

            transaccion.commit();

            System.out.println(
                    "Relaciones guardadas correctamente."
            );

            System.out.println(
                    "Lucia:"
            );

            for (
                    ModuloEntidad modulo
                    : lucia.getModulos()
            ) {
                System.out.println(
                        " - " + modulo
                );
            }

            System.out.println(
                    "Mario:"
            );

            for (
                    ModuloEntidad modulo
                    : mario.getModulos()
            ) {
                System.out.println(
                        " - " + modulo
                );
            }

        } catch (Exception e) {

            if (transaccion != null) {
                transaccion.rollback();
            }

            System.out.println(
                    "Error al guardar relaciones."
            );

            System.out.println(
                    e.getMessage()
            );

        } finally {

            sessionFactory.close();
        }
    }
}