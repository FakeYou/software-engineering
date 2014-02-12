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

    public void init() {
        calc = new Calculator();

        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(300, 300);
        setResizable(false);
        setVisible(true);
        setTitle("Calculator");

        inputController = new InputController(calc);
        add(inputController, BorderLayout.SOUTH);

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
