/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ec.edu.espe.baiscoperation.view;

import ec.edu.espe.baiscoperation.model.Operation;

/**
 *
 * @author Nacimba Mateo, Group5, @ESPE
 */
public class BaiscOperation {

   public static void main(String[] args){
       
       System.out.println("operation");
       Operation operation;
       operation=new Operation();
       System.out.println("the addition of 2 andd 3 is -->"+operation.add(1.2F,2.4F));
       System.out.println("the addition of 2 andd 3 is -->"+operation.add(1.2F,2.4F));
       System.out.println("the addition of 2 andd 3 is -->"+operation.add(1.2F,2.4F));
       
       
       System.out.println("divide -->"+operation.divide(1,0));
       
   }
}
