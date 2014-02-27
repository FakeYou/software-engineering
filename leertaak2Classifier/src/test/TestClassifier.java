package test;
import classifier.*;
import junit.framework.TestCase;



public class TestClassifier extends TestCase {

	public TestClassifier(String arg0) {
		super(arg0);
	}

    private DecisionTree buildTree(){
        // leaves
        Node leafHigh = new Node("high");
        Node leafMed = new Node("medium");
        Node leafLow = new Node("low");

        Node AcNode = new Node("AC");

        Node AbsNode1 = new Node("ABS");
        Node AbsNode2 = new Node("ABS");

        Node CruiseNode1 = new Node("Cruise");
        Node CruiseNode2 = new Node("Cruise");
        Node CruiseNode3 = new Node("Cruise");
        Node CruiseNode4 = new Node("Cruise");

        AcNode.addChild("yes", AbsNode1);
        AcNode.addChild("no", AbsNode2);

        AbsNode1.addChild("yes", CruiseNode1);
        AbsNode1.addChild("no", CruiseNode2);

        AbsNode2.addChild("yes", CruiseNode3);
        AbsNode2.addChild("no", CruiseNode4);

        CruiseNode1.addChild("yes", leafHigh.clone());  // AC yes, ABS yes, Cruise yes = high
        CruiseNode1.addChild("no", leafMed.clone());    // AC yes, ABS yes, Cruise no  = med
        CruiseNode2.addChild("yes", leafMed.clone());   // AC yes, ABS no,  Cruise yes = med
        CruiseNode2.addChild("no", leafMed.clone());    // AC yes, ABS no,  Cruise no  = med
        CruiseNode3.addChild("yes", leafMed.clone());   // AC no,  ABS yes, Cruise yes = med
        CruiseNode3.addChild("no", leafMed.clone());    // AC no,  ABS yes, Cruise no  = med
        CruiseNode4.addChild("yes", leafLow.clone());   // AC no,  ABS no,  Cruise yes = low
        CruiseNode4.addChild("no", leafLow.clone());    // AC no,  ABS no,  Cruise no  = low
		
		return new DecisionTree(AcNode);
    }
	public void testCategory(){
		
		DecisionTree dt = buildTree();

		FeatureType yn = new FeatureType("YesNo"
						,new String[]{"yes","no"});

		Feature[] features = new Feature[] {
            new Feature("AC","yes",yn),
		    new Feature("ABS","yes",yn),
            new Feature("Cruise","yes",yn),
        };
		
		Item item = new Item("car",features);

		String category = dt.assignCategory(item);
		assertEquals("high",category);

		item.setFeatureValue("AC","no");
		category = dt.assignCategory(item);
		assertEquals("medium",category);

		item.setFeatureValue("ABS","no");
		category = dt.assignCategory(item);
		assertEquals("low",category);

        item.setFeatureValue("Cruise","no");
        category = dt.assignCategory(item);
        assertEquals("low",category);
	}
}
