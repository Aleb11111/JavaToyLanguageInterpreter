package utils;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements ListInterface<T> {

    private List<T> list;

    public MyList()
    {
        list = new ArrayList<>();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean add(T e) {
        return list.add(e);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public T get(int index) {
        return list.get(index);
    }

    @Override
    public String toString(){
        String representation = "";
        for(T elem: list){
            representation += (elem.toString() + "\n");
        }

        return representation;
    }

    @Override
    public void remove(T elem) {
        list.remove(elem);
    }

    @Override
    public boolean search(T elem) {
        return list.contains(elem);
    }

    @Override
    public List<T> getContent() {
        return this.list;
    }

}
