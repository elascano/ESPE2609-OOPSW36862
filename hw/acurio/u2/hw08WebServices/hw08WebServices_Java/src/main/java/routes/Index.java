/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package routes;

/**
 *
 * @author Isaac Acurio
 */
import io.javalin.Javalin;
import models.Customer;

public class Index {

    public static void main(String[] args) {

        Customer.checkConnection();
        System.out.println("Connected to MongoDB");

        Javalin app = Javalin.create();

        CustomerRoutes.register(app);

        app.start(3001);

        System.out.println("Java Server running on port 3001");
    }
}
