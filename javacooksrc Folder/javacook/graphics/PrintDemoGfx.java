import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import javax.swing.*;

/** PrintDemoGfx -- Construct and print a GfxDemoCanvas. 
 * Java 2 VERSION, using a PrinterJob.
 */
public class PrintDemoGfx {

	/** Simple demo main program. */
	public static void main(String[] av) throws PrinterException {
		final JFrame f = new JFrame("Printing Test Dummy Frame");

		// Construct the object we want to print. Contrived:
		// this object would already exist in a real program.
		final GfxDemoCanvas thing = new GfxDemoCanvas(400, 300);

		f.getContentPane().add(thing, BorderLayout.CENTER);

		JButton printButton = new JButton("Print");
		f.getContentPane().add(printButton, BorderLayout.SOUTH);

		printButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PrinterJob pjob = PrinterJob.getPrinterJob();
					pjob.setJobName("DemoGfx - Graphics Demo Printout");
					pjob.setCopies(1);
					// Tell the print system how to print our pages.
					pjob.setPrintable(new Printable() {
						/** called from the printer system to print each page */
						public int print(Graphics pg, PageFormat pf, int pageNum) {
							if (pageNum>0)		// we only print one page
								return Printable.NO_SUCH_PAGE;	// ie., end of job

							// Now (drum roll please), ask "thing" to paint itself
							// on the printer, by calling its paint() method with 
							// a Printjob Graphics instead of a Window Graphics.
							thing.paint(pg);

							// Tell print system that the page is ready to print
							return Printable.PAGE_EXISTS;
						}
					});

					if (pjob.printDialog() == false)	// choose printer
						return;				// user cancelled

					pjob.print();			 // Finally, do the printing.
				} catch (PrinterException pe) {
					JOptionPane.showMessageDialog(f,
						"Printer error" + pe, "Printing error",
						JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		f.pack();
		f.setVisible(true);
	}
}
