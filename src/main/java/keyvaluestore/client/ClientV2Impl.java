package keyvaluestore.client;

import keyvaluestore.command.operation.Operation;
import keyvaluestore.store.IStore;
import keyvaluestore.store.IStoreIntegerValueStoreImpl;

import java.util.ArrayList;
import java.util.List;

public class ClientV2Impl implements IClientV2<Integer>{

    private IStore store;


    public ClientV2Impl(){
        store = new IStoreIntegerValueStoreImpl<String>();
    }

    @Override
    public Integer fetch(Operation operation) {
        return (Integer)operation.execute(store);
    }

    @Override
    public void execute(Operation operation) {
        operation.execute(store);
    }

    @Override
    public List<Integer> multiExecute(List<Operation> operations) {
        List<Integer> response = new ArrayList<>();
        for (Operation operation: operations) {
            Object opExecResult = operation.execute(store);
            if (opExecResult == null) {
                response.add(null);
            }else{
                response.add((Integer) opExecResult);
            }
        }
        return response;
    }
}
