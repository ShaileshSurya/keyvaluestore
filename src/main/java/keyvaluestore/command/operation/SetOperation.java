package keyvaluestore.command.operation;

import keyvaluestore.store.IStore;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
public class SetOperation<K> implements Operation {

    K key;

    int value;

    @Override
    public Object execute(IStore store) {
        store.add(key,value);
        return null;
    }
}
