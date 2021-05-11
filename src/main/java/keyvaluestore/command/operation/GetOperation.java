package keyvaluestore.command.operation;

import keyvaluestore.store.IStore;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetOperation<K> implements Operation {
    K key;

    // Can be replaced by GetOperation Behaviour
    @Override
    public Object execute(IStore store) {
        return store.get(key);
    }
}
