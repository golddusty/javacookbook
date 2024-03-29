import java.awt.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.*;

/** Taken from the Sun documentation on Clipboard API */
public class ClipboardTest extends Frame 
                           implements ClipboardOwner, ActionListener {

    TextArea srcText, dstText;
    Button copyButton, pasteButton;

    Clipboard clipboard = getToolkit().getSystemClipboard();

    public ClipboardTest() {
        super("Clipboard Test");
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(gridbag);

        srcText = new TextArea(8, 32);
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        gridbag.setConstraints(srcText, c);
        add(srcText);

        copyButton = new Button("Copy Above");
        copyButton.setActionCommand("copy");
        copyButton.addActionListener(this);
        c.gridy = 1;
        c.gridwidth = 1;
        gridbag.setConstraints(copyButton, c);
        add(copyButton);

        pasteButton = new Button("Paste Below");
        pasteButton.setActionCommand("paste");
        pasteButton.addActionListener(this);
        pasteButton.setEnabled(false);
        c.gridx = 1;
        gridbag.setConstraints(pasteButton, c);
        add(pasteButton);

        dstText = new TextArea(8, 32);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        gridbag.setConstraints(dstText, c);
        add(dstText); 

        pack();
    }

    public void actionPerformed(ActionEvent evt) {
        String cmd = evt.getActionCommand();

        if (cmd.equals("copy")) { 
           // Implement Copy operation
           String srcData = srcText.getText();
           if (srcData != null) {
                StringSelection contents = new StringSelection(srcData);
                clipboard.setContents(contents, this);
                pasteButton.setEnabled(true);
            }
        } else if (cmd.equals("paste")) {
            // Implement Paste operation
            Transferable content = clipboard.getContents(this);
            if (content != null) {
                try {
                    String dstData = (String)content.getTransferData(
                                                DataFlavor.stringFlavor);
                    dstText.append(dstData);
                } catch (Exception e) {
                    System.out.println("Couldn't get contents in format: "+
                           DataFlavor.stringFlavor.getHumanPresentableName()); 
                }
             }
        }
    }
    public void lostOwnership(Clipboard clipboard, Transferable contents) {
       System.out.println("Clipboard contents replaced");
    }
     public static void main(String[] args) {
        ClipboardTest test = new ClipboardTest();
        test.setVisible(true);
     }
}
