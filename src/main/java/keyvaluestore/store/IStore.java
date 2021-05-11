package keyvaluestore.store;

public interface IStore<K,V> {
    public void add(K k, V v);
    public V get(K k);
    public void delete(K k);
    public boolean containsKey(K k);
}
