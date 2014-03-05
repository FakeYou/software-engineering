package classifier.treegui;

import classifier.DecisionTree;
import classifier.Node;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by FakeYou on 2/27/14.
 */
public class TreeView extends JPanel {
    private DecisionTree tree;

    public TreeView(DecisionTree tree) {
        this.tree = tree;

        this.setPreferredSize(new Dimension(2000, 1000));
        this.setLayout(null);

        buildView();
    }

    private void buildView() {
        Node root = tree.getRoot();

        System.out.println(root);

        drawNode(root, 1000, 40, 0, 1);
    }

    private void drawNode(Node node, int x, int y, int xOffset, int depth) {
        JLabel label = new JLabel(node.getLabel());

        label.setBounds(x - 50, y, 100, 20);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBackground(Color.lightGray);
        label.setOpaque(true);

        if(!node.isLeaf()) {
            Map<String, Node> arcs = node.getArcs();
            int children = arcs.size();
            int minX = (x - xOffset) / children;
            int stepX = (x - xOffset) / (children - 1);
            int step = 0;

            for (Iterator<String> iter = arcs.keySet().iterator(); iter.hasNext();) {
                String arcLabel = iter.next();
                Node dest = arcs.get(arcLabel);

                int newX = minX + (stepX * step);
                drawNode(dest, newX, y + 40, (stepX * step * depth), depth + 1);

                step++;
            }
        }

        this.add(label);
    }
}
