package dam.accesodatos.bloque3;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class PruebaHibernateConexion {

    public static void main(String[] args) {

        SessionFactory sessionFactory =
                HibernateUtil.getSessionFactory();

        try (
                Session session =
                        sessionFactory.openSession()
        ) {

            System.out.println(
                    "Conexion Hibernate correcta."
            );

            System.out.println(
                    "Sesion abierta: "
                            + session.isOpen()
            );

        } catch (Exception e) {

            System.out.println(
                    "Error al conectar con Hibernate."
            );

            System.out.println(
                    e.getMessage()
            );

        } finally {

            sessionFactory.close();

            System.out.println(
                    "SessionFactory cerrada."
            );
        }
    }
}