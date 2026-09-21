/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theultimatestudentportal;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

/**
 *
 * @author jorda
 */
public class PortalServer {
    
    public static void main(String[] args) throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/login", exchange -> {

    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
    exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, OPTIONS");
    exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

    if (exchange.getRequestMethod().equals("OPTIONS")) {

        exchange.sendResponseHeaders(204, -1);
        exchange.close();

    } else if (exchange.getRequestMethod().equals("POST")) {

        java.io.InputStream inputStream = exchange.getRequestBody();

        String requestBody = new String(inputStream.readAllBytes());

        System.out.println("Login request received:");
        System.out.println(requestBody);

        String username = requestBody
                .split("\"username\":\"")[1]
                .split("\"")[0];

        String password = requestBody
                .split("\"password\":\"")[1]
                .split("\"")[0];

        boolean loginSuccessful =
                Authentication.LoginFromWeb(username, password);

        String response;

        if (loginSuccessful) {

            response = "Login successful";

        } else {

            response = "Incorrect username or password";
        }

        exchange.sendResponseHeaders(200, response.length());

        exchange.getResponseBody().write(response.getBytes());

        exchange.getResponseBody().close();

    } else {

        String response = "Only POST requests are allowed";

        exchange.sendResponseHeaders(405, response.length());

        exchange.getResponseBody().write(response.getBytes());

        exchange.getResponseBody().close();
    }
});
        
        server.createContext("/register", exchange -> {

    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
    exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, OPTIONS");
    exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

    if (exchange.getRequestMethod().equals("OPTIONS")) {

        exchange.sendResponseHeaders(204, -1);
        exchange.close();

    } else if (exchange.getRequestMethod().equals("POST")) {

        java.io.InputStream inputStream = exchange.getRequestBody();

        String requestBody = new String(inputStream.readAllBytes());

        System.out.println("got your register request:");
        System.out.println(requestBody);

        String username = requestBody
                .split("\"username\":\"")[1]
                .split("\"")[0];

        String password = requestBody
                .split("\"password\":\"")[1]
                .split("\"")[0];

        String dateOfBirth = requestBody
                .split("\"dob\":\"")[1]
                .split("\"")[0];
        
        String firstName = requestBody
                .split("\"firstName\":\"")[1]
                .split("\"")[0];
        
        String lastName = requestBody
                .split("\"lastName\":\"")[1]
                .split("\"")[0];
        
        String password1 = requestBody
                .split("\"password1\":\"")[1]
                .split("\"")[0];
                


        String response = Authentication.Register(username, firstName, lastName, password, password1, dateOfBirth);
        


        exchange.sendResponseHeaders(200, response.length());

        exchange.getResponseBody().write(response.getBytes());

        exchange.getResponseBody().close();

    } else {

        String response = "Only POST requests are allowed";

        exchange.sendResponseHeaders(405, response.length());

        exchange.getResponseBody().write(response.getBytes());

        exchange.getResponseBody().close();
    }
});

        server.start();

        System.out.println("Student Portal Backend running on port 8080");
    }
}