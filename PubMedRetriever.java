import java.io.File;
import java.io.IOException;
import java.io.InputStream;
/* import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller; */

/* //import xml parser
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

//import DOM API
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException; */

//imports for PubMed API
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class PubMedRetriever {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException{
        
    /*
    * Load and parse XML file
    */
    File xmlFile = new File("4020a1-datasets.xml");
    
    //create Parse object
    Parse parser = new Parse(xmlFile);
    //Output all of the Article Titles
    for (String titles : parser.getTitles()) {
        System.out.println(titles);
    }

    /* //create a document builder that parses the XML file
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.parse(xmlFile);
    doc.getDocumentElement().normalize();

    // Get the elements with the tag name "ArticleTitle"
    NodeList articleTitleNodes = doc.getElementsByTagName("ArticleTitle");
    for (int i = 0; i < articleTitleNodes.getLength(); i++) { //limited the length of nodesList to access to 500
        Element articleTitleElement = (Element) articleTitleNodes.item(i);
        //print the text content of each element
        System.out.println("Article Title: " + articleTitleElement.getTextContent());
    } */

    /*
     * Send Requests to PubMed server API
     */

     /*
      * Using an example URL using the pubmed server api provided
      * Database query parameters: db=pubmed;
      * Search term: term=cancer;
      */
     String base = "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/"; //esearch.fcgi?db=pubmed&term=cancer&retmax=10"; 
     /*
      * customize the cgi language to access the desired reuqests
      */
     String term = "John";     //This is the query that will be searched up in the database (ex. Cancer, will search for cancer related articles)
     String url = base + "esearch.fcgi?db=pubmed&term=" + term + "&mindate=2000/07/01&maxdate=2000/07/01&datetype=pdat&retmax=10000&usehistory=y";
     URL obj = new URL(url);
     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
     con.setRequestMethod("GET");

     /* I don't think this line is necessary
     int responseCode = con.getResponseCode();
     System.out.println("Response Code : " + responseCode); 
     */
     BufferedReader in = new BufferedReader(
         new InputStreamReader(con.getInputStream()));
     String inputLine;
     StringBuffer response = new StringBuffer();
     while ((inputLine = in.readLine()) != null) {
       response.append(inputLine);
     }
     in.close();
     System.out.println(response.toString());

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
