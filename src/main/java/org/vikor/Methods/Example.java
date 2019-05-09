package org.vikor.Methods;

import org.vikor.DataStructure.TriangularFuzzyNumber;
import org.vikor.FuzzyOperations.FuzzyOp;

public class Example {

    public static void main(String[] args){
       FuzzyOp fp = new FuzzyOp();
       TriangularFuzzyNumber tfn1 = new TriangularFuzzyNumber(1.0,2.0,3.0);
       TriangularFuzzyNumber tfn2 = new TriangularFuzzyNumber(1.0,3.0,7.0);
       TriangularFuzzyNumber res = fp.Div(tfn1, tfn2);
       System.out.println(res.getLeft()+","+res.getCenter()+","+res.getRight());
    }
}
