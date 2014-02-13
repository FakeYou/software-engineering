package ui;

import multiformat.Calculator;
import multiformat.CalculatorAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by FakeYou on 2/13/14.
 */
public class HistoryView extends JPanel implements ActionListener {

    private Calculator calc;

    private JTextPane textPane;

    public HistoryView() {
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        setPreferredSize(new Dimension(200, 0));

        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(2, 2, 2, 2);

        textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setText("Hello world!");
        add(textPane, c);
    }

    public void actionPerformed(ActionEvent event) {
        calc = (Calculator) event.getSource();
        String text = "";

        for(CalculatorAction action : calc.getHistory()) {
            text += action.toString() + "\n";
        }

        textPane.setText(text);
    }
}
