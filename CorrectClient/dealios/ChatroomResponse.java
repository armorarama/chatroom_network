package dealios;

import java.util.ArrayList;

public class ChatroomResponse
{
	public String type;
	public String userID;
	public int clientNo;
	public ArrayList<String> users;

	public ChatroomResponse() {
		this.type = "chatroom-response";
		this.userID = null;
		this.clientNo = 0;
		this.users = new ArrayList<String>();
	}
	
	public ChatroomResponse(String userID, int clientNo, ArrayList<String> users) {
		this.type = "chatroom-response";
		this.userID = userID;
		this.clientNo = clientNo;
		this.users = users;
	}
	
	@Override
	public String toString() {
		return type + " - " + userID + " - " + clientNo + " " + users;
	}
}
