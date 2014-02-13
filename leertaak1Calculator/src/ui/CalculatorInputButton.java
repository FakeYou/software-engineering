package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by FakeYou on 2/12/14.
 */
public class CalculatorInputButton {

    public GridBagConstraints constraints;
    public String value;
    public Type type;
    public AbstractButton button;

    public enum Type {
        VALUE, OPERATOR, BASE, FORMAT
    }

    public CalculatorInputButton(String label, Type type, int gridx, int gridy, Boolean toggle) {
        if(toggle) {
            button = new JToggleButton(label);
        }
        else {
            button = new JButton(label);
        }

        this.value = label;
        this.type = type;

        constraints = new GridBagConstraints();
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(1, 1, 1, 1);
    }

    public CalculatorInputButton(String label, Type type, int gridx, int gridy) {
        this(label, type, gridx, gridy, false);
    }

    public void addToPane(JPanel pane) {
        pane.add(button, constraints);

        button.addActionListener((ActionListener) pane);
    }

    public String getText() {
        return button.getText();
    }

    public void setEnabled(Boolean value) {
        button.setEnabled(value);
    }

    public void setSelected(Boolean value) {
        if(button instanceof JToggleButton) {
            button.setSelected(value);
        }
    }
}