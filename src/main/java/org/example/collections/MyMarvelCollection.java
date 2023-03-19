package org.example.collections;

import java.util.Iterator;

public class MyMarvelCollection implements Iterable<Film> {
    private Film [] films;
    public MyMarvelCollection (Film [] films) {

    }
    public void add (Film film) {

    }

    public Film get (int index) {
        return get(1);
    }

    @Override
    public Iterator<Film> iterator () {
        return null;
    }
}
