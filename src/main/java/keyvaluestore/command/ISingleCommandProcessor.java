package keyvaluestore.command;

import keyvaluestore.command.operation.Operation;

import java.util.List;

public interface ISingleCommandProcessor {
    Operation process(String Command);
}
