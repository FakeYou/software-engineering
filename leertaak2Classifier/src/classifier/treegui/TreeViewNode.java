package classifier.treegui;

import javax.swing.*;

/**
 * Created by FakeYou on 3/3/14.
 */
public class TreeViewNode extends JLabel {

    private int x;
    private int y;

    public TreeViewNode(String label, int x, int y) {
        super(label);

        setPosition(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
