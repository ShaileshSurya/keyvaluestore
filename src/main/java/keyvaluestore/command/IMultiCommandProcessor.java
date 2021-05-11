package keyvaluestore.command;

import keyvaluestore.command.operation.Operation;

import java.util.List;

public interface IMultiCommandProcessor {
    List<Operation> processMultiple(String Command);
}
