package keyvaluestore.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ValidatorTest {

    @Test
    public void validateTest(){

        assertFalse(Validator.isDoubleWordCommand("SET shailesh 1"));
        assertFalse(Validator.isDoubleWordCommand("GET shailesh 1"));
        assertTrue(Validator.isDoubleWordCommand("GET shailesh"));
        assertTrue(Validator.isDoubleWordCommand("DEL shailesh"));
        assertTrue(Validator.isDoubleWordCommand("INCR shailesh"));
        assertFalse(Validator.isDoubleWordCommand("SET"));
        assertFalse(Validator.isDoubleWordCommand("SET shailesh;"));

    }

    @Test
    public void validateTestThreeWordCommand(){
        assertTrue(Validator.isTripleWordCommand("SET shailesh 1"));
        assertTrue(Validator.isTripleWordCommand("INCRBY shailesh 1"));
        assertFalse(Validator.isTripleWordCommand("GET shailesh 1"));
        assertFalse(Validator.isTripleWordCommand("GET shailesh"));
        assertFalse(Validator.isTripleWordCommand("DEL shailesh"));
        assertFalse(Validator.isTripleWordCommand("INCR shailesh"));
        assertFalse(Validator.isTripleWordCommand("SET"));
        assertFalse(Validator.isTripleWordCommand("SET shailesh"));

    }

    @Test
    public void validateTestMultiSub(){
        assertFalse(Validator.isMultiMultiSubCommand("SET shailesh 1"));
        assertFalse(Validator.isMultiMultiSubCommand("INCRBY shailesh 1"));
        assertFalse(Validator.isMultiMultiSubCommand("GET shailesh 1"));
        assertFalse(Validator.isMultiMultiSubCommand("GET shailesh"));
        assertFalse(Validator.isMultiMultiSubCommand("DEL shailesh"));
        assertFalse(Validator.isMultiMultiSubCommand("INCR shailesh"));
        assertFalse(Validator.isMultiMultiSubCommand("SET"));
        assertFalse(Validator.isMultiMultiSubCommand("SET shailesh"));
        assertTrue(Validator.isMultiMultiSubCommand("MULTI"));
        assertTrue(Validator.isMultiMultiSubCommand("COMPACT"));
        assertTrue(Validator.isMultiMultiSubCommand("EXEC"));
        assertTrue(Validator.isMultiMultiSubCommand("DISCARD"));
    }

}