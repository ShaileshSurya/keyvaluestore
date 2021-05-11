package keyvaluestore.client;


import keyvaluestore.command.*;
import keyvaluestore.command.operation.Operation;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ClientV2ImplTest {

    @Test
    public void fetchTest() {
        IClientV2<Integer> clientV2 = new ClientV2Impl();

        ISingleCommandProcessor commandProcessor = new CommandProcessorImpl();
        Operation operation = commandProcessor.process("SET shailesh 1;");
        clientV2.execute(operation);

        operation = commandProcessor.process("GET shailesh;");
        Integer value = clientV2.fetch(operation);
        assertEquals(value, 1);

        operation = commandProcessor.process("DEL shailesh;");
        clientV2.execute(operation);

        operation = commandProcessor.process("GET shailesh;");
        value = clientV2.fetch(operation);
        assertNull(value);

        operation = commandProcessor.process("GET shailesh;");
        value = clientV2.fetch(operation);
        assertNull(value);
    }


    @Test
    public void executeMultipleTest() {
        IClientV2<Integer> clientV2 = new ClientV2Impl();

        IMultiCommandProcessor commandProcessor = new CommandProcessorImpl();
        List<Operation> operation = commandProcessor.processMultiple("MULTI\n" +
                "INCR foo;" +
                "SET key 2;" +
                "GET foo;" +
                "GET key;" +
                "EXEC;");

        List<Integer> result = clientV2.multiExecute(operation);
        assertEquals(result.get(0),1);
        assertNull(result.get(1));
        assertEquals(result.get(2),1);
        assertEquals(result.get(3),2);
    }


    @Test
    public void executeMultipleCompactTest() {
        IClientV2<Integer> clientV2 = new ClientV2Impl();

        IMultiCommandProcessor commandProcessor = new CommandProcessorImpl();
        List<Operation> operationList = new CommandProcessorImpl().processMultiple("MULTI\n" +
                "INCR foo;" +
                "SET key 2;" +
                "DISCARD;" +
                "GET key;" +
                "INCR key;" +
                "INCRBY key 10;" +
                "INCR key;" +
                "EXEC;");

        List<Integer> result = clientV2.multiExecute(operationList);

        assertEquals(result.size(), 4);
        assertNull(result.get(0));
        assertEquals(result.get(1), 1);
        assertEquals(result.get(2), 11);
        assertEquals(result.get(3), 12);
    }


}