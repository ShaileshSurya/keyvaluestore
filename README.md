# keyvaluestore [![Build Status](https://travis-ci.com/ShaileshSurya/keyvaluestore.svg?branch=main)](https://travis-ci.com/ShaileshSurya/keyvaluestore) [![codecov](https://codecov.io/gh/ShaileshSurya/keyvaluestore/branch/main/graph/badge.svg?token=OD864R1WA6)](https://codecov.io/gh/ShaileshSurya/keyvaluestore)


This is a implementation of Key value store. 

#### How to Use
Client API's 

```java
public interface IClientV2<V> {
    public V fetch(Operation operation);
    public void execute(Operation operation);
    public List<V> multiExecute(List<Operation> operations);
}
``` 

Command Processor API's
```java
public interface ISingleCommandProcessor {
    Operation process(String Command);
}
```

```java
public interface IMultiCommandProcessor {
    List<Operation> processMultiple(String Command);
}
```

Example 1: Execute API :- Execute Command and do not expect response. 
```java
IClientV2<Integer> clientV2 = new ClientV2Impl();

ISingleCommandProcessor commandProcessor = new CommandProcessorImpl();
        Operation operation = commandProcessor.process("SET shailesh 1;");
        
clientV2.execute(operation);
```

Example 2: Fetch API :- Fetch API gathers response in response Object. 
```java
IClientV2<Integer> clientV2 = new ClientV2Impl();

operation = commandProcessor.process("GET shailesh;");
Integer value = clientV2.fetch(operation);
assertEquals(value, 1);
```

Example 3: multiExecute API :- multiExecute API gathers response in List. 
```java
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
```

Supported Commands
##### Case 1: SET key-values and then GET them. You can also DEL a key.
```
SET key1 value1
SET key2 value2
GET key1 // should return value1
DEL key2 // deletes key2 and its value
GET key2 // should return nil
SET key2 newvalue2
```

###### Case 2: Basic numeric operations and error cases
```
SET counter 0
INCR counter // increments a "counter" key, if present and returns incremented value, in this case: 1
GET counter // returns 1
INCRBY counter 10 // increment by 10, returns 11
INCR foo // automatically creates a new key called "foo" and increments it by 1 and thus returns 1
```

##### Case 3: Command spanning multiple sub-commands
###### Example 1: Happy path
```
MULTI // starts a multi line commands
INCR foo // queues this command, doesn't execute it immediately
SET key1 value1 // queues this command, doesn't execute it immediately
EXEC // execute all queued commands and returns output of all commands in an array, thus returns: [1 value1]
```

###### Example 2: Discard
```
MULTI // starts a multi line commands
INCR foo // queues this command, doesn't execute it immediately
SET key1 value1 // queues this command, doesn't execute it immediately
DISCARD // discard all queued commands
GET key1 // returns nil as key1 doesn't exists
```

## Case 4: Generate compacted command output
###### Example 1:
```
SET counter 10
INCR counter
INCR counter
SET foo bar
GET counter // returns 12
INCR counter
COMPACT // this should return following output

SET counter 13
SET foo bar
```

###### Example 2:
```
INCR counter // returns 1
INCRBY counter 10 // returns 11
GET counter // returns 11
DEL counter // deletes counter
COMPACT // this should compact to empty output as there's no keys present in the DB
```