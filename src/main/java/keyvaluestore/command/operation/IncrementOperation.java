package keyvaluestore.command.operation;

import keyvaluestore.store.IStore;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class IncrementOperation<K> implements Operation {

    K key;

    // Can be replaced by IncrementOperation Behaviour
    @Override
    public Object execute(IStore store) {

        int value = 0;
        if (store.containsKey(key)) {
            value = (int) store.get(key);
        }
        value = value + 1;
        store.add(key, value);

        return value;
    }
}
