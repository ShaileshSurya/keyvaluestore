package keyvaluestore.command.operation;

import java.util.List;

public interface OperationFactory {
    Operation getOperation(String[] splits);
}
