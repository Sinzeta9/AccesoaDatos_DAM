package dam.accesodatos.bloque1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;

public class SerializacionObjetos {

    public static void main(String[] args) {

        Path archivo = Path.of("data", "persona.dat");

        Persona personaOriginal = new Persona("Ana", 30);

        try {

            try (ObjectOutputStream salida =
                         new ObjectOutputStream(
                                 new FileOutputStream(archivo.toFile()))) {

                salida.writeObject(personaOriginal);
            }

            System.out.println("Objeto guardado correctamente.");

            try (ObjectInputStream entrada =
                         new ObjectInputStream(
                                 new FileInputStream(archivo.toFile()))) {

                Persona personaRecuperada =
                        (Persona) entrada.readObject();

                System.out.println();
                System.out.println("Objeto recuperado:");
                System.out.println(personaRecuperada);
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error trabajando con el objeto.");
            System.out.println(e.getMessage());
        }
    }
}