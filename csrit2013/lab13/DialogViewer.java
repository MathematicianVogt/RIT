import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * DialogViewer.java
 *
 * Version:
 *     $Id: DialogViewer.java,v 1.1 2013/11/21 22:40:13 rhv5251 Exp rhv5251 $
 *
 * Revisions:
 *     $Log: DialogViewer.java,v $
 *     Revision 1.1  2013/11/21 22:40:13  rhv5251
 *     Initial revision
 *
 */
/*
 * 
 * 
 * 
 * 
 * @author Ryan Vogt
 * A program that will call different
 * methods based on command line input
 * to show different dialog messages
 */
public class DialogViewer extends JFrame {
	// class variables
	private JFileChooser file;
	private JOptionPane pane;
	private JFrame myFrame;

	// method will show a file chooser option
	// if file is found show message name of file to user
	// if not tell user no file chosen
	public void fMethod() {
		file = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"JPG & GIF Images", "jpg", "gif", "txt");
		file.setFileFilter(filter);
		int returnVal = file.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: "
					+ file.getSelectedFile().getName());
		} else {
			System.out.println("No file choosen");

		}

	}

	// makes a diaglog box of yes,no,cancle
	// prints out the corresponding name of button pressed
	public void cMethod() {
		pane = new JOptionPane();
		int dialogButton = pane.YES_NO_CANCEL_OPTION;
		int dialogResult = pane.showConfirmDialog(null, "Are You Sure?",
				"Notice", dialogButton);
		if (dialogResult == pane.YES_OPTION) {
			System.out.println("Yes");

		} else if (dialogResult == pane.NO_OPTION) {

			System.out.println("No");

		} else {
			System.out.println("Cancle");

		}

	}

	// shows my name in a dialog box
	public void mMethod() {
		pane = new JOptionPane();
		pane.showMessageDialog(null, "Ryan Vogt");

	}

	public static void main(String[] args) {
		// main method, will call methods based on
		// command line arguements given.
		DialogViewer myView = new DialogViewer();
		if (args.length > 1) {

			System.err.println("Bad User Input");

		} else if (args[0].equals("f")) {
			myView.fMethod();

		} else if (args[0].equals("c")) {
			myView.cMethod();

		} else if (args[0].equals("m")) {
			myView.mMethod();

		} else {
			System.err.println("Bad User Input");

		}

	}

}
