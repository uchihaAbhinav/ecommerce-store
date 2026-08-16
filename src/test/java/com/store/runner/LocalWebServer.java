package com.store.runner;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class LocalWebServer {
    private static HttpServer server;

    public static void start() throws IOException {
        Path staticDir = Path.of("src/test/resources/static");
        Files.createDirectories(staticDir);

        Path htmlFile = staticDir.resolve("index.html");
        String html = """
                <!doctype html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8" />
                    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
                    <title>E-Commerce Store</title>
                    <style>
                        body { font-family: Arial, sans-serif; margin: 40px; background: #f5f5f5; }
                        .container { max-width: 920px; margin: 0 auto; background: white; padding: 32px; border-radius: 12px; }
                        h1 { color: #1f2937; }
                        .products { display: grid; grid-template-columns: repeat(3, minmax(180px, 1fr)); gap: 16px; }
                        .product { border: 1px solid #e5e7eb; border-radius: 10px; padding: 16px; }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <h1>Welcome to E-Commerce Store</h1>
                        <p>Discover the best deals on trending products.</p>
                        <div class="products">
                            <div class="product"><h3>Wireless Headphones</h3><p>$79.99</p></div>
                            <div class="product"><h3>Smart Watch</h3><p>$129.99</p></div>
                            <div class="product"><h3>Eco Bottle</h3><p>$24.99</p></div>
                        </div>
                    </div>
                </body>
                </html>
                """;
        Files.writeString(htmlFile, html);

        if (server == null) {
            server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext("/", exchange -> {
                byte[] response = Files.readAllBytes(htmlFile);
                exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
                exchange.sendResponseHeaders(200, response.length);
                exchange.getResponseBody().write(response);
                exchange.close();
            });
            server.setExecutor(null);
            server.start();
        }
    }

    public static void stop() {
        if (server != null) {
            server.stop(0);
            server = null;
        }
    }
}
