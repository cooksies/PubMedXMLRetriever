import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse; 

public class HttpRequester {
    
    public HttpRequester(String[] TITLES, String[] PMID, String[] urlList) {
        try {
            /*
            * There are a total of 5299 entries but because of API frequency restraint
            * there is a chance that we receive a "GOAWAY" or a "Remote server terminated handshake"
            */
            for (int i = 0; i < TITLES.length; i++){
                    //SEND HTTP REQUEST
                    HttpClient client = HttpClient.newHttpClient();
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(urlList[i]+"&api_key=a86a08e589f56d7e8f68b14b32b14dc77708")) //API key to allow 10 request per second
                            .build();

                    HttpResponse<String> response;
                    
                        response = client.send(request,
                                HttpResponse.BodyHandlers.ofString());
                    
                    String a = response.body();

                    /*
                    * The xml data we get from the server will have an idList of id, this is our PMID
                    * It will look like <ID>PMID</Id>
                    * We want to get only the PMID from this element
                    */
                    String s1 = a.split("</Id>")[0];
                    String s2 = s1.substring(s1.indexOf("Id>") + 3);
                    PMID[i] = s2; 

                    System.out.println(i+1+"/"+ TITLES.length + " entries processed"); // Status
                    Thread.sleep(10); // slows down requests because of API restraints
            }
            /*
             * This section is create a new XML file with only the PMID and Article titles
             * May contain side effects
             * Creates a new XML file that will output our results
             */
            new XMLresult(TITLES, PMID);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    
    
}

