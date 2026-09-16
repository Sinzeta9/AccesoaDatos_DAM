package dam.accesodatos.bloque1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class LecturaEscrituraTexto {

    public static void main(String[] args) {

        Path archivo = Path.of("data", "primerArchivo.txt");

        String contenido = """
                Primer fichero de Acceso a Datos
                Curso: DAM
                Bloque 1: Gestion de ficheros
                Java: 21
                """;

        try {

            Files.writeString(
                    archivo,
                    contenido,
                    StandardCharsets.UTF_8
            );

            System.out.println("Contenido escrito correctamente.");

            String contenidoLeido = Files.readString(
                    archivo,
                    StandardCharsets.UTF_8
            );

            System.out.println();
            System.out.println("Contenido recuperado del fichero:");
            System.out.println("--------------------------------");
            System.out.println(contenidoLeido);

        } catch (IOException e) {
            System.out.println("Error trabajando con el fichero.");
            System.out.println(e.getMessage());
        }
    }
}