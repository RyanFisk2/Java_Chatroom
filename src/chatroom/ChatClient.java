package chatroom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Ryan Fisk
 */
public class ChatClient extends ChatWindow{

	// Inner class used for networking
	private Communicator comm;

	// GUI Objects
	private JTextField serverTxt;
	private JTextField nameTxt;
	private JButton connectB;
	private JTextField messageTxt;
	private JButton sendB;

	public ChatClient() {
		super();
		this.setTitle("Chat Client");
		printMsg("Chat Client Started.");

		// GUI elements at top of window
		// Need a Panel to store several buttons/text fields
		serverTxt = new JTextField("localhost");
		serverTxt.setColumns(15);
		nameTxt = new JTextField("Name");
		nameTxt.setColumns(10);
		connectB = new JButton("Connect");
		JPanel topPanel = new JPanel();
		topPanel.add(serverTxt);
		topPanel.add(nameTxt);
		topPanel.add(connectB);
		contentPane.add(topPanel, BorderLayout.NORTH);

		// GUI elements and panel at bottom of window
		messageTxt = new JTextField("");
		messageTxt.setColumns(40);
		sendB = new JButton("Send");
		JPanel botPanel = new JPanel();
		botPanel.add(messageTxt);
		botPanel.add(sendB);
		contentPane.add(botPanel, BorderLayout.SOUTH);

		// Resize window to fit all GUI components
		this.pack();

		// Setup the communicator so it will handle the connect button
		Communicator comm = new Communicator();
		connectB.addActionListener(comm);
		sendB.addActionListener(comm);


	}

	/** This inner class handles communication with the server. */
	class Communicator implements ActionListener, Runnable{
		private Socket socket;
		private PrintWriter writer;
		private BufferedReader reader;
		private int port = 2113;
		Thread t = new Thread(this);

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if(actionEvent.getActionCommand().compareTo("Connect") == 0) {
				connect();
				t.start();
			}
			else if(actionEvent.getActionCommand().compareTo("Send") == 0) {
				sendMsg(messageTxt.getText());
			}
		}


		/** Connect to the remote server and setup input/output streams. */
		public void connect(){
			try {
				socket = new Socket(serverTxt.getText(), port);
				InetAddress serverIP = socket.getInetAddress();
				printMsg("Connection made to " + serverIP);
				writer = new PrintWriter(socket.getOutputStream(), true);
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				sendMsg("Hello server");
				//readMsg();

			}
			catch(IOException e) {
				printMsg("\nERROR:" + e.getLocalizedMessage() + "\n");
			}
		}
		/** Receive and display a message */
		public String readMsg() throws IOException {
			String s = "";
			try{
				s = reader.readLine();
			} catch (IOException e){
				System.out.println(e);
			}

			printMsg(s);
			return s;
		}
		/** Send a string */
		public void sendMsg(String s){

			if(s.substring(0, 5).equals("/name")) //client wants to change name
			{
				nameTxt.setText(s.substring(5, s.length())); //set name to everything after \name
				return;
			}

			writer.println("[" + nameTxt.getText() + "]: " + s);
			/*try{
				readMsg();
			} catch (IOException e){
				System.out.println(e);
			}*/
			
		}

		public void run()
		{
			while(true)
			{
				try{
					String s = readMsg();
				} catch (IOException e){
					System.out.println(e);
				}
			}
			
		}
	}

	public static void main(String[] argv)
	{
			new ChatClient();
	}
}

