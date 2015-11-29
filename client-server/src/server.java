import java.io.*;
import java.net.*;
import com.google.gson.Gson;

/*
 * Author : Shivakumar Soppannavar
 * Start Date: 11/01/2015
 * Last edited :  11/21/2015
 * Server program which is the resident program which queries network information  
 * and sends to connected client.
 */

public class server{

	public void go() {

		String cmd[] = {"getmac", "ipconfig", "netstat -an" };
		ServerSocket serverSocket = null;
		Socket clientSocket = null;
		
		//while (true){
		try {
			serverSocket = new ServerSocket(8888);
			int clientCount =0;
			while (true){
			clientSocket = serverSocket.accept();
			System.out.println("Clinet " + clientCount + " connected to the resident system on port " + clientSocket.getLocalPort() + "\n");
			
			BufferedReader serverInputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			BufferedOutputStream mbio=new BufferedOutputStream(clientSocket.getOutputStream());
			//PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			
			Gson gson= new Gson();
			try {
	            //for (int i=0; i<3; i++){
				Process process = Runtime.getRuntime().exec(cmd[2]);
	            InputStream in = process.getInputStream();
	            BufferedReader br= new BufferedReader(new InputStreamReader(in));
	              //byte[] buf = new byte[256];
	              //int numbytes=0 ;
	             String lineToClient, lineToJson;	           
	             String strarr[]= new String [4];   	  
           	  
	             while ((lineToClient = br.readLine()) != null)
	              {
	            	 lineToJson=lineToClient;
	            	 strarr= lineToJson.trim().split("[ ]+", 4);
	            	 if (strarr[0].equals("TCP")) {
	            	 Output objLine = new Output(strarr[0], strarr[1], strarr[2], strarr[3]);
	            	 System.out.println(gson.toJson(objLine));
	            	 mbio.write(gson.toJson(objLine).getBytes());
	            	 mbio.write("\n".getBytes());
	              	 }
	            	 else if (strarr[0].equals("UDP")) {
		            	 Output objLine = new Output(strarr[0], strarr[1]);
		            	 System.out.println(gson.toJson(objLine));
		            	 mbio.write(gson.toJson(objLine).getBytes());
		            	 mbio.write("\n".getBytes());
		              	 }
	            	}	// End while, end line reading
	            //} // end for loop
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
	//	}//End While loop
	}	//End go 

	public static void main(String[] args) {
		server ObjEchoServer = new server();
		ObjEchoServer.go();
	} // end main
} // end class
