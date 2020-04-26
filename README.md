# Multithreaded_Server_Client
Implementation of a Multithreaded Web Server and Simple Web Client.

- Development Tools:    
1. Programming Language: Java (jdk 12.0.2)
2. IDE: Eclipse 
3. External Packages: No external packages are required other than default Java packages like java.io and java.net.
4. OS: Windows 10
5. Command Line Interface: Windows command prompt used to compile, run and test the program 
6. Web Browser: Google Chrome used to test the program. 
7. Xampp / TomCat: to run web browser on local host and test program on a web browser.

- Directory Structure:
1. server: Contains source files for the server implementation and default `index.htm` file.   
   	 i)ServerInitializer.java: Initializes the 'Server'at either a default 8080 or user input for port. 
	ii)Server.java: Implements a multithreaded server. Initializes serverSocket to listen the client requests.
		       Once a client is connected, the processing is handed over to a separate RequestHandler thread.
       iii)RequestHandler.java: Communicates with and processes a client's HTTP request in a separate thread.
        iv)index.htm: A default html file which is sent to the client in case a GET request contains "/" filepath.

2. client: Contains source files for the client implemenetation.
   	 i)Client.java: Implements a single threaded web client which communicates with the server on a specific ip:port address.
			   Requests a file on the server.

- Compile & Run Instructions (Instructions for Windows Command Prompt):

1. Compile all server code located in server directory.  
	`cd server`     
	`javac *.java`      

2. Compile client code located in server directory in separate cmd (for convinience). 
 	`cd client`     
	`javac *.java`  

NOTE: In case you get 'javac' command not recognized, try set path=path/to/jdk12.0.2 (set path to your java file location)    

3. Run server in server directory. If no port is passed, a default `8080` port is used. If port is already in use, a proper error message is displayed.
  	  i) To use Default port `8080` run command: 'java ServerInitializer`

	 ii) To take port number from user and run server on the provided port run command: 'java ServerInitializer 6789' 
		(here, 6789 is the port number entered by the user)

4. Run client in client directory by passing at least one argument i.e. serverHost/IP address.
 Other optional arguments are port and the path of the file to request from the server. A proper error message is displayed to the user in case the server cannot be reached due to some network issue or incorrect port value.

	i) Default port `8080` and default file path "/" will be used if run command: `java Client localhost`

       ii) Given port and default file path "/" will be used if run command: `java Client localhost 6789'

      iii) Default port 8080 and given file path will be used if run command: `java Client localhost /path/to/file`

       iv) Given arguments will be used if run command: `java Client localhost 6789 /path/to/file`

- Output:

1. If `/path/to/file` exists on the server, the server will return a `HTTP/1.0 200 OK` response with appropriate content-type and file content.
    How it works:
   *The client will extract status line and show it on the command prompt. 
   *The content of the response body (requested file) will be extracted and written to a local html file at the same location where Client was run from. 
   *The name of this html file will be created using the value of `<title>` tag of the html returned.
   *If `<title>` tag does not contain any value, a default filename `index.html` is used.
   *This is how browsers behave while naming a file when user saves it.

2. If `/path/to/file` does NOT exist on the server, the server will return a `HTTP/1.0 404 Not Found` response with a general error html file. 
   Other behavior will be similar to what is explained in the step above.

3. All logs are using some prefixes like: `[SERVER - CLIENT<id>]>` prefix (id is a unique sequence number given to each client) on the server and `[CLIENT]> prefix` on the client for each output line.

4. All requested file paths must be relative to the `RequestHandler.java` class. If not, 404 error will be returned.

- How to test on Web Browser:

1. Run TomCat or Xampp. 
2. Try different file paths to retrieve the index.htm file.
3. Write localhost:6789, it should show contents of index.htm file.
4. Write localhost:6789/index.htm, it should show contents of index.htm file.
5. Write localhost:6789/path/to/index.htm, it should show contents of index.htm file. 
6. Write localhost:6789.wrongfilename.htm, it should give error message. 
7. Write localhost:12345, it should give error message. 
