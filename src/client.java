import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class client {

	public static void main(String[] args) {
	
		
		try {
			
			// Create socket for client
			DatagramSocket clientSocket = new DatagramSocket();
			Scanner scanner = new Scanner(System.in);

			
			while(true)
			{
				System.out.println("Server : Please enter the list of numbers to be arranged or 'close' to close.");
				
				
				
				//// 1,2,3,4,5,6
				//Input from user
				String request = scanner.nextLine();
				String [] arrOfStr = request.split(",");
			 
				
				if(request.equalsIgnoreCase("close"))
				{
					//close connection 
					clientSocket.close();
					System.out.println("Client is terminated");
					break;
				}
				else if(!request.equalsIgnoreCase("close")&& arrOfStr[0].matches("\\d"))
				{
					 ///////1,2,3,4,5
					// parameters of request to be sent
					byte[] requestBytes = request.getBytes();
					int requestLength = requestBytes.length;
					InetAddress serverIP=InetAddress.getByName("localhost");
					int serverPort=4070;
					//Send request
					DatagramPacket requestPacket = new DatagramPacket(requestBytes, requestLength,serverIP,serverPort);
					clientSocket.send(requestPacket);
					////////
					while(true)
					{	
						//// please choose 1 or 2 
						//Receive reply from server
						byte [] reply2Buffer = new byte[5000];
						DatagramPacket reply2Packet = new DatagramPacket(reply2Buffer, reply2Buffer.length);
						clientSocket.receive(reply2Packet);
						//Input from server
						String reply2 = new String(reply2Packet.getData());
						System.out.println("Server: "+reply2.trim());
						///////
						
						//Input from user
						String request2 = scanner.nextLine();
					
					if(request2.equalsIgnoreCase("1") || request2.equalsIgnoreCase("2"))
					{
						////// send 1 or 2
						// parameters of request to be sent
						byte[] request2Bytes = request2.getBytes();
						int request2Length = request2Bytes.length;
						//Send request
						DatagramPacket request2Packet = new DatagramPacket(request2Bytes, request2Length,serverIP,serverPort);
						clientSocket.send(request2Packet);
						/////
						
						///recive array
						//Receive reply from server
						byte [] reply3Buffer = new byte[5000];
						DatagramPacket reply3Packet = new DatagramPacket(reply3Buffer, reply3Buffer.length);
						clientSocket.receive(reply3Packet);
						////print array
						//Input from server
						String reply3 = new String(reply3Packet.getData());
						System.out.println("Server: "+reply3.trim());
						break;
						
					}
					else
					{
						System.out.println("Server : please enter 1  or 2 only ");
					}
				 }
			
					
				}
				else
				{
					
					System.out.println("Server: please enter valid number");
					
				}
				
			}
			
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
