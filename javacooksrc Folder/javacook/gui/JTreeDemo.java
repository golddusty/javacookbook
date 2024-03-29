import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;

/** Simple JFC JTree demo
 * @version $Id: JTreeDemo.java,v 1.3 2000/11/25 17:55:15 ian Exp $
 */
public class JTreeDemo extends JFrame {
	JButton addButton, quitButton;
	JTree myTree;
	DefaultMutableTreeNode root, child;

	/** "main program" method - construct and show */
	public static void main(String[] av) {
		// create a JTreeDemo object, tell it to show up
		new JTreeDemo().setVisible(true);
	}

	/** Construct the object including its GUI */
	public JTreeDemo() {
		super("JTreeDemo");
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
			
		root = new DefaultMutableTreeNode("root");

		child = new DefaultMutableTreeNode("Colors"); 
		root.add(child);
		child.add(new DefaultMutableTreeNode("Cyan"));
		child.add(new DefaultMutableTreeNode("Magenta"));
		child.add(new DefaultMutableTreeNode("Yellow"));
		child.add(new DefaultMutableTreeNode("Black"));

		myTree = new JTree(root);

		cp.add(BorderLayout.CENTER, myTree);

		cp.add(BorderLayout.NORTH, addButton = new JButton("Add"));
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			// Insert more nodes into the tree
			child = new DefaultMutableTreeNode("Flavors");
			child.add(new DefaultMutableTreeNode("Java"));
			child.add(new DefaultMutableTreeNode("Espresso"));
			child.add(new DefaultMutableTreeNode("Hey Joe!"));
			child.add(new DefaultMutableTreeNode("Charcoal"));
			child.add(new DefaultMutableTreeNode("Paint Remover"));

			// Notify the model, which will add it and create an event, and
			// send it up the tree...

			((DefaultTreeModel)myTree.getModel()).insertNodeInto(child, root, 0);
			}
		});

		cp.add(BorderLayout.SOUTH, quitButton = new JButton("Exit"));
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});
		pack();
	}
}
