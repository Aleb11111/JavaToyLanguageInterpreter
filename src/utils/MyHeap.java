package utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MyHeap<TKey,TValue> implements HeapInterface<TKey,TValue>{

    private Map<TKey,TValue> heap;
    int firstAvailablePosition = 1;

    public MyHeap()
    {
        this.heap = new HashMap<>();
    }
    @Override
    public Collection values() {
        return this.heap.values();
    }

    @Override
    public void setContent(Map newHeap){
        this.heap.clear();;
        this.heap.putAll(newHeap);
    }

    @Override
    public void setFirstAvailablePosition() {
        this.firstAvailablePosition = this.firstAvailablePosition + 1;
    }

    @Override
    public int getFirstAvailablePosition() {
        int copyOfThePosition = this.firstAvailablePosition;
        this.setFirstAvailablePosition();
        return copyOfThePosition;
    }

    @Override
    public int size() {
        return this.heap.size();
    }

    @Override
    public boolean containsKey(TKey key) {
        return this.heap.containsKey(key);
    }

    @Override
    public boolean containsValue(TValue value) {
        return this.heap.containsValue(value);
    }

    @Override
    public boolean isEmpty() {
        return this.heap.isEmpty();
    }

    @Override
    public void update(TKey key, TValue newValue) {
        this.heap.replace(key,newValue);
    }

    @Override
    public void insert(TKey key, TValue newValue) {
        this.heap.put(key,newValue);
    }

    @Override
    public void clear() {
        this.heap.clear();
    }

    @Override
    public TValue getValue(TKey key) {
        return this.heap.get(key);
    }

    @Override
    public TValue remove(TKey key) {
        return this.heap.remove(key);
    }

    @Override
    public Collection getAllValues() {
        return this.heap.values();
    }

    @Override
    public Collection getAllKeys() {
        return this.heap.keySet();
    }

    @Override
    public Map getContent() {
        return this.heap;
    }

    @Override
    public String toString(){
        String heapVals="";
        for(TKey key:heap.keySet())
            heapVals=heapVals.concat("["+key+"->"+heap.get(key).toString()+"] ");
        return heapVals;
    }

}
