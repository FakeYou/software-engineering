package classifier.gui;

import classifier.DecisionTree;
import classifier.FileReader;

import javax.swing.*;

/**
 * Created by FakeYou on 2/27/14.
 */
public class MVC extends JApplet {
    private DecisionTree tree;
    private FeaturesController featuresController;

    public void init() {
        FileReader fileReader = new FileReader("trainingSet.txt");
        tree = fileReader.buildTree();

        featuresController = new FeaturesController(tree);
        this.add(featuresController);
    }
}
