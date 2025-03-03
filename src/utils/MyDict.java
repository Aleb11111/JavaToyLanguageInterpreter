package utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MyDict<K,V> implements DictionaryInterface<K,V> {

    private Map<K,V> dictionary;

    public MyDict(){this.dictionary = new HashMap<K, V>();}

    @Override
    public String toString()
    {
        String representation = "[ \n";
        Collection<K> allKeys = dictionary.keySet();
        for(K key: allKeys)
        {
            representation += (key.toString() + ": "+dictionary.get(key) + ", \n");
        }
        representation +="]";
        return representation;
    }

    @Override
    public int size() {
        return dictionary.size();
    }

    @Override
    public boolean containsKey(K key) {
        return dictionary.containsKey(key);
    }

    @Override
    public boolean containsValue(V value) {
        return dictionary.containsValue(value);
    }

    @Override
    public boolean isEmpty() {
        return this.dictionary.isEmpty();
    }

    @Override
    public void update(K key, V newValue) {
        this.dictionary.replace(key, newValue);
    }

    @Override
    public void insert(K key, V toBeInserted) {
        this.dictionary.put(key, toBeInserted);
    }

    @Override
    public void clear() {
        this.dictionary.clear();
    }

    @Override
    public V getValue(K key) {
       return  this.dictionary.get(key);
    }

    @Override
    public V remove(K key) {
        return this.dictionary.remove(key);
    }

    @Override
    public Collection<V> getAllValues() {
        return this.dictionary.values();
    }

    @Override
    public Collection<K> getAllKeys() {
        return this.dictionary.keySet();
    }

    @Override
    public Map<K, V> getContent() {
        return this.dictionary;
    }

    @Override
    public DictionaryInterface<K, V> copy() {
        DictionaryInterface<K,V> newDictionary = new MyDict<>();

        for(K key: this.dictionary.keySet())
        {
            newDictionary.insert(key,this.dictionary.get(key));
        }
        return newDictionary;
    }
}
