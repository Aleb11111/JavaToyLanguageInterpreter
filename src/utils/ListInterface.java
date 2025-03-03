package utils;

import java.util.List;
import java.util.Map;

public interface ListInterface<T> {

    int size();
    boolean isEmpty();
    boolean add(T e);
    void clear();
    T get(int index);
    String toString();

    void remove(T elem);
    boolean search(T elem);

    List<T> getContent();
}
