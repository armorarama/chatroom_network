package dealios;

public class ChatroomUpdate
{
	public String type;
	public String type_of_update;
	public String id;
	
	public ChatroomUpdate()
	{
		this.type = "chatroom-update";
		this.type_of_update = null;
		this.id = null;
	}
	
	public ChatroomUpdate(String type_of_update, String user)
	{
		this.type = "chatroom-update";
		this.type_of_update = type_of_update;
		this.id = user;
	}

	@Override
	public String toString() {
		return type + " " + type_of_update + " " + id;
	}
}
