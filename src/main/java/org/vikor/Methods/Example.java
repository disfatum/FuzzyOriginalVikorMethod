package org.vikor.Methods;

import java.awt.List;

import com.fuzzylite.*;
import com.fuzzylite.activation.General;
import com.fuzzylite.defuzzifier.Centroid;
import com.fuzzylite.norm.s.Maximum;
import com.fuzzylite.norm.t.AlgebraicProduct;
import com.fuzzylite.rule.Rule;
import com.fuzzylite.rule.RuleBlock;
import com.fuzzylite.term.Ramp;
import com.fuzzylite.variable.InputVariable;
import com.fuzzylite.variable.OutputVariable;

public class Example {

    public static void main(String[] args){
       String s = "1,2,3";
       String[] l = new String[s.length()];
    	l = s.split(",");
    	for(int i = 0 ; i < l.length;i++) {
    		System.out.println(l[i]);
    	}
    	
    }
}
