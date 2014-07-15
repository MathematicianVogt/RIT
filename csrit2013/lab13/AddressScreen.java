import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * AddressScreen.java
 *
 * Version:
 *     $Id: AddressScreen.java,v 1.1 2013/25/21 23:50:05 rhv5251 Exp rhv5251 $
 *
 * Revisions:
 *     $Log: AddressScreen.java,v $
 *     Revision 1.1  2013/11/25 23:50:05  rhv5251
 *     Initial revision
 *
 */

/*
 * 
 * 
 * @author Ryan Vogt
 * A gui where users can input information
 * about their location
 */
public class AddressScreen extends JFrame {
	// class component objects
	private JPanel myPanel;
	private JPanel myPanel1;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JTextField field1;
	private JTextField field2;
	private JTextField field3;
	private JTextField field4;
	private JTextField field5;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;

	public AddressScreen() { // makes the GUI for the user to see
		Container myContainer = this.getContentPane();
		myContainer.setLayout(new BorderLayout());
		myPanel = new JPanel();
		button1 = new JButton("Add");
		button2 = new JButton("Modify");
		button3 = new JButton("Delete");
		myPanel.add(button1);
		myPanel.add(button2);
		myPanel.add(button3);
		myPanel1 = new JPanel();
		myPanel1.setLayout(new GridLayout(5, 2));

		field1 = new JTextField();
		field2 = new JTextField();
		field3 = new JTextField();
		field4 = new JTextField();
		field5 = new JTextField();
		label1 = new JLabel("Name");
		label2 = new JLabel("Address");
		label3 = new JLabel("City");
		label4 = new JLabel("State");
		label5 = new JLabel("Zip");
		label1.setForeground(new Color(0, 0, 128));
		label2.setForeground(new Color(0, 0, 128));
		label3.setForeground(new Color(0, 0, 128));
		label4.setForeground(new Color(0, 0, 128));
		label5.setForeground(new Color(0, 0, 128));
		myPanel1.add(label1);
		myPanel1.add(field1);
		myPanel1.add(label2);
		myPanel1.add(field2);
		myPanel1.add(label3);
		myPanel1.add(field3);
		myPanel1.add(label4);
		myPanel1.add(field4);
		myPanel1.add(label5);
		myPanel1.add(field5);
		myContainer.add(myPanel, BorderLayout.SOUTH);
		myContainer.add(myPanel1, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		// shows the gui to user
		AddressScreen myGUI = new AddressScreen();
		myGUI.setTitle("Address Information");
		myGUI.setSize(300, 150);
		myGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myGUI.setVisible(true);

	}

}
