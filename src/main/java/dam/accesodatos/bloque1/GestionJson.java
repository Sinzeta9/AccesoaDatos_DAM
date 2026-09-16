package dam.accesodatos.bloque1;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;

public class GestionJson {

    public static void main(String[] args) {

        Path archivo = Path.of("data", "alumno.json");

        AlumnoJson alumnoOriginal =
                new AlumnoJson("Laura", 25, "DAM");

        ObjectMapper mapper = new ObjectMapper();

        try {

            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(archivo.toFile(), alumnoOriginal);

            System.out.println("Objeto guardado en JSON.");

            AlumnoJson alumnoRecuperado =
                    mapper.readValue(
                            archivo.toFile(),
                            AlumnoJson.class
                    );

            System.out.println();
            System.out.println("Objeto recuperado:");
            System.out.println(alumnoRecuperado);

        } catch (IOException e) {
            System.out.println("Error trabajando con JSON.");
            System.out.println(e.getMessage());
        }
    }
}