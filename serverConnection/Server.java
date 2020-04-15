package serverConnection;

import com.sun.net.httpserver.HttpServer;
import org.json.JSONObject;
import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

public class Server {
    public static void main(String[] args) {
        try {
            String address = "localhost";
            int port = 9595;
            HttpServer server = HttpServer.create(new InetSocketAddress(address, port), 0);
            server.createContext("/test", new MyHttpHandler());
            server.setExecutor(Executors.newCachedThreadPool());
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
