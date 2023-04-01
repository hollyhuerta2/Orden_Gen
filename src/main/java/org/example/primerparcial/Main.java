package org.example.primerparcial;

import java.io.File;
import java.util.Map;
import java.lang.NullPointerException;

public class Main {
    
    public static void main (String [] args) {
        System.out.println ("-------------From File--------------------");
        
        File file = new File ("C:\\colors.json");
        Dictionary<Object, Object> dicFromFile = new Dictionary<> ();
        dicFromFile.parse ((file));
        
        for (Object e: dicFromFile.elements ()) {
            System.out.println (e);
        }
        
        System.out.println ("-------------From String--------------------");
        
        Dictionary<String, String> dicFromString = new Dictionary<> ();
        dicFromString.parse ("{\"aliceblue\": \"#f0f8ff\", \"antiquewhite\": \"#faebd7\", \"aqua\": \"#00ffff\", \"aquamarine\": \"#7fffd4\", \"azure\": \"#f0ffff\", \"beige\": \"#f5f5dc\", \"bisque\": \"#ffe4c4\", \"black\": \"#000000\", \"blanchedalmond\": \"#ffebcd\", \"blue\": \"#0000ff\", \"blueviolet\": \"#8a2be2\", \"brown\": \"#a52a2a\"}");
        
        for (Map.Entry<String, String> e: dicFromString.elements ()) {
            System.out.println (e);
        }
        
    }
    
}