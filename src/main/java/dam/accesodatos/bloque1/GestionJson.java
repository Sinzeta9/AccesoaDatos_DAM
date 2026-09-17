package dam.accesodatos.bloque1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GestionJson {

    public static void main(String[] args) {

        Path archivo = Path.of("data", "alumno.json");

        String json = """
                {
                    "nombre": "Laura",
                    "edad": 25,
                    "ciclo": "DAM"
                }
                """;

        try {

            Files.createDirectories(archivo.getParent());

            Files.writeString(archivo, json);

            System.out.println("JSON creado correctamente.");

            String contenido = Files.readString(archivo);

            System.out.println();
            System.out.println("Contenido recuperado del JSON:");
            System.out.println(contenido);

        } catch (IOException e) {

            System.out.println("Error trabajando con JSON.");
            System.out.println(e.getMessage());
        }
    }
}