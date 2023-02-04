import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//import xml parser
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

//import DOM API
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class Parse {

    ArrayList<String> Titles = new ArrayList<>();

    public Parse(File xmlFile) throws ParserConfigurationException, SAXException, IOException {
        //create a document builder that parses the XML file
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.parse(xmlFile);
    doc.getDocumentElement().normalize();

    // Get the elements with the tag name "ArticleTitle"
    NodeList articleTitleNodes = doc.getElementsByTagName("ArticleTitle");
    for (int i = 0; i < articleTitleNodes.getLength(); i++) { 
        Element articleTitleElement = (Element) articleTitleNodes.item(i);
        //print the text content of each element
        //System.out.println("Article Title: " + articleTitleElement.getTextContent());
        //Save the title in the arrayList
        Titles.add(articleTitleElement.getTextContent());
    }
    }

    public ArrayList<String> getTitles() {
        return Titles;
    }

}
