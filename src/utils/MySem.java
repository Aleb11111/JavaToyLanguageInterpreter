package utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MySem<TKey,TValue> implements SemaphoreInterface<TKey,TValue>{
    private Map<TKey,TValue> sem;
    final static Lock lock = new ReentrantLock();
    private int lastAddress;

    public MySem()
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
    public void update(TKey key, TValue newValue) {
        lock.lock();
        this.sem.replace(key,newValue);
        lock.unlock();
    }

    @Override
    public void insert(TKey key, TValue newValue) {

        lock.lock();
        this.sem.put(key,newValue);
        lock.unlock();
    }

    @Override
    public void clear() {
        this.sem.clear();
    }

    @Override
    public TValue getValue(TKey key) {
        lock.lock();
        TValue val = this.sem.get(key);
        lock.unlock();
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
