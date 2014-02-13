package ui;

import multiformat.Calculator;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by FakeYou on 2/13/14.
 */
public class OperandsView extends JPanel implements ActionListener {

    private Calculator calc;
    private JTextField operand1;
    private JTextField operand2;
    private JTextField input;
    private JLabel operand1Label;
    private JLabel operand2Label;
    private JLabel inputLabel;

    public OperandsView() {
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(2, 2, 1, 2);

        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 5.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        operand1 = new JTextField();
        operand1.setEditable(false);
        add(operand1, c);

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.05;
        c.fill = GridBagConstraints.HORIZONTAL;
        operand1Label = new JLabel();
        operand1Label.setText("Operand 1");
        operand1Label.setHorizontalAlignment(JLabel.RIGHT);
        add(operand1Label, c);

        c.gridx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        operand2 = new JTextField();
        operand2.setEditable(false);
        add(operand2, c);

        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        operand2Label = new JLabel();
        operand2Label.setText("Operand 2");
        operand2Label.setHorizontalAlignment(JLabel.RIGHT);
        add(operand2Label, c);

        c.gridx = 1;
        c.gridy = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        input = new JTextField();
        input.setEditable(true);
        add(input, c);

        input.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateCalcInput();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateCalcInput();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateCalcInput();
            }

            private void updateCalcInput() {
                Runnable doUpdateCalcInput = new Runnable() {
                    @Override
                    public void run() {
                        calc.setSilentInput(input.getText());
                    }
                };
                SwingUtilities.invokeLater(doUpdateCalcInput);
            }
        });

        c.gridx = 0;
        c.gridy = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        inputLabel = new JLabel();
        inputLabel.setText("Input");
        inputLabel.setHorizontalAlignment(JLabel.RIGHT);
        add(inputLabel, c);
    }

    public void actionPerformed(ActionEvent event) {
        if(event.getSource() instanceof Calculator) {
            System.out.println("calculator event");
            calc = (Calculator) event.getSource();

            operand1.setText(calc.firstOperand());
            operand2.setText(calc.secondOperand());
            input.setText(calc.getInput());
        }
        else {

            System.out.println("other event");
        }
    }
}
