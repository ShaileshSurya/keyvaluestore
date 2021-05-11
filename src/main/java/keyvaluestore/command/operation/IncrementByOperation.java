package keyvaluestore.command.operation;

import keyvaluestore.store.IStore;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class IncrementByOperation<K> implements Operation {

    K key;
    int incrementStep;

    // Can be replaced by IncrementOperationBehaviour
    @Override
    public Object execute(IStore store) {
        Integer value = (Integer) store.get(key);
        Integer incrementedValue = value + incrementStep;
        store.add(key, incrementedValue);
        return incrementedValue;
    }
}
