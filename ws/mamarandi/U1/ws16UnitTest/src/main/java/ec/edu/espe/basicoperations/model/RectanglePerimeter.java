/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.basicoperations.model;

/**
 *
 * @author Marcos Mamarandi, Binary Bosses,@ESPE
 */


public class RectanglePerimeter {

    public float calculatePerimeter(float base, float height) {
        float perimeter;

        if (base <= 0 || height <= 0) {
            throw new IllegalArgumentException("Invalid dimensions");
        }

        perimeter = 2 * (base + height);
        return perimeter;
    }
}

  
