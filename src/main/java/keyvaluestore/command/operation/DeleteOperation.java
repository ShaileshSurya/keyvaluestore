package keyvaluestore.command.operation;

import keyvaluestore.store.IStore;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteOperation<K> implements Operation{
    K key;

    // Can be replaced by DeleteOperation Behaviour
    @Override
    public Object execute(IStore store) {
        store.delete(key);
        return null;
    }
}
