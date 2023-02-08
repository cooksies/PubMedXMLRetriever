import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLresult {
    public static final String path = "group5_result.xml";
    
    public XMLresult(ArrayList<String> TITLES, ArrayList<String> PMID) {
        //We are forming a new document builder to be able to create an xml file
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        try {
            documentBuilder = documentFactory.newDocumentBuilder();
        
        Document document = documentBuilder.newDocument();

        //Create the Root Element "PubmedArticleSet"
        Element root = document.createElement("PubmedArticleSet");
        document.appendChild(root);

        for(int i=0; i<TITLES.size();i++){
            //<PubmedArticle>
            Element PubmedArticle = document.createElement("PubmedArticle");
            root.appendChild(PubmedArticle);

            //<PMID>
            Element pmid = document.createElement("PMID");
            pmid.appendChild(document.createTextNode(PMID.get(i)));
            PubmedArticle.appendChild(pmid);

            //<ArticleTitle>
            Element articleTitle = document.createElement("ArticleTitle");
            articleTitle.appendChild(document.createTextNode(TITLES.get(i)));
            PubmedArticle.appendChild(articleTitle);
        }

        //OUTPUTTING XML
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(path));
        transformer.transform(domSource, streamResult);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
    
}

