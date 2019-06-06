package org.vikor.Methods;
import java.math.BigDecimal;
import java.math.RoundingMode;
 
public class Example {
 
    public static void main(String[] args) {
        
        double templateDouble = 12.1354678578862; 
        
        System.out.println("Template double: " + templateDouble);
        
         templateDouble = new BigDecimal(templateDouble).setScale(3, RoundingMode.UP).doubleValue();
        String s = " 1";
        double s1 = Double.valueOf(s);
        System.out.println("New double: " + templateDouble+" "+s1);
        
    }
    
}