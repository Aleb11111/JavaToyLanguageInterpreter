package utils;

import java.util.Collection;
import java.util.Map;

public interface DictionaryInterface<K,V> {

    public String toString();
    public int size();
    public boolean containsKey(K key);
    public boolean containsValue(V value);
    public boolean isEmpty();
    public void update(K key, V newValue);
    public void insert (K key, V toBeInserted);
    public void clear();
    public V getValue(K key);
    public V remove(K key);
    public Collection<V> getAllValues();
    public Collection<K> getAllKeys();
    public Map<K,V> getContent();
    public DictionaryInterface<K,V> copy();

}
