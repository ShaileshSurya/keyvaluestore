package keyvaluestore.command.operation;

import keyvaluestore.command.CommandProcessorImpl;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class OperationFactoryImplTest {

    @Test
    void processMultiCompact(){
        String[] arr = {};
        assertThrows(RuntimeException.class, () ->
                new OperationFactoryImpl().getOperation(arr)
        );
    }
}