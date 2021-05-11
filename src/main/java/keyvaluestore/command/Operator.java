package keyvaluestore.command;

public interface Operator {

     enum DoubleCommandOperator {
        GET,
        DEL,
        INCR,
    }

    enum TripleCommandOperator {
        SET,
        INCRBY,
    }

    enum MultiCommandOperator {
        MULTI,
        COMPACT,
        DISCARD,
        EXEC,
    }

}