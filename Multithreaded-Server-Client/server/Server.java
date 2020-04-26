/**
 * CSE 5344 � 001 - Fall 2019 - LAB 1
 * @author Spardha Gupta
 * 1001642027
 */

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

//Reference: http://tutorials.jenkov.com/java-multithreaded-servers/multithreaded-server.html
public class Server implements Runnable {

	private ServerSocket serverSocket; //server will be started
	private String serverHost; //hostname or IP address of the server
	private int serverPort; //portnumber where server has to start

	private final String DEFAULT_HOST = "localhost"; //Default localhost for serverSocket
	private final int DEFAULT_PORT = 8080; //Default portnumber 8080 for serverSocket

	//Takes DEFAULT_HOST and DEFAULT_PORT values if no values are passed
	public Server ()
	{
		this.serverHost = DEFAULT_HOST;
		this.serverPort = DEFAULT_PORT;
	}


	//Parameterized constructor if hostname is passed
	public Server (String sHost, int port)
	{
		this.serverHost = sHost; //hostname
		this.serverPort = port; //default portnumber 8080
	}


	//Parameterized constructor if a portnumber is passed
	public Server (int port)
	{
		this.serverHost = DEFAULT_HOST; //default hostname
		this.serverPort = port; //port passed by the ServerInitializer
	}


	@Override
	public void run() {

		try {

			//get inet address of the host
			InetAddress serverInet = InetAddress.getByName(serverHost);


			//now using serverInet address and serverPort, initialize serverSocket
			//using a default backlog value which depends on the implementation
			serverSocket = new ServerSocket(serverPort, 0, serverInet);

			System.out.println("[SERVER]> SERVER has started at host: " + serverSocket.getInetAddress() + " port: " + serverSocket.getLocalPort() + "\n");

			//initialize clientID with zero
			int clientID=0;

			//multithreaded server
			//Reference: https://www.geeksforgeeks.org/introducing-threads-socket-programming-java/

			while(true){

				//wait for a client to get connected
				Socket clientSocket = serverSocket.accept();

				System.out.println("[SERVER - CLIENT"+clientID+"]> Successful connection established with client at " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());
				RequestHandler rh = new RequestHandler(clientSocket, clientID);
				new Thread(rh).start();
				clientID++;
			}

		} catch (UnknownHostException e) {
			System.err.println("[SERVER]> UnknownHostException for the hostname: " + serverHost);
		} catch (IllegalArgumentException iae) {
			System.err.println("[SERVER]> EXCEPTION in starting the SERVER: " + iae.getMessage());
		}
		catch (IOException e) {
			System.err.println("[SERVER]> EXCEPTION in starting the SERVER: " + e.getMessage());
		}
		finally {
				try {
					if(serverSocket != null){
						serverSocket.close();
					}
				} catch (IOException e) {
					System.err.println("[SERVER]> EXCEPTION in closing the SERVER." + e);
				}
		}
	}
}
