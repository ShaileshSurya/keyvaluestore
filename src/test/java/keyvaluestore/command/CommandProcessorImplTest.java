package keyvaluestore.command;

import keyvaluestore.command.operation.*;
import keyvaluestore.exception.InvalidCommandException;
import org.junit.jupiter.api.Test;

import javax.naming.OperationNotSupportedException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommandProcessorImplTest {

    // TODO assert the operation fields.
    @Test
    void process() {

        Operation operation = new CommandProcessorImpl().process("SET shailesh 1;");
        assertTrue(operation instanceof SetOperation);

        operation = new CommandProcessorImpl().process("DEL shailesh;");
        assertTrue(operation instanceof DeleteOperation);

        operation = new CommandProcessorImpl().process("GET shailesh;");
        assertTrue(operation instanceof GetOperation);

        operation = new CommandProcessorImpl().process("INCR shailesh;");
        assertTrue(operation instanceof IncrementOperation);

        operation = new CommandProcessorImpl().process("SET shailesh 1;");
        assertTrue(operation instanceof SetOperation);

        operation = new CommandProcessorImpl().process("INCRBY shailesh 1;");
        assertTrue(operation instanceof IncrementByOperation);
    }

    @Test
    void processException() {
        assertThrows(InvalidCommandException.class, () ->
                new CommandProcessorImpl().process("Invalid command;")
        );
    }

    @Test
    void processMultiple() {

        List<Operation> operationList = new CommandProcessorImpl().processMultiple("MULTI\n" +
                "INCR foo;" +
                "SET key 2;" +
                "EXEC;");

        assertTrue(operationList.get(0) instanceof IncrementOperation);
        assertTrue(operationList.get(1) instanceof SetOperation);
    }

    @Test
    void processMultiCompact(){
        assertThrows(UnsupportedOperationException.class, () ->
                new CommandProcessorImpl().processMultiple("MULTI\n" +
                        "INCR foo;" +
                        "SET key 2;" +
                        "COMPACT;")
        );
    }

    @Test
    void processMultipleException() {
        assertThrows(InvalidCommandException.class, () ->
                new CommandProcessorImpl().processMultiple("Invalid command;")
        );
    }

    @Test
    void processDiscardMultiple() {
        List<Operation> operationList = new CommandProcessorImpl().processMultiple("MULTI\n" +
                "INCR foo;" +
                "SET key 2;" +
                "DISCARD;"+
                "GET key;"+
                "INCR key;"+
                "EXEC;");

       assertEquals(operationList.size(),2);
       assertTrue(operationList.get(0) instanceof GetOperation);
       assertTrue(operationList.get(1) instanceof IncrementOperation);
    }
}
