/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprnkler;

import com.fuzzylite.Engine;
import com.fuzzylite.norm.s.Maximum;
import com.fuzzylite.norm.t.Minimum;
import com.fuzzylite.rule.Rule;
import com.fuzzylite.rule.RuleBlock;

/**
 *
 * @author Asif Ali
 */
public class FuzzyRules {

    private RuleBlock ruleBlock = new RuleBlock();

    public FuzzyRules(Engine engine) {
        ruleBlock = new RuleBlock();
        ruleBlock.setEnabled(true);
        ruleBlock.setName("Rule Block");

        // Set up fuzzy functions for AND, OR and NOT
        ruleBlock.setConjunction(new Minimum());
        ruleBlock.setDisjunction(new Maximum());
        ruleBlock.setActivation(new Minimum());

        // Add the rules as follows
        ruleBlock.addRule(Rule.parse("if (frequency is f1 or frequency is f2) then change_in_frequency is PH", engine));
        ruleBlock.addRule(Rule.parse("if (frequency is f3) then change_in_frequency is Zero", engine));

        ruleBlock.addRule(Rule.parse("if (frequency is f3 and pressure is lo) then change_in_frequency is NL", engine));
        ruleBlock.addRule(Rule.parse("if (frequency is f3 and pressure is op) then change_in_frequency is Zero", engine));
        ruleBlock.addRule(Rule.parse("if (frequency is f3 and pressure is hi) then change_in_frequency is PM", engine));
        ruleBlock.addRule(Rule.parse("if (frequency is f3 and pressure is vh) then change_in_frequency is PH", engine));

        ruleBlock.addRule(Rule.parse("if (frequency is f1 and pressure is lo) then change_in_frequency is Zero", engine));
        ruleBlock.addRule(Rule.parse("if (frequency is f1 and pressure is hi) then change_in_frequency is Zero", engine));

        ruleBlock.addRule(Rule.parse("if (frequency is f1 and pressure is vh) then change_in_frequency is PH", engine));
        ruleBlock.addRule(Rule.parse("if (frequency is f2 and pressure is vh) then change_in_frequency is PM", engine));
        ruleBlock.addRule(Rule.parse("if (frequency is f4 and pressure is vh) then change_in_frequency is NM", engine));

    }

    public RuleBlock getRuleBlock() {
        return ruleBlock;
    }

    public void setRuleBlock(RuleBlock ruleBlock) {
        this.ruleBlock = ruleBlock;
    }

}
