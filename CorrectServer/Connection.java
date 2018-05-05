/**
 * This is the separate thread that services each
 * incoming echo client request.
 *
 * @author Greg Gagne 
 */

import java.net.*;

public class Connection implements Runnable
{
	private Socket	client;
	private static Handler handler = new Handler();
	
	public Connection(Socket client) {
		this.client = client;
		System.out.println("Client accepted");
	}

    /**
     * This method runs in a separate thread.
     */	
	public void run() { 
		try {
			handler.process(client);
		}
		catch (java.io.IOException ioe) {
			System.err.println(ioe);
		}
	}
}

