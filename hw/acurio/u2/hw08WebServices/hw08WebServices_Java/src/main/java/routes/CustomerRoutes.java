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
import org.bson.Document;
import com.mongodb.client.model.Filters;

public class CustomerRoutes {

    public static void register(Javalin app) {

        app.get("/computerstore/customers", ctx -> {

            String json = "[";
            boolean first = true;

            for (Document customer : Customer.customers.find()) {
                if (!first) {
                    json += ",";
                }

                json += customer.toJson();
                first = false;
            }

            json += "]";

            ctx.contentType("application/json");
            ctx.result(json);
        });

        // Buscar cliente por ID
        app.get("/computerstore/customer/{id}", ctx -> {

            int id = Integer.parseInt(ctx.pathParam("id"));

            Document customer = Customer.customers
                    .find(Filters.eq("id", id)).first();

            ctx.contentType("application/json");

            if (customer == null) {
                ctx.status(404);
                ctx.result("{\"message\":\"Customer not found\"}");
            } else {
                ctx.result(customer.toJson());
            }
        });
    }
}
