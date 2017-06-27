import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

/** Main GUI component for the NetWatch program.
 * @author	Ian F. Darwin, ian@darwinsys.com
 * @version	$Id: NetFrame.java,v 1.4 2001/02/17 20:15:07 ian Exp $
 * Copyright (c) 2000, Ian F. Darwin. See LEGAL.NOTICE for licensing.
 */
public class NetFrame extends JFrame {
	Properties props;
	Container cp;
	JDialog propsDialog;

	/** Constructor */
	public NetFrame(String title, Properties p) {
		super(title);

		props = p;
		cp = getContentPane();

		JMenuBar jb = new JMenuBar();
		JMenu jm;
		JMenuItem mi;

		// FILE MENU
		jb.add(jm = new JMenu("File"));
		jm.add(mi = new JMenuItem("Exit"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		// VIEW MENU
		jb.add(jm = new JMenu("Edit"));
		jm.add(mi = new JMenuItem("Properties..."));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (propsDialog == null) {
					propsDialog = new ProtoDialog(NetFrame.this, "Properties");
				}
				propsDialog.setVisible(true);
				// TODO fetch protocol

			}
		});

		// HELP MENU
		jb.add(jm = new JMenu("Help"));
		jm.add(mi = new JMenuItem("About"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(NetFrame.this,
					"NetWatch Application -- $Version$\n" +
					"Copyright (c) 2000 Ian F. Darwin\n" +
					"See LEGAL.NOTICE for licensing.",
					"RMIWatch",
					JOptionPane.INFORMATION_MESSAGE);
			}
		});
		this.setJMenuBar(jb);

		cp.setLayout(new GridLayout(0,1));
	}

	/** CONSTRUCT PANELS, ONE FOR EACH HOST. */
	protected void addHost(String hostName, Properties props) {
		cp.add(new RMIPanel(hostName, props));

		// If asked for ncolumns, make it so.
		// Else If it got too tall, make it multi columns.
		String nc = props.getProperty("netwatch.gui.columns");
		if (nc != null) {
			int n = Integer.parseInt(nc);
			cp.setLayout(new GridLayout(0, n));
		} else if (cp.getComponents().length > 12)
			cp.setLayout(new GridLayout(0,3));
		else if (cp.getComponents().length > 6)
			cp.setLayout(new GridLayout(0,2));
	}
}
