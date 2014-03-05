package classifier.treegui;

import classifier.DecisionTree;
import classifier.FileReader;

import javax.swing.*;
import java.awt.*;

/**
 * Created by FakeYou on 2/27/14.
 */
public class Applet extends JApplet {
    private DecisionTree tree;
    private TreeView treeView;

    public void init() {
        FileReader fileReader = new FileReader("trainingSet.txt");
        tree = fileReader.buildTree();

        treeView = new TreeView(tree);

        JScrollPane mainView = new JScrollPane(treeView);
        mainView.setPreferredSize(new Dimension(800, 600));
        this.add(mainView);
    }
}
