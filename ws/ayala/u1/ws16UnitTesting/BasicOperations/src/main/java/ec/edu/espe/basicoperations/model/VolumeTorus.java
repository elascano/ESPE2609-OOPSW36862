/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.basicoperations.model;

/**
 *
 * @author Enrique Ayala, LogicLords,@ESPE
 */

public class VolumeTorus {
    public float computeVolume(float majorRadius, float minorRadius) {
        float volume;
        volume = 2 * (float) Math.PI * (float) Math.PI * majorRadius * minorRadius * minorRadius;
        return volume;
    }
}
