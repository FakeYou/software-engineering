package classifier.treegui;

import classifier.DecisionTree;
import classifier.Node;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by FakeYou on 3/3/14.
 */
public class TreeView extends JPanel {
    private DecisionTree tree;
    private ArrayList<TreeViewNode> nodes;

    public TreeView(DecisionTree tree) {
        this.tree = tree;
        nodes = new ArrayList<TreeViewNode>();

        this.setPreferredSize(new Dimension(2000, 1000));
        this.setLayout(null);

        buildView();
    }

    private void buildView() {
        Node root = tree.getRoot();

        System.out.println(root);

        drawNode(root, 50, 250, 0);
    }

    private void drawTree() {

    }

    private void drawNode(Node node, int x, int y, int yOffset) {
        JLabel label = new JLabel(node.getLabel());

        label.setBounds(x, y - 10, 100, 20);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        if(!node.isLeaf()) {
            Map<String, Node> arcs = node.getArcs();
            int children = arcs.size();
            int minY = (y / children) + yOffset;
            int stepY = (y - yOffset) / (children - 1);
            int step = 0;

            for (Iterator<String> iter = arcs.keySet().iterator(); iter.hasNext();) {
                String arcLabel = iter.next();
                Node dest = arcs.get(arcLabel);

                System.out.println(node.getLabel() + (step * stepY));

                int newY = minY + (stepY * step);
                drawNode(dest, x + 100, newY, (newY - y) / 2);

                step++;
            }
        }

        this.add(label);
    }

    private void drawLine(int xs, int ys, int dx, int dy) {

    }
}
