package keyvaluestore.client;


import keyvaluestore.command.operation.Operation;

import java.util.List;

public interface IClientV2<V> {
    public V fetch(Operation operation);
    public void execute(Operation operation);
    public List<V> multiExecute(List<Operation> operations);
}
