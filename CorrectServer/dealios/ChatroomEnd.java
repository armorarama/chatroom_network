package dealios;

public class ChatroomEnd
{
	public String type;
	public String id;
	
	public ChatroomEnd()
	{
		this.type = "chatroom-end";
		this.id = null;
	}
	
	public ChatroomEnd(String username)
	{
		this.type = "chatroom-end";
		this.id = username;
	}
	
	@Override 
	public String toString() {
		return type + " " + id;
	}

}
