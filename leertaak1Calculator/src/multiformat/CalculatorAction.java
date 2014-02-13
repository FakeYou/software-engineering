package multiformat;

/**
 * Created by FakeYou on 2/13/14.
 */
public class CalculatorAction {
    private String operand_0;
    private String operand_1;
    private String outcome;
    private Operator operator;
    private Base base;

    public enum Operator {
        ADD, SUBTRACT, DIVIDE, MULTIPLY
    }

    public CalculatorAction(String operand_0, String operand_1, String outcome, Operator operator, Base base) {
        this.operand_0 = operand_0;
        this.operand_1 = operand_1;
        this.outcome = outcome;
        this.operator = operator;
        this.base = base;
    }

    public String toString() {
        String string = "";

        string += "[" + base.getName() + "] ";
        string += operand_0;

        switch (operator) {
            case ADD:
                string += " + ";
                break;
            case SUBTRACT:
                string += " - ";
                break;
            case DIVIDE:
                string += " / ";
                break;
            case MULTIPLY:
                string += " * ";
                break;
        }

        string += operand_1;
        string += " = ";
        string += outcome;

        return string;
    }

}
