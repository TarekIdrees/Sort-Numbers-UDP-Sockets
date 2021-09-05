import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Scanner;

public class server {
	
	static void rvereseArray(int[] arr) {
		   int start = 0; 
		    int end = arr.length - 1; 
		    while (start < end) {
		        int temp = arr[start];
		        arr[start] = arr[end];
		        arr[end] = temp;
		        start = start + 1;
		        end = end - 1;
		    }
		}

	public static void main(String[] args) {
		try {
			
			/// Create socket for server 
			DatagramSocket serverSocket = new DatagramSocket(4070);
			Scanner scanner = new Scanner(System.in);
			System.out.println("System is booted up");
			
			
			
			while(true)
			{
				
				//Receive request from client
				byte [] requestBuffer = new byte[5000];
				DatagramPacket requestPacket = new DatagramPacket(requestBuffer,requestBuffer.length);
				serverSocket.receive(requestPacket);
				// Input from Client
				String request = new String(requestPacket.getData());
				System.out.println("Client: "+request.trim());
				
				String [] arrOfStr = request.split(",");
				
				// parameters of reply to be sent
				String reply = "Please choose:\r\n"+ "1. Ascending order.\r\n"+ "2. Descending order.";
				byte [] replyBytes = reply.getBytes();
				int replyLength = replyBytes.length;
				InetAddress clientIP = requestPacket.getAddress();
				int clientPort=requestPacket.getPort();
				//Send reply
				DatagramPacket replyBacket = new DatagramPacket(replyBytes,replyLength,clientIP,clientPort);
				serverSocket.send(replyBacket);
									

				//Receive request from client
				byte [] request2Buffer = new byte[5000];
				DatagramPacket request2Packet = new DatagramPacket(request2Buffer,request2Buffer.length);
				serverSocket.receive(request2Packet);
				// Input from Client
				String request2 = new String(request2Packet.getData());
				System.out.println("Client: "+request2.trim());
				
										
			    int size=arrOfStr.length;
				int [] arr=new int [size];
				for(int i=0;i<size;i++)
				   {
					    System.out.println("iteration ="+i);
				    	arr[i]=Integer.parseInt(arrOfStr[i].trim());
	
				   }
				Arrays.sort(arr);
										
				if(request2.contains("1"))
				   {
		     			
						StringBuilder stringBuilder = new StringBuilder();
						for (int i = 0; i < arr.length; i++) {
								if(i==0)
									{
										stringBuilder.append("[");
									}
									  stringBuilder.append(arr[i]);
								if(i==(arr.length)-1)
								    {
										 stringBuilder.append("]");
									   	break;
								    }
							   stringBuilder.append(",");
						}
			            String arrS = stringBuilder.toString();
										
						/////// send list after sort numbers
						// parameters of reply to be sent
						String reply2 = arrS;
						byte [] reply2Bytes = reply2.getBytes();
						int reply2Length = reply2Bytes.length;
						//Send reply
						DatagramPacket reply2Backet = new DatagramPacket(reply2Bytes,reply2Length,clientIP,clientPort);
						serverSocket.send(reply2Backet);
						////////
				  }
			else
			     {
						rvereseArray(arr);
						StringBuilder stringBuilder = new StringBuilder();
						for (int i = 0; i < arr.length; i++) {
								if(i==0)
								  {
										stringBuilder.append("[");
							   	  }
							    stringBuilder.append(arr[i]);
							    if(i==(arr.length)-1)
					    		  {
					     		    	stringBuilder.append("]");
								    	break;
	    						  }
									  stringBuilder.append(",");
						}
						String arrS = stringBuilder.toString();
										
	    				/////// revers sort numbers 
						// parameters of reply to be sent
						String reply2 = arrS;
						byte [] reply2Bytes = reply2.getBytes();
						int reply2Length = reply2Bytes.length;
						//Send reply
						 DatagramPacket reply2Backet = new DatagramPacket(reply2Bytes,reply2Length,clientIP,clientPort);
						serverSocket.send(reply2Backet);
						///////
				}				
				
					
			}
			
		} catch (SocketException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}
}