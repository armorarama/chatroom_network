/** * This program is a rudimentary demonstration of Swing GUI programming. * Note, the default layout manager for JFrames is the border layout. This * enables us to position containers using the coordinates South and Center. * * Usage: *	java ChatScreen * * When the user enters text in the textfield, it is displayed backwards  * in the display area. */import java.awt.*;import java.awt.event.*;import javax.swing.*;import javax.swing.border.*;import com.google.gson.Gson;import com.google.gson.GsonBuilder;import dealios.ChatroomBegin;import dealios.ChatroomBroadcast;import dealios.ChatroomEnd;import dealios.ChatroomSend;import java.io.*;import java.net.*;import java.util.ArrayList; public class ChatScreen extends JFrame implements ActionListener, KeyListener{	private JButton sendButton;	private JButton exitButton;	private JTextField sendText;	private JTextArea displayArea;		private static int inputCount = 0;	public static String myUsername = null;	private static Socket server = null;		public ChatScreen() {		/**		 * a panel used for placing components		 */		JPanel p = new JPanel();		Border etched = BorderFactory.createEtchedBorder();		Border titled = BorderFactory.createTitledBorder(etched, "Enter Message Here ...");		p.setBorder(titled);		/**		 * set up all the components		 */		sendText = new JTextField(30);		sendButton = new JButton("Send");		exitButton = new JButton("Exit");		/**		 * register the listeners for the different button clicks		 */		sendText.addKeyListener(this);		sendButton.addActionListener(this);		exitButton.addActionListener(this);		/**		 * add the components to the panel		 */		p.add(sendText);		p.add(sendButton);		p.add(exitButton);		/**		 * add the panel to the "south" end of the container		 */		getContentPane().add(p,"South");		/**		 * add the text area for displaying output. Associate		 * a scrollbar with this text area. Note we add the scrollpane		 * to the container, not the text area		 */		displayArea = new JTextArea(15,40);		displayArea.setEditable(false);		displayArea.setFont(new Font("SansSerif", Font.PLAIN, 14));		JScrollPane scrollPane = new JScrollPane(displayArea);		getContentPane().add(scrollPane,"Center");		/**		 * set the title and size of the frame		 */		setTitle("GUI Demo");		pack();		setVisible(true);		sendText.requestFocus();		/** anonymous inner class to handle window closing events */		addWindowListener(new WindowAdapter() {			public void windowClosing(WindowEvent evt) {				System.exit(0);			}		} );	}	/**	 * Displays a message	 */	public void displayMessage(String message) {		displayArea.append(message + "\n");	}	/**	 * This gets the text the user entered and outputs it	 * in the display area.	 */	public void displayText() {		String message = sendText.getText().trim();		StringBuffer buffer = new StringBuffer(message.length());		for (int i = 0; i < message.length(); i++)			buffer.append(message.charAt(i));		displayArea.append(buffer.toString() + "\n");		sendText.setText("");		sendText.requestFocus();	}	/**	 * This method responds to action events .... i.e. button clicks	 * and fulfills the contract of the ActionListener interface.	 */	public void actionPerformed(ActionEvent evt) {		Object source = evt.getSource();		if (source == sendButton) {			if(inputCount == 0){				myUsername = sendText.getText();				System.out.println(myUsername);				try {					this.chatroomBegin(server, myUsername);					System.out.println("sending");				} catch (IOException e) {				}				inputCount++;			}			else if(inputCount>0){				try {					this.chatroomSend(server, sendText.getText());				} catch (IOException e) {				}				//displayText();			}					}		else if (source == exitButton){			try {				this.chatroomEnd(server);				System.exit(0);			} catch (IOException e) {			}		//System.exit(0);		}	}	/**	 * These methods responds to keystroke events and fulfills	 * the contract of the KeyListener interface.	 */	/**	 * This is invoked when the user presses	 * the ENTER key.	 */	public void keyPressed(KeyEvent e) { 		if (e.getKeyCode() == KeyEvent.VK_ENTER){			if(inputCount == 0){								myUsername = sendText.getText();								//System.out.println(myUsername);				try {					this.chatroomBegin(server, myUsername);				} catch (IOException i) {				}				inputCount++;							}			else if (inputCount>0){				try {					this.chatroomSend(server, sendText.getText());				} catch (IOException e1) {				}			}						//displayText();		}	}	/** Not implemented */	public void keyReleased(KeyEvent e) { }	/** Not implemented */	public void keyTyped(KeyEvent e) {  }			public void chatroomBegin(Socket server, String username) throws IOException{		Gson gson = new GsonBuilder().create();		DataOutputStream out = new DataOutputStream(server.getOutputStream());		ChatroomBegin cb = new ChatroomBegin(username, username.length()-1);		String chatroomBegin = gson.toJson(cb) + "\r\n";		System.out.println(chatroomBegin);		out.writeBytes(chatroomBegin);		System.out.println("sent");		out.flush();	}		public void chatroomSend(Socket server, String message) throws IOException{		Gson gson = new GsonBuilder().create();		ArrayList<String> people = new ArrayList();		DataOutputStream out = new DataOutputStream(server.getOutputStream());		ChatroomSend cs = new ChatroomSend(myUsername, people, message, message.length());		String chatroomSend = gson.toJson(cs) + "\r\n";		out.writeBytes(chatroomSend);			out.flush();	}		public void chatroomEnd(Socket server) throws IOException{		Gson gson = new GsonBuilder().create();		DataOutputStream out = new DataOutputStream(server.getOutputStream());		ChatroomEnd ce = new ChatroomEnd(myUsername);		String chatroomEnd = gson.toJson(ce) + "\r\n";		out.writeBytes(chatroomEnd);		out.flush();	}	public static void main(String[] args) {						try {			server = new Socket(args[0], 8029);			ChatScreen win = new ChatScreen();			win.displayMessage("Enter Username:");						Thread ReaderThread = new Thread(new ReaderThread(server, win));			ReaderThread.start();			while(true){}								}		catch (UnknownHostException uhe) { System.out.println(uhe); }		catch (IOException ioe) { System.out.println(ioe); }	}}