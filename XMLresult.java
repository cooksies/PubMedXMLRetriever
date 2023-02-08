import java.io.File;
import java.io.IOException;
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
import org.xml.sax.SAXException;

public class XMLresult {
    public static final String path = "group5_result.xml";
    
    public XMLresult(ArrayList<String> TITLES, ArrayList<String> PMID) {
        //We are forming a new document builder to be able to create an xml file
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            //ROOT ELEMENT
            Element root = document.createElement("PubmedArticleSet");
            document.appendChild(root);

            for(int i=0; i<TITLES.size();i++){
                //<PubmedArticle>
                Element PubmedArticle = document.createElement("PubmedArticle");
                root.appendChild(PubmedArticle);

                //PMID
                Element pmid = document.createElement("PMID");
                pmid.appendChild(document.createTextNode(PMID.get(i)));
                PubmedArticle.appendChild(pmid);

                //Title
                Element Title = document.createElement("ArticleTitle");
                Title.appendChild(document.createTextNode(TITLES.get(i)));
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
            e.printStackTrace();
        } catch (TransformerConfigurationException tce) {
            System.out.println("* Transformer Factory error");
            System.out.println(" " + tce.getMessage());

            Throwable x = tce;
            if (tce.getException() != null)
                x = tce.getException();
            x.printStackTrace(); 
        } catch (TransformerException te) {
            System.out.println("* Transformation error");
            System.out.println(" " + te.getMessage());
        
            Throwable x = te;
            if (te.getException() != null)
                x = te.getException();
            x.printStackTrace();
        }
    }
    
}

