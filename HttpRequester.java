import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class HttpRequester {

    public HttpRequester(ArrayList<String> TITLES, ArrayList<String> PMID, ArrayList<String> urlList) throws IOException, InterruptedException {
        for (int i = 0; i < TITLES.size(); i++){

            //SEND HTTP REQUEST
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlList.get(i)+"&api_key=a86a08e589f56d7e8f68b14b32b14dc77708")) //API key to prevent PubMed from stopping the request
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            String a = response.body();

            /*
             * The xml data we get from the server will have an idList of id, this is our PMID
             * It will look like <ID>PMID</Id>
             * We want to get only the PMID from this element
             */
            String s1 = a.split("</Id>")[0];
            String s2 = s1.substring(s1.indexOf("Id>") + 3);
            PMID.add(s2); 
        }
    }
    
}
