import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Enumeration;

/**
 * Created by FakeYou on 17-2-14.
 */
public class Hierarchy {

    public Hierarchy() {
        DefaultMutableTreeNode person = new DefaultMutableTreeNode("person");
        DefaultMutableTreeNode employee = new DefaultMutableTreeNode("employee");
        DefaultMutableTreeNode sales_rep = new DefaultMutableTreeNode("sales_rep");
        DefaultMutableTreeNode engineer = new DefaultMutableTreeNode("engineer");
        DefaultMutableTreeNode customer = new DefaultMutableTreeNode("customer");
        DefaultMutableTreeNode us_customer = new DefaultMutableTreeNode("us_customer");
        DefaultMutableTreeNode non_us_customer = new DefaultMutableTreeNode("non_us_customer");
        DefaultMutableTreeNode local_customers = new DefaultMutableTreeNode("local_customer");
        DefaultMutableTreeNode regional_customers = new DefaultMutableTreeNode("regional_customer");

        person.add(employee);
        person.add(customer);

        employee.add(sales_rep);
        employee.add(engineer);

        customer.add(us_customer);
        customer.add(non_us_customer);

        us_customer.add(local_customers);
        us_customer.add(regional_customers);

        Enumeration enumeration;

        System.out.println("\nBreadthFirst enumation");
        System.out.println("=======================");
        enumeration = person.breadthFirstEnumeration();
        while(enumeration.hasMoreElements()) {
            System.out.println(enumeration.nextElement());
        }

        System.out.println("\nPreorder enumeration");
        System.out.println("=====================");
        enumeration = person.preorderEnumeration();
        while(enumeration.hasMoreElements()) {
            System.out.println(enumeration.nextElement());
        }

        System.out.println("\nPostorder enumartion");
        System.out.println("=====================");
        enumeration = person.postorderEnumeration();
        while(enumeration.hasMoreElements()) {
            System.out.println(enumeration.nextElement());
        }
    }

    public static void main(String[] args) {
        new Hierarchy();
    }
}
