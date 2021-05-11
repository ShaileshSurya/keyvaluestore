package keyvaluestore.command.operation;


import keyvaluestore.command.Operator;

public class OperationFactoryImpl implements OperationFactory {

    @Override
    public Operation getOperation(String[] splits) {

        Operation operation;
        if(splits.length == 2) {
            switch (Operator.DoubleCommandOperator.valueOf(splits[0])) {
                case DEL:
                    return new DeleteOperation(splits[1]);
                case GET:
                    return new GetOperation(splits[1]);
                case INCR:
                    return new IncrementOperation(splits[1]);
            }
        }else if (splits.length == 3){
            switch (Operator.TripleCommandOperator.valueOf(splits[0])) {
                case INCRBY:
                    return new IncrementByOperation(splits[1],Integer.parseInt(splits[2]));
                case SET:
                    return new SetOperation(splits[1],Integer.parseInt(splits[2]));
            }
        }
        throw new RuntimeException("Internal Error");
    }
}
