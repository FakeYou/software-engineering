package classifier;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by FakeYou on 2/27/14.
 */
public class FileReader {

    private String filename;
    private DecisionTree tree;

    public FileReader(String filename) {
        this.filename = filename;
    }

    public DecisionTree getTree() {
        return tree;
    }

    public DecisionTree buildTree() {
        Map<Item, String> trainingSet = new HashMap<Item, String>();
        Map<String, FeatureType> featureSet = new HashMap<String, FeatureType>();
        ArrayList<String> featureNames = new ArrayList<String>();

        FeatureType yn = new FeatureType("YesNo",new String[]{"ja","nee"});

        int extraLines = 0;
        int numfeatures = 0;
        int numItems = 0;

        URL url = getClass().getResource("trainingSets/" + filename);
        File file = new File(url.getPath());
        Scanner scanner;

        try {
            scanner = new Scanner(file);
            scanner.useDelimiter(";");
        }
        catch (FileNotFoundException e) {
            System.out.println(e);
            return tree;
        }

        try {
            int lines = 0;
            while(scanner.hasNextLine()) {
                String current = scanner.nextLine();
                lines += 1;

                String[] parts = current.split(";");

                if(parts[0].equals("Features")) {
                    extraLines += 1;
                    numfeatures = Integer.parseInt(parts[1]);

                    if(numfeatures <= 0) {
                        throw new Exception("Invalid amount of features");
                    }

                    featureSet.clear();
                    featureNames.clear();
                    for(int i = 0; i < numfeatures; i++) {
                        // Generate a letter of the alphabet for every feature starting with A
                        String name = Character.toString((char) (65 + i));
                        featureSet.put(name, yn);
                        featureNames.add(name);
                    }
                }
                else if(parts[0].equals("FeatureNames")) {
                    extraLines += 1;
                    numfeatures = parts.length - 1;

                    if(parts.length <= 0) {
                        throw new Exception("Invalid amount of features");
                    }

                    featureSet.clear();
                    featureNames.clear();
                    for(int i = 0; i < parts.length - 1; i++) {
                        featureSet.put(parts[i + 1], yn);
                        featureNames.add(parts[i + 1]);
                    }
                }
                else if(parts[0].equals("Items")) {
                    extraLines += 1;
                    numItems = Integer.parseInt(parts[1]);

                    if(numItems <= 0) {
                        throw new Exception("Invalid amount of items");
                    }
                }
                else {
                    if(parts.length != numfeatures + 2) {
                        throw new Exception("Invalid amount of values in line \"" + current + "\"");
                    }

                    Feature[] featureValues = new Feature[numfeatures];

                    for(int i = 0; i < parts.length - 2; i++) {
                        String name = featureNames.get(i);
                        String value = parts[i + 1];

                        if(parts[i + 1].equals("0")) { value = "nee"; };
                        if(parts[i + 1].equals("1")) { value = "ja"; };

                        featureValues[i] = new Feature(name, value, yn);
                    }

                    Item item = new Item(parts[0], featureValues);
                    trainingSet.put(item, parts[parts.length - 1]);
                }
            }

            if(numItems + extraLines != lines) {
                throw new Exception("Incorrect amount of items defined, defined: " + numItems + ", found: " + lines);
            }

            if(Math.pow(2, numfeatures) != numItems) {
                throw new Exception("Not enough data to build DecisionTree");
            }

            tree = new DecisionTree(trainingSet, featureSet);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return tree;
    }
}
