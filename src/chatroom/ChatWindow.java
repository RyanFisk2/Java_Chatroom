package chatroom;

import javax.swing.*;
import java.awt.*;

/**
 * YOUR NAME HERE
 */
public class ChatWindow extends JFrame {

	private JTextArea textArea;
	protected Container contentPane;

	public ChatWindow(){

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setColumns(55);
		contentPane = this.getContentPane();
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JScrollPane textScroller = new JScrollPane(textArea);
		textScroller.setPreferredSize(new Dimension(400, 400));
		contentPane.add(textScroller);
		this.setLocation(500, 80);
		this.setVisible(true);
		this.setTitle("Chat Window");

	}

	/** Append a message to the text area and then scroll down so it is visible */
	public void printMsg(String s){
		textArea.append(s + "\n");
		textArea.setCaretPosition(textArea.getText().length());
	}

}
