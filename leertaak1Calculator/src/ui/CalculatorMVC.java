package ui;

import multiformat.Calculator;

import javax.swing.*;
import java.awt.*;

/**
 * Created by FakeYou on 2/12/14.
 */
public class CalculatorMVC extends JFrame {
    Calculator calc;
    InputController inputController;
    OperandsView operandsView;
    HistoryView historyView;

    public void init() {
        calc = new Calculator();

        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(300, 300);
        setResizable(false);
        setVisible(true);
        setTitle("Calculator");

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;

        c.gridx = 0;
        c.gridy = 1;
        inputController = new InputController(calc);
        add(inputController, c);

        c.gridx = 0;
        c.gridy = 0;
        operandsView = new OperandsView();
        add(operandsView, c);

        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 2;
        historyView = new HistoryView();
        add(historyView, c);

        calc.addActionListener(operandsView);
        calc.addActionListener(historyView);

        pack();
    }

    public static void main(String[] args) {
        try {
            for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                System.out.println(info.getName());
                if(info.getName().equals("Windows")) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch(Exception e) {

        }

        CalculatorMVC applet = new CalculatorMVC();
        applet.init();
    }
}
