/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;
/**
 *
 * @author Isaac Acurio
 */
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;

public class Customer {

    static Dotenv dotenv = Dotenv.load();

    static MongoClient client = MongoClients.create(
            dotenv.get("MONGODB_URI")
    );

    public static MongoCollection<Document> customers =
            client.getDatabase("oop").getCollection("Customer");

    public static void checkConnection() {
        client.getDatabase("admin").runCommand(new Document("ping", 1));
    }
}
