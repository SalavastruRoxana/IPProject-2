package serverConnection;

import org.json.JSONObject;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Client {
    static HttpClient client;

    public static void main(String[] args) {
        client = HttpClient.newHttpClient();
        String uri = "http://localhost:9595/test:getBook";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Generic_name");
        jsonObject.put("lastName", "Generic_last_name");
        jsonObject.put("age", 420);
        //get(uri);
        String post = jsonObject.toString();
        post(uri, post);

    }

    static void get(String uri) {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void post(String uri, String data) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .POST(HttpRequest.BodyPublishers.ofString(data))
                .build();
        try {
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
