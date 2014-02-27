package classifier.gui;

import classifier.DecisionTree;
import classifier.Feature;
import classifier.FeatureType;
import classifier.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Created by FakeYou on 2/27/14.
 */
public class FeaturesController extends JPanel implements ActionListener {
    private final DecisionTree tree;
    private ArrayList<Feature> features;

    public FeaturesController(final DecisionTree tree) {
        this.tree = tree;

        this.setLayout(new GridLayout(0, 2));

        Map<String, FeatureType> featuresTypes = tree.getFeatures();
        features = new ArrayList<Feature>();

        for(Map.Entry<String, FeatureType> feature : featuresTypes.entrySet()) {
            JLabel label = new JLabel(feature.getKey());

            Object[] featureTypes = feature.getValue().allowedValues().toArray();

            JComboBox dropdown = new JComboBox(featureTypes);
            dropdown.setName("dropdown" + feature.getKey());
            dropdown.addActionListener(this);

            features.add(new Feature(feature.getKey(), (String) featureTypes[0], feature.getValue()));

            this.add(label);
            this.add(dropdown);
        }

        JButton submit = new JButton("Find");
        this.add(new JLabel());
        this.add(submit);

        this.add(new JLabel());
        final JLabel category = new JLabel("");
        this.add(category);

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Item item = new Item("test", features.toArray(new Feature[0]));
                category.setText(tree.assignCategory(item));
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        JComboBox dropdown = (JComboBox) e.getSource();
        String featureName = dropdown.getName().replace("dropdown", "");

        for(Feature feature : features) {
            if(feature.getName().equals(featureName)) {
                feature.setValue(dropdown.getSelectedItem().toString());
            }
        }
    }

    public ArrayList<Feature> getFeatures() {
        return features;
    }

    public Dimension getPrefferedSize() {
        return new Dimension(400, 400);
    }
}
