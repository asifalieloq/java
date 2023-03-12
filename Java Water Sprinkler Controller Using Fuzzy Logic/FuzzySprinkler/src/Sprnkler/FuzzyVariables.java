/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprnkler;

import com.fuzzylite.Engine;
import com.fuzzylite.defuzzifier.Centroid;
import com.fuzzylite.norm.s.Maximum;
import com.fuzzylite.term.Trapezoid;
import com.fuzzylite.term.Triangle;
import com.fuzzylite.variable.InputVariable;
import com.fuzzylite.variable.OutputVariable;

/**
 *
 * @author Asif Ali
 */
public class FuzzyVariables {

    private InputVariable Frequency;
    private InputVariable Pressure;
    private OutputVariable freq_change;

    

    public FuzzyVariables(Engine engine) {
          // TODO: Setup the level input variable
        // Setup the level linguistic variable
        Frequency = new InputVariable();
        Frequency.setEnabled(true);
        Frequency.setName("frequency");
        Frequency.setRange(0, 6);

        // TODO: create the appropriate terms for inputVariable1 	
        // Add each term for the Linguistic variable
        // Water level term setup
        Frequency.addTerm(new Trapezoid("f1", 0.0,  0.2, 1.8, 2.0));
        Frequency.addTerm(new Trapezoid("f2", 1.8,  2.0, 2.5, 3.0));
        Frequency.addTerm(new Trapezoid("f3", 2.7,  2.8, 4.0, 4.3));
        Frequency.addTerm(new Trapezoid("f4", 3.8,  4.2, 5.8, 6.0));
        // Add the variable to the fuzzy engine
        engine.addInputVariable(Frequency);

        // TODO: Setup the demand input variable
        // Setup the demand linguistic variable
        Pressure = new InputVariable();
        Pressure.setEnabled(true);
        Pressure.setName("pressure");
        Pressure.setRange(5, 46);

        // TODO: create the appropriate terms for inputVariable2 	
        // Add each term for the Linguistic variable
        Pressure.addTerm(new Triangle("lo", 5, 14));
        Pressure.addTerm(new Triangle("op", 13, 15));
        Pressure.addTerm(new Triangle("hi", 14,  30));
        Pressure.addTerm(new Trapezoid("vh", 22,  32,  45,   46));
        // Add the variable to the fuzzy engine
        engine.addInputVariable(Pressure);

        // TODO: Setup the output command variable
        // Setup the output command variable
        freq_change = new OutputVariable();
        freq_change.setEnabled(true);
        freq_change.setName("change_in_frequency");
        freq_change.setRange(-0.31, 0.35);
        // How should the rules be accumulated
        freq_change.fuzzyOutput().setAccumulation(new Maximum());
        // How will the output be Defuzzified?
        freq_change.setDefuzzifier(new Centroid(100));
        freq_change.setLockValidOutput(false);
        freq_change.setLockOutputRange(false);
        // Add each term for the Linguistic variable
        // TODO: create the appropriate terms for outputVariable - see lab sheet
        freq_change.addTerm(new Trapezoid("NL", -0.31, -0.3, -0.2, -0.1));
        freq_change.addTerm(new Triangle("NM", -0.2, 0.0));
        freq_change.addTerm(new Triangle("Zero", -0.01, 0.01));
        freq_change.addTerm(new Triangle("PM", 0.0,  0.2));
        freq_change.addTerm(new Trapezoid("PH", 0.1,0.2, 0.3,  0.35));

        // Add the variable to the fuzzy engine
        engine.addOutputVariable(freq_change);
    }

    public void setFrequency(InputVariable Frequency) {
        this.Frequency = Frequency;
    }

    public void setPressure(InputVariable Pressure) {
        this.Pressure = Pressure;
    }

    public void setFreq_change(OutputVariable freq_change) {
        this.freq_change = freq_change;
    }

    public InputVariable getFrequency() {
        return Frequency;
    }

    public InputVariable getPressure() {
        return Pressure;
    }

    public OutputVariable getFreq_change() {
        return freq_change;
    }
    
 
    
    
}
