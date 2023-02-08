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

public class PubMedRetriever {
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
        
        /*
        * Creating a an ArrayList for a dynamic list which will add in the specified element once it is read
        */
        ArrayList<String> authors = new ArrayList<>();
        ArrayList<String> pubDates = new ArrayList<>();
        ArrayList<String> shortTitles = new ArrayList<>();
        ArrayList<String> urlList = new ArrayList<>();
        ArrayList<String> compDates = new ArrayList<>();
        ArrayList<String> TITLES = new ArrayList<>();
        ArrayList<String> PMID = new ArrayList<>();

         //local variables
        String compDate;
        String pubDate;
        String lastName = " ";
        String initials = " ";
        String title;   

            //load and parse XML file
        File xmlFile = new File("4020a1-datasets.xml");
        Parse parser =  new Parse();

        try {
            /*
             * Document Builder is used to parse the xml datasets making it readable for our code
             */
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // Using this nodelist to identify how many entries we are working with using .getLength()
            NodeList list = doc.getElementsByTagName("PubmedArticle");
            /*
             * Create an array that will go through all the entries
             * List.getLength() is used to determine the amount of loops
             */
            for (int i = 0; i < list.getLength(); i++) {
                boolean check1= false, check2 = false;

                Element element = (Element)list.item(i);

                // Add the read titles into our TITLES ArrayList
                title = element.getElementsByTagName("ArticleTitle").item(0).getTextContent();
                TITLES.add(title);
                /*
                 * With the given titles, PubMed queries max out at a certain word count
                 * We will only be obtaining the first two words in the title
                 */
                String space="%20";
                String delims = "[ ]";
                String[] titlewords = title.split(delims);
                for (int d=0; d<titlewords.length; d++){
                    titlewords[d]=titlewords[d].replace("'","");
                    titlewords[d]=titlewords[d].replace('"',' ');
                    titlewords[d]=titlewords[d].replace(" ","");
                }
                shortTitles.add(titlewords[0] + space + titlewords[1]) ;

                /*
                 * Get the publication date and creation date
                 * We will be using a separate Date class that will convert our publication and creation date entries
                 * to a readable string type of date format YYYY/MM/DD
                 */
                pubDate = element.getElementsByTagName("PubDate").item(0).getTextContent();
                pubDates.add(parser.PublishDate(pubDate));
                if(element.getElementsByTagName("DateCompleted").getLength()>0){//some entries have no completion date listed
                    compDate = element.getElementsByTagName("DateCompleted").item(0).getTextContent();
                    compDates.add(parser.CompletionDate(compDate));
                } 
                else
                    compDates.add(null);//this is to maintain array size of compDates with other ArrayLists
                
                
                /*
                 * This section is to obtain the author names
                 * Some entries have either no author last name or intitials
                 * We use the booleans check1 and check 2 to indicate that the entry has either a last name or initials and then pass the parameters
                 * @param: lastName, initials, check1, check 2 - to Author class
                 */
                if (element.getElementsByTagName("LastName").getLength()>0) { //SOME ENTRIES HAVE NO AUTHORS
                    lastName = element.getElementsByTagName("LastName").item(0).getTextContent();
                    check1 = true; // SOME ENTRIES HAVE A LAST NAME BUT NO INITIALS
                }
                if (element.getElementsByTagName("Initials").getLength()>0) {
                    initials = element.getElementsByTagName("Initials").item(0).getTextContent();
                    check2=true;
                }
                authors.add(parser.getAuthorName(lastName,initials, check1, check2));

                /*
                 * This section of the loop will concatonate and form a parameter string for the url
                 */
                urlList.add(parser.formURL(authors, compDates, pubDates, shortTitles, i));

            }
            
            /*
             * This HttpRequestor calls the HttpRequestor.java
             * May contain side effects
             * Adds elements in the PMID ArrayList
             */
            new HttpRequester(TITLES, PMID, urlList);

            /*
             * This section is create a new XML file with only the PMID and Article titles
             * May contain side effects
             * Creates a new XML file that will 
             */
            new XMLresult(TITLES, PMID);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    
    }
}
