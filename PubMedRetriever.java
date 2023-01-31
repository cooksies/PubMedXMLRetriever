import java.io.InputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class PubMedRetriever {

    //Send the HTTP GET request to retrieve the XML data
    String url = "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/";
    InputStream input = new URL(url).openStream();

    //unmarshal the XML data into JAXB
    JAXBContext jaxbContext = JAXBContext.newInstance(PubmedArticleSet.class);
    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    PubmedArticleSet articleSet = (PubmedArticleSet) jaxbUnmarshaller.unmarshal(input);
    {
            // Process the data as desired
        for (PubmedArticleSet article : articleSet.getPubmedArticle()){
            System.out.println(articl.getMedlineCitation().getArticle().getArticleTitle());
        }
    }
    
}
