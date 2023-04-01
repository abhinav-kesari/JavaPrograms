import java.io.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) throws SAXException, IOException, TransformerException, ParserConfigurationException {
        File inputFile = new File("C:\\Users\\akesari\\Downloads\\input.xml");
        File outputFile = new File("C:\\Users\\akesari\\Downloads\\output.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Document document = builder.parse(inputFile);
        NodeList nodeList = document.getElementsByTagName("TRIGGER_RULE");
        ArrayList<String> outputList = new ArrayList<>();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);

          /*  for (int j = 0; j < element.getAttributes().getLength(); j++) {
                Element attr = (Element) element.getAttributes().item(j);
                String attrName = attr.getNodeName();
                String attrValue = attr.getNodeValue();
                element.removeAttribute(attrName);
                element.setAttribute(attrName.toLowerCase(), attrValue);
            }*/

            String id = element.getAttribute("ID");
            String triggerType = element.getAttribute("TRIGGER_TYPE");
            String dataPointIdentifier = element.getAttribute("DATAPOINT_IDENTIFIER");
            String fireOperator = element.getAttribute("FIRE_OPERATOR");
            String fireLimit = element.getAttribute("FIRE_LIMIT");
            String resetOperator = element.getAttribute("RESET_OPERATOR");
            String resetLimit = element.getAttribute("RESET_LIMIT");
            String changedBy = element.getAttribute("CHANGED_BY");
            String changedAt = element.getAttribute("CHANGED_AT");

            String output = String.format("<TRIGGER_RULE id=\"%s\" trigger_type=\"%s\" datapoint_identifier=\"%s\" fire_operator=\"%s\" fire_limit=\"%s\" reset_operator=\"%s\" reset_limit=\"%s\" changed_by=\"%s\" changed_at=\"%s\"/>",
                    id, triggerType, dataPointIdentifier, fireOperator, fireLimit, resetOperator, resetLimit, changedBy, changedAt);
            outputList.add(output);
        }
        for (String s : outputList) {
            System.out.println(s);
        }
        // Your code to generate the outputList goes here...
        try {
            PrintWriter writer = new PrintWriter(outputFile);
            // Write the XML header
            writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            // Write the root element
            writer.println("<dataset>");
            // Write each trigger rule element
            for (String s : outputList) {
                writer.println(s);
            }
            // Close the root element
            writer.println("</dataset>");
            writer.close();
            System.out.println("Output written to output.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Done!");
    }
}
