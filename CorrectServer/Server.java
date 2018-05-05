/**
 * A web server listening on port 8080. 
 *
 * This services each request in a separate thread.
 *
 * @author - Greg Gagne, Edited by Emily Mason
 */

import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;


public class  Server
{
	public static final int DEFAULT_PORT = 8029;
	public static Map<String, Socket> users = new HashMap<String, Socket>();
	public static int counter = 0; 
	
    // construct a thread pool for concurrency	
	
	public static void main(String[] args) throws IOException {
	
		Executor exec = Executors.newCachedThreadPool();	

		ServerSocket sock = null;

        System.out.println("Listening at port 8029");
		
		try {
			// establish the socket
			sock = new ServerSocket(DEFAULT_PORT);
			
			while (true) {
				/**
				 * now listen for connections
				 * and service the connection in a separate thread.
				 */
				Runnable task = new Connection(sock.accept());
				exec.execute(task);
			}
		}
		catch (IOException ioe) { }
//		finally {
//			if (sock != null)
//				sock.close();
//		}
	}
}