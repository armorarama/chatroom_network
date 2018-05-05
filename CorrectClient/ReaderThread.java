/**
 * This thread is passed a socket that it reads from. Whenever it gets input
 * it writes it to the ChatScreen text area using the displayMessage() method.
 */

import java.io.*;
import java.net.*;
import javax.swing.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dealios.ChatroomBroadcast;
import dealios.ChatroomEnd;
import dealios.ChatroomResponse;
import dealios.ChatroomUpdate;

public class ReaderThread implements Runnable
{
	Socket server;
	BufferedReader fromServer;
	ChatScreen screen;
	String message = null;

	public ReaderThread(Socket server, ChatScreen screen) {
		this.server = server;
		this.screen = screen;
	}

	public void run() {
		
		try {
			fromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));
			
		  	
			
			while((message = fromServer.readLine()) != null){
			
				System.out.println(message);
				
				if(message.contains("chatroom-response")){
					Gson gson = new GsonBuilder().create();
					
					ChatroomResponse cr = gson.fromJson(message, ChatroomResponse.class);
					ChatScreen.myUsername = ChatScreen.myUsername + ":" + cr.userID;
					
				}
				
				else if (message.contains("chatroom-broadcast")){
					Gson gson = new GsonBuilder().create();
					
					ChatroomBroadcast cb = gson.fromJson(message, ChatroomBroadcast.class);
					screen.displayMessage(cb.message);
					
				}
				
				else if(message.contains("chatroom-update")){
					Gson gson = new GsonBuilder().create();
					
					ChatroomUpdate cu = gson.fromJson(message, ChatroomUpdate.class);
					
					screen.displayMessage(cu.id + "  " + cu.type_of_update);
				}

			
			
			
			}
		}
		
		catch (IOException ioe) { ioe.printStackTrace(); }

	
	}
}
