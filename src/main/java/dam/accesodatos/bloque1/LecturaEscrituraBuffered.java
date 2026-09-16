package dam.accesodatos.bloque1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class LecturaEscrituraBuffered {

    public static void main(String[] args) {

        Path archivo = Path.of("data", "buffered.txt");

        try {

            try (BufferedWriter writer =
                         Files.newBufferedWriter(archivo, StandardCharsets.UTF_8)) {

                writer.write("Acceso a Datos");
                writer.newLine();

                writer.write("Bloque 1 - Gestion de ficheros");
                writer.newLine();

                writer.write("Ejemplo con BufferedWriter y BufferedReader");
                writer.newLine();

                writer.write("Java 21");
            }

            System.out.println("Fichero escrito correctamente.");

            System.out.println();
            System.out.println("Contenido del fichero:");
            System.out.println("----------------------");

            try (BufferedReader reader =
                         Files.newBufferedReader(archivo, StandardCharsets.UTF_8)) {

                String linea;

                while ((linea = reader.readLine()) != null) {
                    System.out.println(linea);
                }
            }

        } catch (IOException e) {
            System.out.println("Error trabajando con el fichero.");
            System.out.println(e.getMessage());
        }
    }
}