package peevedFowls;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

public class MyFrame extends JFrame 
{
	static String text;
	MyPanel panel;
	JButton button;
	JTextField textField;
	public static String returnText()
	{
		return text;
	}
	MyFrame() 
	{
		panel = new MyPanel();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(panel);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	
}
