/*
 * (C) Copyright 2005 Davide Brugali, Marco Torchiano
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307  USA
 */
package multiformat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The multiformat calculator
 */
public class Calculator {
    private Rational operand_0 = new Rational();
    private Rational operand_1 = new Rational();
    private String input = "";

    private ArrayList<ActionListener> actionListenerList = new ArrayList<ActionListener>();
    private ArrayList<CalculatorAction> history = new ArrayList<CalculatorAction>();

    // The current format of the calculator
    private Format format = new FixedPointFormat();
    // The current numberbase of the calculator
    private Base base = new DecimalBase();

    public void addOperand(String newOperand) throws FormatException, NumberBaseException {
        Rational parsed;

        try {
            parsed = format.parse(newOperand, base);
        }
        // catch NumberBaseException an rethrow to prevent
        // the following code from being executed
        catch (NumberBaseException e) {
            throw(e);
        }

        operand_1 = operand_0;
        operand_0 = parsed;

        processEvent( new ActionEvent( this, ActionEvent.ACTION_PERFORMED, null));
    }

    public void add(){
        String operandValue_0 = firstOperand();
        String operandValue_1 = secondOperand();

        operand_0 = operand_1.plus(operand_0);
        operand_1 = new Rational();

        history.add(new CalculatorAction(
                operandValue_0,
                operandValue_1,
                secondOperand(),
                CalculatorAction.Operator.ADD,
                getBase()
        ));

        processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "operator"));
    }
    public void subtract() {
        String operandValue_0 = firstOperand();
        String operandValue_1 = secondOperand();

        operand_0 = operand_1.minus(operand_0);
        operand_1 = new Rational();

        history.add(new CalculatorAction(
                operandValue_0,
                operandValue_1,
                secondOperand(),
                CalculatorAction.Operator.SUBTRACT,
                getBase()
        ));

        processEvent( new ActionEvent( this, ActionEvent.ACTION_PERFORMED, "operator"));
    }
    public void multiply() {
        String operandValue_0 = firstOperand();
        String operandValue_1 = secondOperand();

        operand_0 = operand_1.mul(operand_0);
        operand_1 = new Rational();

        history.add(new CalculatorAction(
                operandValue_0,
                operandValue_1,
                secondOperand(),
                CalculatorAction.Operator.MULTIPLY,
                getBase()
        ));

        processEvent( new ActionEvent( this, ActionEvent.ACTION_PERFORMED, "operator"));
    }
    public void divide() {
        String operandValue_0 = firstOperand();
        String operandValue_1 = secondOperand();

        if(operand_0.numerator == 0.0) {
            throw new DivideByZeroException();
        }
        else {
            operand_0 = operand_1.div(operand_0);
            operand_1 = new Rational();

            history.add(new CalculatorAction(
                    operandValue_0,
                    operandValue_1,
                    secondOperand(),
                    CalculatorAction.Operator.DIVIDE,
                    getBase()
            ));
        }

        processEvent( new ActionEvent( this, ActionEvent.ACTION_PERFORMED, "operator"));
    }
    public void delete() {
        operand_0 = operand_1;
        operand_1 = new Rational();

        processEvent( new ActionEvent( this, ActionEvent.ACTION_PERFORMED, null));
    }

    public String firstOperand(){
        return format.toString(operand_1,base);
    }
    public String secondOperand(){
        return format.toString(operand_0,base);
    }

    public String getInput() {
        return input;
    }

    public ArrayList<CalculatorAction> getHistory() {
        return history;
    }

    public void setInput(String input) {
        this.input = input;

        processEvent( new ActionEvent( this, ActionEvent.ACTION_PERFORMED, null));
    }

    public void setSilentInput(String input) {
        this.input = input;
    }

    public void setBase(Base newBase){
        base = newBase;

        processEvent( new ActionEvent( this, ActionEvent.ACTION_PERFORMED, null));
    }
    public Base getBase(){
        return base;
    }
    public void setFormat(Format newFormat){
        format = newFormat;

        processEvent( new ActionEvent( this, ActionEvent.ACTION_PERFORMED, null));
    }
    public Format getFormat(){
        return format;
    }

    public void addActionListener(ActionListener l){
        actionListenerList.add(l);

        processEvent( new ActionEvent( this, ActionEvent.ACTION_PERFORMED, null));
    }

    public void removeActionListener( ActionListener l){
        if ( actionListenerList.contains( l ) ) {
            actionListenerList.remove( l );
        }
    }

    private void processEvent(ActionEvent e){
        for( ActionListener l : actionListenerList) {
            l.actionPerformed( e );
        }
    }
}