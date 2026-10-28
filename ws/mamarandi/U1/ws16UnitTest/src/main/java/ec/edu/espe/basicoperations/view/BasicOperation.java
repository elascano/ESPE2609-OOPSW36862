/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ec.edu.espe.basicoperations.view;

import ec.edu.espe.basicoperations.model.Operation;

/**
 *
 * @author Marcos Mamarandi, Binary Bosses,@ESPE
 */
public class BasicOperation {

    public static void main(String[] args) {
        System.out.println("Operations!");
        //declare and instantiate an object
        //declare and anilizr parameters
        //call the method
        
        Operation operation;
        operation = new Operation();
        System.out.println("the addition of 2 and 3 is -->" + operation.add(1.2F,2.4F));
        System.out.println("the addition of 2 and 3 is -->" + operation.add(2,3));
        System.out.println("the addition of 2 and 3 is -->" + operation.add(2,3));
        
        System.out.println("divide -->" + operation.divide(1,0));
        
        //Triangle triangle = new Triangle();

//float perimeter = triangle.calculatePerimeter(3, 4, 5);

//System.out.println("Triangle perimeter --> " + perimeter);
    }
}
