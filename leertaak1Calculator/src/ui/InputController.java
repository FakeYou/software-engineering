package ui;

import multiformat.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by FakeYou on 2/12/14.
 */
public class InputController extends JPanel implements ActionListener {
    Calculator calc;

    private ArrayList<CalculatorInputButton> buttons;
    private String input = "";

    public InputController(Calculator calc) {
        this.calc = calc;
        setLayout(new GridBagLayout());

        buttons = new ArrayList<CalculatorInputButton>();

        CalculatorInputButton button0 =
                new CalculatorInputButton("0", CalculatorInputButton.Type.VALUE, 2, 4);
        button0.constraints.gridwidth = 2;

        buttons.add(button0);
        buttons.add(new CalculatorInputButton("1", CalculatorInputButton.Type.VALUE, 2, 3));
        buttons.add(new CalculatorInputButton("2", CalculatorInputButton.Type.VALUE, 3, 3));
        buttons.add(new CalculatorInputButton("3", CalculatorInputButton.Type.VALUE, 4, 3));
        buttons.add(new CalculatorInputButton("4", CalculatorInputButton.Type.VALUE, 2, 2));
        buttons.add(new CalculatorInputButton("5", CalculatorInputButton.Type.VALUE, 3, 2));
        buttons.add(new CalculatorInputButton("6", CalculatorInputButton.Type.VALUE, 4, 2));
        buttons.add(new CalculatorInputButton("7", CalculatorInputButton.Type.VALUE, 2, 1));
        buttons.add(new CalculatorInputButton("8", CalculatorInputButton.Type.VALUE, 3, 1));
        buttons.add(new CalculatorInputButton("9", CalculatorInputButton.Type.VALUE, 4, 1));

        buttons.add(new CalculatorInputButton("A", CalculatorInputButton.Type.VALUE, 0, 1));
        buttons.add(new CalculatorInputButton("B", CalculatorInputButton.Type.VALUE, 1, 1));
        buttons.add(new CalculatorInputButton("C", CalculatorInputButton.Type.VALUE, 0, 2));
        buttons.add(new CalculatorInputButton("D", CalculatorInputButton.Type.VALUE, 1, 2));
        buttons.add(new CalculatorInputButton("E", CalculatorInputButton.Type.VALUE, 0, 3));
        buttons.add(new CalculatorInputButton("F", CalculatorInputButton.Type.VALUE, 1, 3));

        buttons.add(new CalculatorInputButton("/", CalculatorInputButton.Type.OPERATOR, 5, 0));
        buttons.add(new CalculatorInputButton("*", CalculatorInputButton.Type.OPERATOR, 5, 1));
        buttons.add(new CalculatorInputButton("-", CalculatorInputButton.Type.OPERATOR, 5, 2));
        buttons.add(new CalculatorInputButton("+", CalculatorInputButton.Type.OPERATOR, 5, 3));

        buttons.add(new CalculatorInputButton("bin", CalculatorInputButton.Type.BASE, 1, 0, true));
        buttons.add(new CalculatorInputButton("oct", CalculatorInputButton.Type.BASE, 2, 0, true));
        buttons.add(new CalculatorInputButton("dec", CalculatorInputButton.Type.BASE, 3, 0, true));
        buttons.add(new CalculatorInputButton("hex", CalculatorInputButton.Type.BASE, 4, 0, true));

        buttons.add(new CalculatorInputButton(".", CalculatorInputButton.Type.OPERATOR, 4, 4));
        buttons.add(new CalculatorInputButton("=", CalculatorInputButton.Type.OPERATOR, 5, 4));

        buttons.add(new CalculatorInputButton("c", CalculatorInputButton.Type.OPERATOR, 0, 0));

        for(CalculatorInputButton button : buttons) {
            button.addToPane(this);
        }

        calc.setBase(new DecimalBase());
        enableBaseButtons(new DecimalBase());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        AbstractButton abstractButton = (AbstractButton) e.getSource();
        CalculatorInputButton sourceButton = buttons.get(0);
        Base base;

        for(CalculatorInputButton button : buttons) {
            if(button.button.equals(abstractButton)) {
                sourceButton = button;
            }
        }

        if(sourceButton.getText().equals("bin")) {
            base = new BinaryBase();
            calc.setBase(base);
            enableBaseButtons(base);
            input = "";
        }

        if(sourceButton.getText().equals("oct")) {
            base = new OctalBase();
            calc.setBase(base);
            enableBaseButtons(base);
            input = "";
        }

        if(sourceButton.getText().equals("dec")) {
            base = new DecimalBase();
            calc.setBase(base);
            enableBaseButtons(base);
            input = "";
        }

        if(sourceButton.getText().equals("hex")) {
            base = new HexBase();
            calc.setBase(base);
            enableBaseButtons(base);
            input = "";
        }

        if(sourceButton.type == CalculatorInputButton.Type.VALUE || sourceButton.getText().equals(".")) {
            input = input + sourceButton.getText();
        }

        if(sourceButton.getText().equals("=") && input.length() > 0) {
            try {
                calc.addOperand(input);
                input = "";
            }
            catch(FormatException exception){
                System.out.println("Wrong operand: " + exception.getMessage());
            }
            catch(NumberBaseException exception){
                System.out.println("Wrong operand: " + exception.getMessage());
            }
        }

        if(sourceButton.getText().equals("*")) {
            calc.multiply();
        }

        if(sourceButton.getText().equals("/")) {
            try {
                calc.divide();
            }
            catch (DivideByZeroException exception) {
                System.out.println(exception.getMessage());
            }
        }

        if(sourceButton.getText().equals("-")) {
            calc.subtract();
        }

        if(sourceButton.getText().equals("+")) {
            calc.add();
        }

        if(sourceButton.getText().equals("c")) {
            calc.delete();
            calc.delete();
            input = "";
        }

        System.out.print("\n["+calc.getBase().getName()+","
                + calc.getFormat().getName()+","
                + calc.firstOperand() + ", "
                + calc.secondOperand() + "]");
    }

    private void enableBaseButtons(Base base) {
        for(CalculatorInputButton button : buttons) {
            if(button.type == CalculatorInputButton.Type.VALUE) {
                if(base.getDigits().indexOf(button.getText()) != -1) {
                    button.setEnabled(true);
                }
                else {
                    button.setEnabled(false);
                }
            }

            if(button.type == CalculatorInputButton.Type.BASE) {
                if(base.getName().equals(button.getText())) {
                    button.setSelected(true);
                }
                else {
                    button.setSelected(false);
                }
            }
        }
    }
}
