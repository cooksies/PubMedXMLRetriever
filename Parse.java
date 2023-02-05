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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class Parse {

    ArrayList<String> Titles = new ArrayList<>();
    ArrayList<String> LastNames = new ArrayList<>();
    ArrayList<String> Initials = new ArrayList<>();
    ArrayList<String> dateCompleted = new ArrayList<>();

    public Parse(File xmlFile) throws ParserConfigurationException, SAXException, IOException {
        //create a document builder that parses the XML file
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.parse(xmlFile);
    doc.getDocumentElement().normalize();

    // Get the elements with the tag name "ArticleTitle"
    NodeList articleTitleNodes = doc.getElementsByTagName("ArticleTitle");
    NodeList authorLastNameNodes = doc.getElementsByTagName("LastName");
    NodeList authorInitialsNodes = doc.getElementsByTagName("Initials");
    Node dCom = doc.getElementsByTagName("DateCompleted").item(0);
    NodeList dateCompleteNodes = dCom.getChildNodes();
    
    for (int i = 0; i < articleTitleNodes.getLength(); i++) { 
        Element articleTitleElement = (Element) articleTitleNodes.item(i);
        Element authorLastNameElement = (Element)authorLastNameNodes.item(i);
        Element authorInitialsElement = (Element)authorInitialsNodes.item(i);
        

        for (int j = 0; j < dateCompleteNodes.getLength(); j++) {
            Node child = dateCompleteNodes.item(j);
            switch (j) {
                case 0:
                    dateReformat.setYear(child.getTextContent());
                    continue;
                case 1:
                    dateReformat.setMonth(child.getTextContent());
                    continue;
                case 2:
                    dateReformat.setDay(child.getTextContent());
                    continue;
                default:
                    break;
            }
            //System.out.println(child.getNodeName() + ": " + child.getTextContent());
          }

        //print the text content of each element
        System.out.println("Article Title: " + articleTitleElement.getTextContent() + "\n" +
        "Author Last Name: " + authorLastNameElement.getTextContent() + "\n" +
        "Author Initials: " + authorInitialsElement.getTextContent() + "\n" +
        "Date Completed: " + dateReformat.getDate());

        //Save the title in the arrayList
        Titles.add(articleTitleElement.getTextContent());
        LastNames.add(authorLastNameElement.getTextContent());
        Initials.add(authorInitialsElement.getTextContent());
        dateCompleted.add(dateReformat.getDate());
    }
    }

    public ArrayList<String> getTitles() {
        return Titles;
    }

}
