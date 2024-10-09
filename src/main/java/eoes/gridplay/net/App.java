package eoes.gridplay.net;

public class App 
{
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Starting server");
	    UDPServer udpServer = new UDPServer(666);
	    udpServer.start();
	}
}
