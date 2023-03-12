/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprnkler;

import com.fuzzylite.Engine;

/**
 *
 * @author Asif Ali
 */
public class ControllerTest {

    public static void main(String[] args) {
        float pressure_level = (float) 7.0;
        float curFrequency = (float) 2.3;
        Controller ctlr = new Controller();
        double frequencyChange = ctlr.ControllerOutput(pressure_level, curFrequency);
        System.out.println("Frequency Change:" + frequencyChange);
        
        pressure_level = (float) 14;
        curFrequency = (float) 3.5;
        frequencyChange = ctlr.ControllerOutput(pressure_level, curFrequency);
        System.out.println("Frequency Change:" + frequencyChange);


        pressure_level = (float)31;
        curFrequency = (float) 5.7;
        frequencyChange = ctlr.ControllerOutput(pressure_level, curFrequency);
        System.out.println("Frequency Change:" + frequencyChange);
    }
}
