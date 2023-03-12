/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprnkler;

/**
 *
 * @author Asif Ali
 */
public class FuzzyMember {

    private float start_freq = 45;
    private float start_pressure = (float) 2.3;
    private float end_pressure = 57;
    private float end_freq = 7;

    public void setEndValues(float freq, float pressure) {
        end_freq = freq;
        end_pressure = pressure;
    }

    public void fuzzify(float pressure, float freq, Controller ctrl) {
        System.out.println("Frequency Change:");
    }

    void setStartValues(double d, double d0) {
        start_freq = (float) d;
        start_pressure = (float) d0;
    }

}
