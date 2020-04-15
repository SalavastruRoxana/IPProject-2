package mainPackage;

import bdConnection.DataBaseConnection;
import dbObjects.Book;
import org.json.JSONObject;
import serverConnection.*;

import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String args[]) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Artur");
        jsonObject.put("lastName", "Scolnic");
        jsonObject.put("age", 22);

        JSONObject object = new JSONObject(jsonObject.toString());
        System.out.println(object.toString());

    }
}
