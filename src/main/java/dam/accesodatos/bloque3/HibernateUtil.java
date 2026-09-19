package dam.accesodatos.bloque3;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory =
            crearSessionFactory();

    private static SessionFactory crearSessionFactory() {

        try {

            String host =
                    System.getenv()
                            .getOrDefault(
                                    "DB_HOST",
                                    "localhost"
                            );

            Configuration configuracion =
                    new Configuration();


	configuracion.addAnnotatedClass(
        	CursoEntidad.class
					);

	configuracion.addAnnotatedClass(
        	AlumnoCursoEntidad.class
					);

            configuracion.setProperty(
                    "hibernate.connection.driver_class",
                    "org.postgresql.Driver"
            );

            configuracion.setProperty(
                    "hibernate.connection.url",
                    "jdbc:postgresql://"
                            + host
                            + ":5433/accesodatos"
            );

            configuracion.setProperty(
                    "hibernate.connection.username",
                    "dam"
            );

            configuracion.setProperty(
                    "hibernate.connection.password",
                    "dam123"
            );

            configuracion.setProperty(
                    "hibernate.hbm2ddl.auto",
                    "validate"
            );

            configuracion.setProperty(
                    "hibernate.show_sql",
                    "true"
            );

            configuracion.setProperty(
                    "hibernate.format_sql",
                    "true"
            );

            return configuracion
                    .buildSessionFactory();

        } catch (Exception e) {

            System.out.println(
                    "Error al crear SessionFactory."
            );

            throw new RuntimeException(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}