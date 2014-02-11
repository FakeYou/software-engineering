import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by FakeYou on 2/11/14.
 */
public class StatistiekenView extends JPanel implements ActionListener {

    private JTextField[] worpVelden = new JTextField[6];
    DobbelsteenModel d;

    public StatistiekenView() {
        this.setLayout(new GridLayout(7, 2));

        this.add(new Label("Waarde"));
        this.add(new Label("Worpen"));

        for(int i = 1; i <= 6; i++) {
            worpVelden[i - 1] = new JTextField();
            worpVelden[i - 1].setText("0 keer");

            this.add(new Label(i + ":"));
            this.add(worpVelden[i - 1]);
        }

    }

    public void actionPerformed(ActionEvent e) {
        d = (DobbelsteenModel) e.getSource();

        int[] gegooideWaarden = d.getGegooideWaarden();

        for(int i = 0; i < gegooideWaarden.length; i++) {
            worpVelden[i].setText(gegooideWaarden[i] + " keer");
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(150, 50);
    }
}
