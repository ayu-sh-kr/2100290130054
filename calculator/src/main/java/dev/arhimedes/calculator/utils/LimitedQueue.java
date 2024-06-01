package dev.arhimedes.calculator.utils;

import java.util.LinkedList;

public class LimitedQueue extends LinkedList<Integer> {
    private int limit;

    public LimitedQueue(int limit) {
        this.limit = limit;
    }

    @Override
    public boolean add(Integer e) {
        super.add(e);
        while (size() > limit) { super.remove(); }
        return true;
    }
}
