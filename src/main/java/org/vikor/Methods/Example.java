package org.vikor.Methods;

import java.awt.List;


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
