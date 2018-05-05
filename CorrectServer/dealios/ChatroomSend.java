package dealios;

import java.util.ArrayList;

public class ChatroomSend
{
	public String type;
	public String from;
	public ArrayList<String> to;
	public String message;
	public int message_length;

	public ChatroomSend() {
		this.type = "chatroom-send";
		this.from = null;
		this.to = new ArrayList<String>();
		this.message = null;
		this.message_length = 0;
	}
	
	public ChatroomSend(String from, ArrayList<String> to, String message, int message_length) {
		this.type = "chatroom-send";
		this.from = from;
		this.to = to;
		this.message = message;
		this.message_length = message_length;
	}
	
	@Override
	public String toString() {
		return type + " - " + from + " - " + to + " " + message + " " + message_length;
	}

}
