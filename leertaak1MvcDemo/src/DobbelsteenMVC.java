import javax.swing.*;
import java.awt.*;

public class DobbelsteenMVC extends JApplet
{
    DobbelsteenModel model;             // Model
    TekstView tekstView;                // View
    DobbelsteenView dobbelsteenView;    // View
    StatistiekenView statistiekenView;  // View
    DobbelsteenController controller;   // Controller

    public void init()
    {
        resize(350,300);

        // Maak het model
        model = new DobbelsteenModel();

        // Maak de controller en geef hem het model
        controller = new DobbelsteenController(model);
        controller.setBackground(Color.cyan);
        getContentPane().add(controller,BorderLayout.NORTH);

        // Maak de views
        dobbelsteenView = new DobbelsteenView(Color.red);
        dobbelsteenView.setBackground(Color.black);
        getContentPane().add(dobbelsteenView, BorderLayout.CENTER);

        tekstView = new TekstView();
        tekstView.setBackground(Color.green);
        getContentPane().add(tekstView, BorderLayout.SOUTH);

        statistiekenView = new StatistiekenView();
        statistiekenView.setBackground(Color.white);
        getContentPane().add(statistiekenView, BorderLayout.EAST);

        // Registreer de views bij het model
        model.addActionListener(tekstView);
        model.addActionListener(dobbelsteenView);
        model.addActionListener(statistiekenView);

        // Eerste worp
        model.gooi();
    }
}