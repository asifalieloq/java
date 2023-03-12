/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprnkler;

import com.fuzzylite.Engine;
import com.fuzzylite.rule.RuleBlock;
import com.fuzzylite.variable.InputVariable;
import com.fuzzylite.variable.OutputVariable;

/**
 *
 * @author Asif Ali
 */
public class Controller {

    private Engine engine;
    FuzzyVariables fv;
    private RuleBlock ruleBlock;
    private FuzzyMember fm;

    public Controller() {
        engine = new Engine();
        engine.setName("FuzzySprinkler");
        fv = new FuzzyVariables(engine);
        engine.addInputVariable(fv.getFrequency());
        engine.addInputVariable(fv.getPressure());
        engine.addOutputVariable(fv.getFreq_change());
        FuzzyRules fr = new FuzzyRules(engine);
        engine.addRuleBlock(fr.getRuleBlock());
    }

    public float ControllerOutput(float l, float d) {

        // TODO: Load the input variables
        // Level
        fv.getPressure().setInputValue(l);

        // Demand
        fv.getFrequency().setInputValue(d);

        // TODO: Run the engine
        engine.process();

        // TODO: Return the output -> outputVariable.defuzzify()
        return (float) (fv.getFreq_change().defuzzify());
    }

    public Engine getEngine() {
        return engine;
    }

    public FuzzyMember getFm() {
        return fm;
    }

    public FuzzyVariables getFv() {
        return fv;
    }

    

    
}
