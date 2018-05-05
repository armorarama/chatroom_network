package dealios;

public class ChatroomError
{
	public String type;
	public String type_of_error;
	public String id;
	
	public ChatroomError()
	{
		this.type = "chatroom-error";
		this.type_of_error = null;
		this.id = null;
	}
	
	public ChatroomError(String type_of_error, String id)
	{
		this.type = "chatroom-error";
		this.type_of_error = type_of_error;
		this.id = id;
	}
	
	@Override
	public String toString() {
		return type + " " + type_of_error + " " + id;
	}

}
