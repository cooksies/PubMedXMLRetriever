import java.io.InputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class PubMedRetriever {
    public static void main(String[] args) {
        
        //load and parse XML file
    File xmlFile = new File("4020a1-datasets.xml");
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.parse(xmlFile);
    Element root = doc.getDocumentElement().normalize();

    // Get the elements with the tag name "ArticleTitle"
    NodeList articleTitleNodes = doc.getElementsByTagName("ArticleTitle");
    for (int i = 0; i < articleTitleNodes.getLength(); i++) {
        Element articleTitleElement = (Element) articleTitleNodes.item(i);
        System.out.println("Article Title: " + articleTitleElement.getTextContent());
    }
    /* //Send the HTTP GET request to retrieve the XML data
    String url = "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/";
    InputStream input = new URL(url).openStream();

    //unmarshal the XML data into JAXB
    JAXBContext jaxbContext = JAXBContext.newInstance(PubmedArticleSet.class);
    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    PubmedArticleSet articleSet = (PubmedArticleSet) jaxbUnmarshaller.unmarshal(input);
            // Process the data as desired
        for (PubmedArticleSet article : articleSet.getPubmedArticle()){
            System.out.println(articl.getMedlineCitation().getArticle().getArticleTitle());
        } */
    
    
    }
    
}
