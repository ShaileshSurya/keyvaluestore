package keyvaluestore.command;

import keyvaluestore.command.operation.*;
import keyvaluestore.exception.InvalidCommandException;
import java.util.*;

import static keyvaluestore.command.Operator.MultiCommandOperator.DISCARD;
import static keyvaluestore.command.Validator.lineSeparator;


public final class CommandProcessorImpl implements ISingleCommandProcessor, IMultiCommandProcessor {

    @Override
    public final Operation process(String command) {

        command = removeTrailingSemiColonIfAny(command);
        if (Validator.isDoubleWordCommand(command) || Validator.isTripleWordCommand(command)) {
            String[] split = command.split("\\s+");
            OperationFactory factory = new OperationFactoryImpl();
            return factory.getOperation(split);
        } else {
            throw new InvalidCommandException();
        }
    }

    @Override
    public List<Operation> processMultiple(String command) {
        if (Validator.isMultiWordCommand(command)) {
            return multiCommandOperations(command);
        } else if (Validator.isMultiCompactCommand(command)) {
            return processMultipleCompact(command);
        } else {
            throw new InvalidCommandException();
        }
    }

    public List<Operation> processMultipleCompact(String multiCommand) {
        throw new UnsupportedOperationException();
    }

    private List<Operation> multiCommandOperations(String multiCommand) {
        multiCommand = multiCommand.trim();
        multiCommand = removeLeadingKeyword(multiCommand, Validator.startsWithMulti.pattern());
        multiCommand = removeTrailingKeyWord(multiCommand, Validator.endsWithExec.pattern());

        String[] multiCommands = multiCommand.split(lineSeparator);

        List<Operation> operations = new ArrayList<Operation>();
        for (String command : multiCommands) {
            if (command.equals(DISCARD.name())) {
                operations.clear();
                continue;
            }

            Operation operation = process(command);
            operations.add(operation);

        }

        return operations;
    }

    private String removeLeadingKeyword(String multiCommand, String regex) {
        String[] multi = multiCommand.split(regex);
        return multi[1];
    }

    private String removeTrailingKeyWord(String multiCommand, String regex) {
        String[] multi = multiCommand.split(regex);
        return multi[0];
    }

    private String removeTrailingSemiColonIfAny(String command) {
        if (command.endsWith(lineSeparator)) {
            command = command.substring(0, command.length() - 1);
        }
        return command;
    }
}
