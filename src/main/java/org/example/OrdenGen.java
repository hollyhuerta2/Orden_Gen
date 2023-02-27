package org.example;

import java.util.Arrays;

public class OrdenGen {
    public static void main(String[] args){
        Integer[] intNum = {101, 99, 12, 19, 21, 111, 345, 25, 77, 81};
        Float[] floatNum = { 19.1f, 456.6f, 23.45f, 12.34f, 11.11f, 354.55f, 78.45f, 28.33f, 45.99f, 108.88f };
       // String[] strnum = {"zz", "aa", "cc", "hh", "bb", "ee", "ll"};

        System.out.println("antes: "+ Arrays.toString(intNum));
        Integer[] result = Utils.ordena1( intNum);
        System.out.println(Arrays.toString(result));

        System.out.println("--------------");

        System.out.println("antes: "+ Arrays.toString(floatNum));
        Float [] result2 = Utils.ordena1( floatNum);
        System.out.println(Arrays.toString(result2));



    }
}
