package org.example.collections;

import java.util.ArrayList;
import java.util.Iterator;

public class MyMarvelCollection<Film> implements Iterable<Film> {
    private Film [] films;
    private final ArrayList<Film> peli;
    public MyMarvelCollection (Film [] films) {
       peli = new ArrayList<Film>();
    }

    public void add (Film film) {
        peli.add(film);
    }
    public Film get (int index) {
        return null;
    }
    @Override
    public  Iterator<Film> iterator () {
        return peli.iterator();
    }
}
