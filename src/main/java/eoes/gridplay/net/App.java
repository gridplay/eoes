package eoes.gridplay.net;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
public class App 
{
	public static void main(String[] args) throws InterruptedException {
	    TCPServer tcpServer = new TCPServer(666);
	    UDPServer udpServer = new UDPServer(666);
	    ExecutorService executor = Executors.newFixedThreadPool(2);

	    executor.submit(() -> {
	        try {
	            tcpServer.start();
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	        }
	    });

	    executor.submit(() -> {
	        try {
	            udpServer.start();
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	        }
	    });

	    // Add shutdown hook
	    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
	        System.out.println("Shutting down servers...");
	        executor.shutdownNow();
	        try {
	            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
	                System.err.println("Executor did not terminate in the specified time.");
	                List<Runnable> droppedTasks = executor.shutdownNow();
	                System.err.println("Executor was abruptly shut down. " + droppedTasks.size() + " tasks will not be executed.");
	            }
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	        }
	    }));
	}
}
