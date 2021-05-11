package keyvaluestore.command.operation;

import keyvaluestore.store.IStore;

public interface Operation<K> {
    K execute(IStore store);
}
