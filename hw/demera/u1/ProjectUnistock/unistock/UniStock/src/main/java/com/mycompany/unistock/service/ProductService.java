package com.mycompany.unistock.service;

import com.mycompany.unistock.model.Product;
import com.mycompany.unistock.model.Stationery;
import com.mycompany.unistock.model.Uniform;
import com.mycompany.unistock.structure.SimpleList;

import java.math.BigDecimal;

public class ProductService {

    private final SimpleList<Product> products = new SimpleList<>();
    private int nextId = 1;

    //agrega las referencias a los servicios de catálogos 
    private final UniformService uniformService;
    private final StationeryService stationeryService;

    // recibe catalogo
    public ProductService(UniformService uniformService, StationeryService stationeryService) {
        this.uniformService = uniformService;
        this.stationeryService = stationeryService;
    }

    public Product registerUniform(String name, BigDecimal price, int stock, String size, String school) {
        // Validar que la talla y el colegio
        if (!uniformService.existsSize(size)) {
            System.out.println("Error: La talla '" + size + "' no existe en el catálogo de uniformes.");
            return null; // Rechaza el registro si no está registrado previamente
        }
        if (!uniformService.existsSchool(school)) {
            System.out.println("Error: El colegio '" + school + "' no existe en el catálogo de uniformes.");
            return null; // Rechaza el registro si no está registrado previamente
        }

        Product product = new Uniform(nextId++, name, price, stock, size, school);
        products.add(product);
        return product;
    }

    public Product registerStationery(String name, BigDecimal price, int stock, String brand, String type) {
        if (!stationeryService.existsBrand(brand)) {
            System.out.println("Error: La marca '" + brand + "' no existe en el catálogo de papelería.");
            return null; 
        }
        
        if (!stationeryService.existsType(type)) {
            System.out.println(" Error: El tipo '" + type + "' no existe en el catálogo de papelería.");
            return null; 
        }

        Product product = new Stationery(nextId++, name, price, stock, brand, type);
        products.add(product);
        return product;
    }

    public boolean removeProduct(int id) {
        Product product = findById(id);
        if (product == null) {
            return false;
        }
        products.remove(product);
        return true;
    }

    public Product findById(int id) {
        products.resetIteration();
        while (products.hasNext()) {
            Product product = products.next();
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public SimpleList<Product> getProducts() {
        return products;
    }
}