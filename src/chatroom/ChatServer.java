package chatroom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * YOUR NAME HERE
 */
public class ChatServer extends ChatWindow {

	private ClientHandler handler;

	public ChatServer(){
		super();
		this.setTitle("Chat Server");
		this.setLocation(80,80);

		try {
			// Create a listening service for connections
			// at the designated port number.
			ServerSocket srv = new ServerSocket(2113);

			while (true) {
				// The method accept() blocks until a client connects.
				printMsg("Waiting for a connection");
				Socket socket = srv.accept();

				handler = new ClientHandler(socket);
				handler.handleConnection();
			}

		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/** This innter class handles communication to/from one client. */
	class ClientHandler {
		private PrintWriter writer;
		private BufferedReader reader;

		public ClientHandler(Socket socket) {
			try {
				InetAddress serverIP = socket.getInetAddress();
				printMsg("Connection made to " + serverIP);
				writer = new PrintWriter(socket.getOutputStream(), true);
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			}
			catch (IOException e){
					printMsg("\nERROR:" + e.getLocalizedMessage() + "\n");
				}
		}
		public void handleConnection() {
			try {
				while(true) {
					// read a message from the client
					readMsg();
				}
			}
			catch (IOException e){
				printMsg("\nERROR:" + e.getLocalizedMessage() + "\n");
			}
		}

		/** Receive and display a message */
		public void readMsg() throws IOException {
			String s = reader.readLine();
			printMsg(s);
		}
		/** Send a string */
		public void sendMsg(String s){
			writer.println(s);
		}

	}

	public static void main(String args[]){
		new ChatServer();
	}
}
