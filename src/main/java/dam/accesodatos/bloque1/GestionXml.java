package dam.accesodatos.bloque1;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.nio.file.Path;

public class GestionXml {

    public static void main(String[] args) {

        Path archivo = Path.of("data", "alumno.xml");

        try {

            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();

            DocumentBuilder builder =
                    factory.newDocumentBuilder();

            Document documento =
                    builder.newDocument();

            Element alumno =
                    documento.createElement("alumno");

            documento.appendChild(alumno);

            Element nombre =
                    documento.createElement("nombre");
            nombre.setTextContent("Laura");
            alumno.appendChild(nombre);

            Element edad =
                    documento.createElement("edad");
            edad.setTextContent("25");
            alumno.appendChild(edad);

            Element ciclo =
                    documento.createElement("ciclo");
            ciclo.setTextContent("DAM");
            alumno.appendChild(ciclo);

            TransformerFactory transformerFactory =
                    TransformerFactory.newInstance();

            Transformer transformer =
                    transformerFactory.newTransformer();

            transformer.setOutputProperty(
                    OutputKeys.INDENT,
                    "yes"
            );

            transformer.transform(
                    new DOMSource(documento),
                    new StreamResult(archivo.toFile())
            );

            System.out.println("XML creado correctamente.");

            Document documentoLeido =
                    builder.parse(archivo.toFile());

            NodeList nombres =
                    documentoLeido.getElementsByTagName("nombre");

            NodeList edades =
                    documentoLeido.getElementsByTagName("edad");

            NodeList ciclos =
                    documentoLeido.getElementsByTagName("ciclo");

            System.out.println();
            System.out.println("Datos recuperados del XML:");
            System.out.println("Nombre: " +
                    nombres.item(0).getTextContent());
            System.out.println("Edad: " +
                    edades.item(0).getTextContent());
            System.out.println("Ciclo: " +
                    ciclos.item(0).getTextContent());

        } catch (Exception e) {
            System.out.println("Error trabajando con XML.");
            System.out.println(e.getMessage());
        }
    }
}