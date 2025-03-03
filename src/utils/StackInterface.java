package utils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public interface StackInterface<Elem> {


    List<Elem> reverse();
    Elem top();
    public Elem pop();
    public int size();
    public void clear();
    public void push(Elem newElement);
    public boolean isEmpty();
    public ArrayDeque<Elem> getContent();

}
