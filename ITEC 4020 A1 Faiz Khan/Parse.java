import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
public class Parse {
     private static final String url = "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/esearch.fcgi?";

    private static final String FILENAME = "src/4020a1-datasets.txt";

    public static void main(String[] args) {
        boolean check1,check2;
        String compDate = "";
        String publishdate,year,day;
        String lastname = "";
        String initials = "";
        String author = "";
        String dateComp="";




        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try{ // PARSING
            String[] Articles;
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(FILENAME));
            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("PubmedArticle");

            //ARRAYS FOR OUTPUTS
            String[] Authors = new String[list.getLength()];
            String[] Pubdates= new String[list.getLength()];
            String[] ATitles= new String[list.getLength()];
            String[] URLS = new String[list.getLength()];
            String[] DCOM = new String[list.getLength()];
            String[] TITLES = new String[list.getLength()];
            String[] PMID = new String[list.getLength()];

            // INFORMATION SECTION
            for (int temp = 0; temp < list.getLength(); temp++) {

                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;


                    // TITLE
                    String title = element.getElementsByTagName("ArticleTitle").item(0).getTextContent();
                    TITLES[temp]=title;



                    //PUB DATE
                    String pubdate = element.getElementsByTagName("PubDate").item(0).getTextContent();
                    year = pubdate.substring(0, 4); // DEFAULT IS YYYYMMDD REPLACE TO YYYY/MM/DD
                    String month = pubdate.substring(4, 7);
                    day = pubdate.substring(7);

                    if((day.isEmpty())==true){
                        day="01"; //PUBLISH DATE (DAY) IS EMPTY AT TIMES
                    }
                    month = switch (month) { // CHANGE FROM LETTERS TO NUMBERS
                        case "Jan" -> "01";
                        case "Feb" -> "02";
                        case "Mar" -> "03";
                        case "Apr" -> "04";
                        case "May" -> "05";
                        case "Jun" -> "06";
                        case "Aug" -> "08";
                        case "Sep" -> "09";
                        case "Oct" -> "10";
                        case "Nov" -> "11";
                        case "Dec" -> "12";
                        case "Jul" -> "07";
                        default -> "error";
                    };
                    publishdate = year + "/" + month + "/" + day; // FINISHING YYYY/MM/DD

                    //FIRST TWO WORDS OF A TITLE
                    String x="%20";
                    String delims = "[ ]";
                    String[] titlewords = title.split(delims);
                    for (int d=0; d<titlewords.length; d++){
                        titlewords[d]=titlewords[d].replace("'","");
                        titlewords[d]=titlewords[d].replace('"',' ');
                        titlewords[d]=titlewords[d].replace(" ","");
                    }

                    title = titlewords[0] + x + titlewords[1];


                    //PUB MED QUERIES MAX OUT AT A CERTAIN WORD COUNT, FIRST TWO WORDS FOR INCREASED ACCURACY

                    //AUTHOR
                    if ((element.getElementsByTagName("LastName").getLength() > 0)) { //SOME ENTRIES HAVE NO AUTHORS
                        lastname = element.getElementsByTagName("LastName").item(0).getTextContent();
                        check1 = true; // SOME ENTRIES HAVE A LAST NAME BUT NO INITIALS
                    }else{
                        check1=false;
                    }
                    if ((element.getElementsByTagName("Initials").getLength() > 0)) {
                        initials = element.getElementsByTagName("Initials").item(0).getTextContent();
                        check2=true;
                    }
                    else{
                        check2=false;
                    }
                    if (check1 == true && check2 == true){ // IF BOTH LAST NAME AND INITIALS ARE PRESENT
                        author= lastname+"%20"+initials;
                        //System.out.println("Author :" + author);
                        Authors[temp] = author;
                    }
                    else if(check1 == true && check2 == false){
                        author =lastname;
                        //System.out.println("Author :" + author);
                        Authors[temp] = author;
                    }
                    else if(check1 == false && check2 == true){
                        author=initials;
                        //System.out.println("Author :" + author);
                        Authors[temp] = author;
                    }
                    else{
                        Authors[temp] = null;
                    }

                    //DATECOMPLETED
                    if ((element.getElementsByTagName("DateCompleted").getLength() > 0)) { //SOME ENTRIES HAVE NO AUTHORS
                        dateComp = element.getElementsByTagName("DateCompleted").item(0).getTextContent();
                        String year1 = dateComp.substring(0, 4); // DEFAULT IS YYYYMMDD REPLACE TO YYYY/MM/DD
                        String month1 = dateComp.substring(4, 6);
                        String day1 = dateComp.substring(6,8);
                        compDate = year1 + "/" + month1 + "/" + day1; // FINISHING YYYY/MM/DD
                    }

                    //PRINTING EVERYTHING
                   // System.out.println("Title :" + title);
                   // System.out.println("PubDate :" + publishdate);

                    //EVERYTHING IN THE ARRAYS
                    Pubdates[temp] = publishdate;
                    ATitles[temp]=title;
                    DCOM[temp]=compDate;

                }
            }
            //MAKES URLS SECTION
            for (int i = 0; i < list.getLength(); i++){

                if (Authors[i]!=null && DCOM[i]!=null){ //IF ARTICLE HAS AN AUTHOR
                    Authors[i]=Authors[i].replace(" ","%20");
                    URLS[i]= url+"db=pubmed&term="+Authors[i]+"[au]"+Pubdates[i]+"[pdat]"+DCOM[i]+"[DCOM]";

                }
                else if(Authors[i]!=null && DCOM[i]==null){
                    Authors[i]=Authors[i].replace(" ","%20");
                    URLS[i]= url+"db=pubmed&term="+Authors[i]+"[au]"+Pubdates[i]+"[pdat]"+ATitles[i]+"[title]";

                }
                else if(Authors[i]==null && DCOM[i]!=null){//IF NO AUTHOR STATED
                    URLS[i]= url+"db=pubmed&term="+Pubdates[i]+"[pdat]"+ATitles[i]+"[title]"+DCOM[i]+"[DCOM]";
                }
                else{
                    URLS[i]= url+"db=pubmed&term="+Pubdates[i]+"[pdat]"+ATitles[i]+"[title]";
                }
            }

            //SEND INFO TO HTTP CLASS
            http.main(TITLES,PMID,URLS);


            } catch (ParserConfigurationException | SAXException | IOException e) {
                e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}

