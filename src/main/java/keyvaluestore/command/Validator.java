package keyvaluestore.command;

import java.util.Arrays;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Validator {

    static final String lineSeparator = ";";
    static Pattern startsWithMulti = Pattern.compile(String.format("^(%s)[\\s]", Operator.MultiCommandOperator.MULTI.name()));
    static Pattern endsWithExec = Pattern.compile(String.format("(%s)[%s]$",Operator.MultiCommandOperator.EXEC.name(), lineSeparator));

    static Pattern endsWithCompact = Pattern.compile(String.format("(%s)[%s]$",Operator.MultiCommandOperator.COMPACT.name(), lineSeparator));

    static Pattern tripleWordCommands = Pattern.compile("^(" + tripleWordOperands() + ")[\\s][a-zA-Z]+[\\s][0-9]+$");
    static Pattern doubleWordCommands = Pattern.compile("^(" + doubleWordOperands() + ")[\\s][a-zA-Z]+$");
    static Pattern multiCommands = Pattern.compile("^(" + multiWordOperands() + ")$");

    private static String doubleWordOperands() {
        Operator.DoubleCommandOperator[] commands = Operator.DoubleCommandOperator.values();
        Iterator<Operator.DoubleCommandOperator> iterator = Arrays.stream(commands).iterator();
        return buildSupportedOperands(iterator);
    }

    public static boolean isDoubleWordCommand(String command) {
        Matcher startsWith = doubleWordCommands.matcher(command);
        return startsWith.find();
    }

    public static boolean isTripleWordCommand(String command) {
        Matcher startsWith = tripleWordCommands.matcher(command);
        return startsWith.find();
    }

    public static boolean isMultiWordCommand(String command) {
        Matcher starWith = startsWithMulti.matcher(command);
        Matcher endWith = endsWithExec.matcher(command);
        return starWith.find() && endWith.find();
    }

    public static boolean isMultiCompactCommand(String command) {
        Matcher starWith = startsWithMulti.matcher(command);
        Matcher endWith = endsWithCompact.matcher(command);
        return starWith.find() && endWith.find();
    }

    public static boolean isMultiMultiSubCommand(String command) {
        Matcher matcher = multiCommands.matcher(command);
        return matcher.find();
    }

    private static String tripleWordOperands() {
        Operator.TripleCommandOperator[] commands = Operator.TripleCommandOperator.values();
        Iterator<Operator.TripleCommandOperator> iterator = Arrays.stream(commands).iterator();
        return buildSupportedOperands(iterator);
    }

    private static String multiWordOperands() {
        Operator.MultiCommandOperator[] commands = Operator.MultiCommandOperator.values();
        Iterator<Operator.MultiCommandOperator> iterator = Arrays.stream(commands).iterator();
        return buildSupportedOperands(iterator);
    }


    private static String buildSupportedOperands(Iterator<?> iterator) {
        String supportedOperators = "";
        while (iterator.hasNext()) {
            supportedOperators += iterator.next().toString();

            if (iterator.hasNext()) {
                supportedOperators += "|";
            }
        }
        return supportedOperators;
    }


}
