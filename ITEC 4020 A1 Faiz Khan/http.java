import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class http {
    public static void main(String title[], String pmid[], String URL[]) throws IOException, InterruptedException {
        for (int j = 0; j < title.length; j++){

            //SEND HTTP REQUEST
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL[j]+"&api_key=a86a08e589f56d7e8f68b14b32b14dc77708")) //API key to prevent PubMed from stopping the request
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            String a = response.body();

            //PARSING THE PMID
            String s1 = a.split("</Id>")[0];
            String s2 = s1.substring(s1.indexOf("Id>") + 3);
            pmid[j]=s2; //STORING




            System.out.println(j+1+"/"+ pmid.length); // Status
            Thread.sleep(30); // slows down requests because of API restraints
            //System.out.println("Title:"+TITLES[j]);
            //System.out.println("PMID:"+ PMID[j]);
            //System.out.println("URL:"+ URLS[j]);
        }
        //SEND INFO TO XML
        outputXML.main(title,pmid);
    }
}
