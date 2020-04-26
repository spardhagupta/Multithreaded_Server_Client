/**
 * CSE 5344 � 001 - Fall 2019 - LAB 1
 * @author Spardha Gupta
 * 1001642027
 */

//Reference: Thread Tutorial from http://docs.oracle.com/javase/tutorial/essential/concurrency/runthread.html

public class ServerInitializer {

	//main method
	public static void main(String[] args) {

		//initialize port to default 8080
		int port = 8080;

		//check command line arguments for port
		if(args.length == 1)
		{
			//port is provided
			try {
				port = Integer.parseInt(args[0]); //check if port is valid integer
			}
			catch (NumberFormatException nfe)
			{
				System.err.println("[SERVER]> Integer Port is not provided. Server will start at default port.");
			}
		}

		System.out.println("[SERVER]> Using Server Port : " + port);

		//constructing Server object
		Server ws = new Server(port);

		//start Server in a new thread
		new Thread(ws).start();
	}
}
