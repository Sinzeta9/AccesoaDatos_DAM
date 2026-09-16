package dam.accesodatos.bloque1;
	
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class EjemploPathFiles {

    public static void main(String[] args) {

        Path carpetaDatos = Path.of("data");
        Path archivo = carpetaDatos.resolve("primerArchivo.txt");

        try {

            if (Files.notExists(carpetaDatos)) {
                Files.createDirectories(carpetaDatos);
                System.out.println("Carpeta data creada.");
            }

            if (Files.notExists(archivo)) {
                Files.createFile(archivo);
                System.out.println("Archivo creado: " + archivo);
            } else {
                System.out.println("El archivo ya existe.");
            }

            System.out.println();
            System.out.println("Informacion del archivo:");
            System.out.println("Ruta: " + archivo);
            System.out.println("Ruta absoluta: " + archivo.toAbsolutePath());
            System.out.println("Nombre: " + archivo.getFileName());
            System.out.println("Existe: " + Files.exists(archivo));
            System.out.println("Es archivo: " + Files.isRegularFile(archivo));
            System.out.println("Es directorio: " + Files.isDirectory(archivo));
            System.out.println("Tamano: " + Files.size(archivo) + " bytes");

        } catch (IOException e) {
            System.out.println("Se ha producido un error trabajando con el fichero.");
            System.out.println(e.getMessage());
        }
    }
}