package utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyProcTable <TKey,TValue> implements ProcTable<TKey,TValue>{

    private Map<TKey,TValue> sem;
    private int lastAddress;

    public MyProcTable()
    {
        this.sem = new HashMap<>();
        this.lastAddress = 1;
    }

    @Override
    public void setLastAddress() {
        this.lastAddress = this.lastAddress + 1;
    }

    @Override
    public int getLastAddress() {
        int copyOfThePosition = this.lastAddress;
        this.setLastAddress();
        return copyOfThePosition;
    }

    @Override
    public Collection values() {
        return this.sem.values();
    }


    @Override
    public void setContent(Map newHeap){
        this.sem.clear();;
        this.sem.putAll(newHeap);
    }

    @Override
    public int size() {
        return this.sem.size();
    }

    @Override
    public boolean containsKey(TKey key) {
        return this.sem.containsKey(key);
    }

    @Override
    public boolean containsValue(TValue value) {
        return this.sem.containsValue(value);
    }

    @Override
    public boolean isEmpty() {
        return this.sem.isEmpty();
    }



    @Override
    public void insert(TKey key, TValue newValue) {

        if (!sem.containsKey(key))
            this.sem.put(key,newValue);
    }

    @Override
    public void clear() {
        this.sem.clear();
    }

    @Override
    public TValue getValue(TKey key) {
        TValue val = this.sem.get(key);
        return val;
    }

    @Override
    public TValue remove(TKey key) {
        return this.sem.remove(key);
    }

    @Override
    public Collection getAllValues() {
        return this.sem.values();
    }

    @Override
    public Collection getAllKeys() {
        return this.sem.keySet();
    }

    @Override
    public Map getContent() {
        return this.sem;
    }

    @Override
    public String toString(){
        String heapVals="";
        for(TKey key: sem.keySet())
            heapVals=heapVals.concat("["+key+"->"+ sem.get(key).toString()+"] ");
        return heapVals;
    }

}
