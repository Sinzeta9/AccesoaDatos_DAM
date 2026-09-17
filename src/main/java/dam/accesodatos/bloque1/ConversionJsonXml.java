package dam.accesodatos.bloque1;

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

public class ConversionJsonXml {

    public static void main(String[] args) {

        Path archivoJson = Path.of("data", "alumno.json");
        Path archivoXml = Path.of("data", "alumnoConvertido.xml");

        try {

            // 1. Leer JSON y convertirlo en objeto Java
            ObjectMapper mapper = new ObjectMapper();

            AlumnoJson alumno =
                    mapper.readValue(
                            archivoJson.toFile(),
                            AlumnoJson.class
                    );

            System.out.println("JSON leido correctamente.");
            System.out.println(alumno);

            // 2. Crear documento XML
            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();

            DocumentBuilder builder =
                    factory.newDocumentBuilder();

            Document documento =
                    builder.newDocument();

            Element raiz =
                    documento.createElement("alumno");

            documento.appendChild(raiz);

            Element nombre =
                    documento.createElement("nombre");
            nombre.setTextContent(alumno.getNombre());
            raiz.appendChild(nombre);

            Element edad =
                    documento.createElement("edad");
            edad.setTextContent(
                    String.valueOf(alumno.getEdad())
            );
            raiz.appendChild(edad);

            Element ciclo =
                    documento.createElement("ciclo");
            ciclo.setTextContent(alumno.getCiclo());
            raiz.appendChild(ciclo);

            // 3. Guardar XML
            Transformer transformer =
                    TransformerFactory
                            .newInstance()
                            .newTransformer();

            transformer.setOutputProperty(
                    OutputKeys.INDENT,
                    "yes"
            );

            transformer.transform(
                    new DOMSource(documento),
                    new StreamResult(archivoXml.toFile())
            );

            System.out.println();
            System.out.println("Conversion JSON -> XML completada.");

        } catch (Exception e) {
            System.out.println("Error en la conversion.");
            System.out.println(e.getMessage());
        }
    }
}