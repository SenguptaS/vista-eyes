import java.io.*;
import java.net.*;

public class server{

	public void go() {

		String cmd[] = {"getmac", "ipconfig", "netstat -a" };
		ServerSocket serverSocket = null;
		Socket clientSocket = null;
		
		try {
			serverSocket = new ServerSocket(8888);
			int clientCount =0;
			while (true){
			clientSocket = serverSocket.accept();
			System.out.println("Clinet " + clientCount + " connected to the resident system on port " + clientSocket.getLocalPort() + "\n");
			BufferedReader serverInputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			BufferedOutputStream mbio=new BufferedOutputStream(clientSocket.getOutputStream());
			
			try {
	            for (int i=0; i<3; i++){
				Process process = Runtime.getRuntime().exec(cmd[i]);
	            InputStream in = process.getInputStream();
	              byte[] buf = new byte[256];
	              int numbytes=0 ;
	              while ((numbytes = in.read(buf, 0, 256)) != -1) {
	                  mbio.write(buf,0, numbytes);
	              	}
	            }
        		} 	catch (Exception e) {
        					e.printStackTrace(System.err);
        			 }
				mbio.flush();
				mbio.close();
				clientCount++;
				serverInputStream.close();
				clientSocket.close();
			 }//End while loop
			} 	catch (IOException e) {
					  System.out.println("Could not listen on port: " + 8888 + ", " + e);
				 }
	}	//End go 

	public static void main(String[] args) {
		server ObjEchoServer = new server();
		ObjEchoServer.go();
	} // end main
} // end class
