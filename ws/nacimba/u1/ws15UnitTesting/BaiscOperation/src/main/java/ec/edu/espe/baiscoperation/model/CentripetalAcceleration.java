/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.baiscoperation.model;

/**
 *
 * @author Nacimba Mateo, Group5, @ESPE
 */
public class CentripetalAcceleration {

    public float calculate(float velocity, float radius) {

        float acceleration;
        acceleration = (velocity * velocity) / radius;
        return acceleration;
    }
}