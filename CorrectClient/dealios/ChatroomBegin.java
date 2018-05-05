package dealios;

public class ChatroomBegin
{
	public String type;
	public String username;
	public int len;

	public ChatroomBegin() {
		this.type = "chatroom-begin";
		this.username = null;
		this.len = 0;
	}
	
	public ChatroomBegin(String username, int len) {
		this.type = "chatroom-begin";
		this.username = username;
		this.len = len;
	}
	
	@Override
	public String toString() {
		return type + " - " + username + " - " + len;
	}
}
