package keyvaluestore.store;


import java.util.HashMap;
import java.util.Map;

public final class IStoreIntegerValueStoreImpl<K> implements IStore<K,Integer>{

    private Map<K, Integer> map;

    public IStoreIntegerValueStoreImpl(){
        map = new HashMap<>();
    }

    @Override
    public void add(K k, Integer value) {
        map.put(k, value);
    }

    @Override
    public Integer get(K k) {
        return map.get(k);
    }

    @Override
    public void delete(K k) {
        map.remove(k);
    }

    @Override
    public boolean containsKey(K k) {
        return map.containsKey(k);
    }
}
