package utils;

import java.util.*;

public class MyStack<Elem> implements StackInterface<Elem> {

    private ArrayDeque<Elem> stack;

    public MyStack()
    {
        stack = new ArrayDeque<>();
    }

    @Override
    public String toString()
    {
        String representation = "";
        for(Elem current: stack){
            representation += current.toString() + "\n";
        }
//        representation = stack.toString();
        return representation;
    }


    @Override
    public List<Elem> reverse() {
        List<Elem> list;

        list = Arrays.asList((Elem[])stack.toArray());
        return list;

    }

    @Override
    public Elem top() {
        return stack.peek();
    }

    @Override
    public Elem pop() {
        return stack.pop();
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public void clear() {
        stack.clear();
    }

    @Override
    public void push(Elem newElement) {
        stack.push(newElement);
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public ArrayDeque<Elem> getContent() {
        return this.stack;
    }
}
