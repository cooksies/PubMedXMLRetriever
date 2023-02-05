//john's api_key: cb2badcb50c6d4bb46975489b9825682cb08 
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

//import xml parser
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

//import DOM API
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
     String term = "john+snow+and+modern-day+environmental+epidemiology.";     //This is the query that will be searched up in the database (ex. Cancer, will search for cancer related articles)
     String url = base + "esearch.fcgi?db=pubmed&term=" + term + "[Title]&retmode=xml";
     URL obj = new URL(url);
     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
     con.setRequestMethod("GET");
     BufferedReader in = new BufferedReader(
         new InputStreamReader(con.getInputStream()));
     String inputLine;
     StringBuffer response = new StringBuffer();
     while ((inputLine = in.readLine()) != null) {
       response.append(inputLine);
     }
     in.close();
     System.out.println(response.toString());    
    
    }
    
}
