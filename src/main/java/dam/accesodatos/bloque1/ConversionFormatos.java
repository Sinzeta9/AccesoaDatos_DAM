package dam.accesodatos.bloque1;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.nio.file.Path;

public class ConversionFormatos {

    public static void main(String[] args) {

        // Archivo JSON de origen.
        Path archivoJson = Path.of("data", "alumno.json");

        // Archivo XML que vamos a generar.
        Path archivoXml = Path.of("data", "alumno_convertido.xml");

        try {

            /*
             * ObjectMapper pertenece a Jackson.
             *
             * Nos permite leer y trabajar con información JSON
             * desde Java.
             */
            ObjectMapper mapper = new ObjectMapper();

            /*
             * Leemos alumno.json.
             *
             * JsonNode representa la estructura completa
             * del documento JSON.
             */
            JsonNode alumnoJson =
                    mapper.readTree(archivoJson.toFile());

            /*
             * Recuperamos cada dato del JSON.
             */
            String nombre =
                    alumnoJson.get("nombre").asText();

            int edad =
                    alumnoJson.get("edad").asInt();

            String ciclo =
                    alumnoJson.get("ciclo").asText();

            /*
             * Creamos un nuevo documento XML.
             */
            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();

            DocumentBuilder builder =
                    factory.newDocumentBuilder();

            Document documento =
                    builder.newDocument();

            /*
             * Creamos el elemento raíz:
             *
             * <alumno>
             */
            Element alumno =
                    documento.createElement("alumno");

            documento.appendChild(alumno);

            /*
             * Creamos:
             *
             * <nombre>Laura</nombre>
             */
            Element elementoNombre =
                    documento.createElement("nombre");

            elementoNombre.setTextContent(nombre);

            alumno.appendChild(elementoNombre);

            /*
             * Creamos:
             *
             * <edad>25</edad>
             */
            Element elementoEdad =
                    documento.createElement("edad");

            elementoEdad.setTextContent(
                    String.valueOf(edad)
            );

            alumno.appendChild(elementoEdad);

            /*
             * Creamos:
             *
             * <ciclo>DAM</ciclo>
             */
            Element elementoCiclo =
                    documento.createElement("ciclo");

            elementoCiclo.setTextContent(ciclo);

            alumno.appendChild(elementoCiclo);

            /*
             * Transformer convierte el árbol DOM
             * en un archivo XML real.
             */
            TransformerFactory transformerFactory =
                    TransformerFactory.newInstance();

            Transformer transformer =
                    transformerFactory.newTransformer();

            transformer.setOutputProperty(
                    OutputKeys.INDENT,
                    "yes"
            );

            /*
             * Guardamos el XML generado.
             */
            transformer.transform(
                    new DOMSource(documento),
                    new StreamResult(archivoXml.toFile())
            );

            System.out.println(
                    "Conversion JSON -> XML realizada correctamente."
            );

            System.out.println();
            System.out.println("Datos convertidos:");

            System.out.println(
                    "Nombre: " + nombre
            );

            System.out.println(
                    "Edad: " + edad
            );

            System.out.println(
                    "Ciclo: " + ciclo
            );

            System.out.println();
            System.out.println(
                    "Archivo creado: " + archivoXml
            );

        } catch (Exception e) {

            System.out.println(
                    "Error durante la conversion."
            );

            System.out.println(
                    e.getMessage()
            );
        }
    }
}