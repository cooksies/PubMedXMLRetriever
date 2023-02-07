import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class outputXML {
    public static final String path = "src/group5_result.xml";
    public static void main(String title[], String pmid[]){
        try{
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            //ROOT ELEMENT
            Element root = document.createElement("PubmedArticleSet");
            document.appendChild(root);

            for(int i=0; i<title.length;i++){
                //<PubmedArticle>
                Element PubmedArticle = document.createElement("PubmedArticle");
                root.appendChild(PubmedArticle);

                //PMID
                Element PMID = document.createElement("PMID");
                PMID.appendChild(document.createTextNode(pmid[i]));
                PubmedArticle.appendChild(PMID);

                //Title
                Element Title = document.createElement("ArticleTitle");
                Title.appendChild(document.createTextNode(title[i]));
                PubmedArticle.appendChild(Title);
            }

            //OUTPUTTING XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(path));
            transformer.transform(domSource, streamResult);








        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }


    }
}