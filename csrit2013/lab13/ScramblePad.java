import java.awt.*;

import javax.swing.*;

/*
 * ScramblePad.java
 *
 * Version:
 *     $Id: ScramblePad.java,v 1.1 2013/11/25 23:55:08 rhv5251 Exp rhv5251 $
 *
 * Revisions:
 *     $Log: ScramblePad.java,v $
 *     Revision 1.1  2013/11/25 23:55:08  rhv5251
 *     Initial revision
 *
 */

/*
 * 
 * 
 * @author Ryan Vogt
 * A gui of buttons with colors
 */
public class ScramblePad extends JFrame {
	// class components for gui
	private JPanel panel;
	private JPanel panel1;
	private JPanel panel2;
	private Container myContainer;
	private JLabel label;
	private JButton button;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JButton button6;
	private JButton button7;
	private JButton button8;
	private JButton button9;
	private JButton button10;
	private JButton button11;
	private JButton button12;
	private JButton button13;

	public ScramblePad() {
		// makes a gui for the user to see
		panel = new JPanel();
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(4, 3));
		panel2 = new JPanel();
		myContainer = new Container();
		myContainer.setLayout(new BorderLayout());
		panel.setBackground(Color.BLACK);
		panel1.setBackground(Color.BLACK);
		panel2.setBackground(Color.BLACK);
		label = new JLabel("LOCKED");
		label.setForeground(Color.RED);
		panel.add(label);
		button = new JButton("1");
		button.setForeground(Color.YELLOW);
		button.setBackground(Color.BLACK);
		panel1.add(button);
		button1 = new JButton("2");
		button1.setForeground(Color.YELLOW);
		button1.setBackground(Color.BLACK);
		panel1.add(button1);
		button2 = new JButton("3");
		button2.setForeground(Color.YELLOW);
		button2.setBackground(Color.BLACK);
		panel1.add(button2);
		button3 = new JButton("4");
		button3.setForeground(Color.YELLOW);
		panel1.add(button3);
		button3.setBackground(Color.BLACK);
		button4 = new JButton("5");
		button4.setForeground(Color.YELLOW);
		button4.setBackground(Color.BLACK);
		panel1.add(button4);
		button5 = new JButton("6");
		button5.setForeground(Color.YELLOW);
		button5.setBackground(Color.BLACK);
		panel1.add(button5);
		button6 = new JButton("7");
		button6.setForeground(Color.YELLOW);
		button6.setBackground(Color.BLACK);
		panel1.add(button6);
		button7 = new JButton("8");
		button7.setForeground(Color.YELLOW);
		button7.setBackground(Color.BLACK);
		panel1.add(button7);
		button8 = new JButton("9");
		button8.setForeground(Color.YELLOW);
		button8.setBackground(Color.BLACK);
		panel1.add(button8);
		button9 = new JButton("");
		button9.setForeground(Color.YELLOW);
		button9.setBackground(Color.BLACK);
		panel1.add(button9);
		button10 = new JButton("0");
		button10.setForeground(Color.YELLOW);
		button10.setBackground(Color.BLACK);
		panel1.add(button10);
		button11 = new JButton("");
		button11.setForeground(Color.YELLOW);
		button11.setBackground(Color.BLACK);
		panel1.add(button11);
		button12 = new JButton("Start");
		button13 = new JButton("Okay");
		panel2.add(button12);
		panel2.add(button13);
		myContainer.add(panel, BorderLayout.NORTH);
		myContainer.add(panel1, BorderLayout.CENTER);
		myContainer.add(panel2, BorderLayout.SOUTH);

		this.add(myContainer);

	}

	public static void main(String[] args) {
		// shows the user the gui
		ScramblePad myPad = new ScramblePad();
		myPad.setTitle("DCS Scramble Pad");
		myPad.setSize(200, 300);
		myPad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myPad.setVisible(true);

	}

}
