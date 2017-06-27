import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/** A simple demo of a JFileChooser in action. */
public class JFileChooserDemo extends JPanel {

	/** Constructor */
	public JFileChooserDemo(JFrame f) {
		final JFrame frame = f;
		final JFileChooser chooser = new JFileChooser();

		// If want the user to select only directories, use this.
		// Default is to allow selection of files only.
		// Note if you set the selection mode to DIRECTORIES_ONLY,
		// it no longer displays any files, even with the file view.

		// chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		// If want it to only show certain file types, use a FileFilter.
		// N.B. JFileFilter is not in javax.swing; it is my implementation
		// of interface javax.swing.filechooser.FileFilter, and is similar
		// to the ExtentionFilter in demo/jfc accompanying the J2SE SDK.
		JFileFilter filter = new JFileFilter();
		filter.addType("java");
		filter.addType("class");
		filter.addType("jar");
		filter.setDescription("Java-related files");
		chooser.addChoosableFileFilter(filter);
		JButton b = new JButton("Choose file...");
		add(b);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			int returnVal = chooser.showOpenDialog(frame);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile();
				System.out.println("You chose a " + 
					(file.isFile() ? "file" : "directory") +
					" named: " + file.getPath());
			} else {
				System.out.println("You did not choose a filesystem object.");
			}
			}
		});
	}


	public static void main(String[] args) {
		JFrame f = new JFrame("JFileChooser Demo");
		f.getContentPane().add(new JFileChooserDemo(f));
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
