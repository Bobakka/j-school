package com.sbt.javaschool.rnd.lesson3;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

//Class for Task5
public class ReverseIterator<E> implements Iterator {
    private ListIterator<E> iterator;

    public ReverseIterator(List<E> src) throws NullPointerException {

        if (src.size() == 0) {
            throw new NullPointerException();
        }

        iterator = src.listIterator(src.size());
   }

    @Override
    public boolean hasNext() {
        return (iterator.hasPrevious());
    }

    @Override
    public E next() {
        if (!hasNext()) {
            throw  new NoSuchElementException();
        }
        return iterator.previous();
    }
}
