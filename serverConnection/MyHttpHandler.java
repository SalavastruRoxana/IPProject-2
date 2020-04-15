package serverConnection;

import bdConnection.DataBaseConnection;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dbObjects.Book;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;

import static java.lang.Thread.sleep;

public class MyHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String requestParamValue = null;
        if ("GET".equals(httpExchange.getRequestMethod())) {
            requestParamValue = handleGetRequest(httpExchange);
        } else if ("POST".equals(httpExchange.getRequestMethod())) {
            handlePostRequest(httpExchange);
        }
        handleResponse(httpExchange, requestParamValue);
    }

    private void handlePostRequest(HttpExchange httpExchange) {
        String method = httpExchange.getRequestURI().toString().split(":")[1];
        StringBuilder argument = new StringBuilder();
        int ch;
        while(true){
            try {
                if (!(( ch = httpExchange.getRequestBody().read()) > 0)) break;
                argument.append((char)ch);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        JSONObject jsonObject = new JSONObject(argument.toString());

        System.out.println(jsonObject.get("name"));
    }

    private String handleGetRequest(HttpExchange httpExchange) {
        return httpExchange.getRequestURI().toString().split(":")[1];
    }

    private void handleResponse(HttpExchange httpExchange, String requestParamValue) throws IOException {
        OutputStream outputStream = httpExchange.getResponseBody();
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "STUDENT";
        String password = "STUDENT";
        String htmlResponse = requestParamValue;

        httpExchange.sendResponseHeaders(200, htmlResponse.length());
        outputStream.write(htmlResponse.getBytes());

        outputStream.flush();
        outputStream.close();
    }

}
