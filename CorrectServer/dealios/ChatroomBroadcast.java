package dealios;

import java.util.ArrayList;

public class ChatroomBroadcast
{
	public String type;
	public String from;
	public ArrayList<String> to;
	public String message;
	public int message_length;
	
	public ChatroomBroadcast()
	{
		this.type = "chatroom-broadcast";
		this.from = null;
		this.to = new ArrayList<String>();
		this.message = null;
		this.message_length = 0;
	}
	
	public ChatroomBroadcast(String from, ArrayList<String> to, String message, int message_length)
	{
		this.type = "chatroom-broadcast";
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
