package dam.accesodatos.bloque1;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;

public class AccesoAleatorio {

    public static void main(String[] args) {

        Path archivo = Path.of("data", "random.dat");

        final int TAMANO_REGISTRO = Integer.BYTES + Double.BYTES;

        try (RandomAccessFile raf =
                     new RandomAccessFile(archivo.toFile(), "rw")) {

            raf.setLength(0);

            raf.writeInt(101);
            raf.writeDouble(1200.50);

            raf.writeInt(102);
            raf.writeDouble(1850.75);

            raf.writeInt(103);
            raf.writeDouble(950.25);

            System.out.println("Tres registros escritos.");

            long posicionSegundoRegistro = TAMANO_REGISTRO;

            raf.seek(posicionSegundoRegistro);

            int id = raf.readInt();
            double saldo = raf.readDouble();

            System.out.println();
            System.out.println("Segundo registro:");
            System.out.println("ID: " + id);
            System.out.println("Saldo: " + saldo);

            raf.seek(posicionSegundoRegistro + Integer.BYTES);
            raf.writeDouble(2500.00);

            raf.seek(posicionSegundoRegistro);

            id = raf.readInt();
            saldo = raf.readDouble();

            System.out.println();
            System.out.println("Segundo registro modificado:");
            System.out.println("ID: " + id);
            System.out.println("Saldo: " + saldo);

        } catch (IOException e) {
            System.out.println("Error trabajando con el fichero.");
            System.out.println(e.getMessage());
        }
    }
}