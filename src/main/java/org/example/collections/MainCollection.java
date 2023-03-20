package org.example.collections;

import java.util.ArrayList;
import java.util.Iterator;

public class MainCollection {
    public static void main(String [] args) {
        var filmVector = new Film [10];

        var myCol  = new MyMarvelCollection (filmVector);
        myCol.add (new Film ("Ironman", 2008));
        myCol.add (new Film ("Captain America", 2010));
        myCol.add (new Film ("The Avengers", 2012));

        var iterator = myCol.iterator ();
        while (iterator.hasNext ()) {
            System.out.println (iterator.next ());
        }

        System.out.println(" ");
        for (var film: myCol) {
            System.out.println (film);
        }
    }
}
