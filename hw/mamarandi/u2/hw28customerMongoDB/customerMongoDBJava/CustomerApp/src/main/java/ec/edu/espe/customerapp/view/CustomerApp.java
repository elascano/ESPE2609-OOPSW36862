package ec.edu.espe.customerapp.view;

/**
 *
 * @author Marcos Mamarandi, @ESPE
 */

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import ec.edu.espe.customerapp.controller.CustomerController;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class CustomerApp {
    public static void main(String[] args) throws IOException {
        int port = 3013;
        CustomerController controller = new CustomerController();
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/computerstore/customers", exchange -> {
            if ("GET".equalsIgnoreCase(exchange.getRequestMethod())) {
                sendJson(exchange, 200, controller.getAllCustomersJson());
            } else {
                exchange.sendResponseHeaders(405, -1);
            }
        });

        server.createContext("/computerstore/customers/stats", exchange -> {
            if ("GET".equalsIgnoreCase(exchange.getRequestMethod())) {
                sendJson(exchange, 200, controller.getCustomerStatsJson());
            } else {
                exchange.sendResponseHeaders(405, -1);
            }
        });

        server.setExecutor(null);
        server.start();
        System.out.println("Marcos Computers Store Server is " + port);
    }

    private static void sendJson(HttpExchange exchange, int statusCode, String json) throws IOException {
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        exchange.sendResponseHeaders(statusCode, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }
}