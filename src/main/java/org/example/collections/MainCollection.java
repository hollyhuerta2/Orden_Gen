package org.example.collections;

import java.util.ArrayList;
import java.util.Iterator;

public class MainCollection {
    public static void main(String [] args) {
        ArrayList<Film> filmVector = new ArrayList<>() ;

        //var  = new MyMarvelCollection (filmVector);
        filmVector.add (new Film ("Ironman", 2008));
        filmVector.add (new Film ("Captain America", 2010));
        filmVector.add (new Film ("The Avengers", 2012));

        var iterator = filmVector.iterator ();
        while (iterator.hasNext ()) {
            System.out.println (iterator.next ());
        }

        System.out.println(" ");
        for (var film: filmVector) {
            System.out.println (film);
        }
    }
}
